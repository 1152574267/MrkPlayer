package com.mrk.mrkplayer.threadpool;

import android.content.Context;
import android.util.Log;

import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.util.DbHelper;

import java.io.IOException;
import java.util.List;

final class RealCall<T> implements Call {
    final TaskClient client;
    private EventListener eventListener;
    private boolean executed;
    private int requestCode;

    private RealCall(TaskClient client, int requestCode) {
        this.client = client;
        this.requestCode = requestCode;
    }

    static RealCall newRealCall(TaskClient client, int requestCode) {
        RealCall call = new RealCall(client, requestCode);
        call.eventListener = client.eventListenerFactory().create(call);
        return call;
    }

    @Override
    public void execute() {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }

        eventListener.callStart(this);
        try {
            client.dispatcher().executed(this);
        } catch (Exception e) {
            eventListener.callFailed(this, e);
            throw e;
        } finally {
            client.dispatcher().finished(this);
        }
    }

    @Override
    public void enqueue(Callback responseCallback) {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }

        eventListener.callStart(this);
        client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public void cancel() {

    }

    @Override
    public synchronized boolean isExecuted() {
        return executed;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public RealCall clone() {
        return RealCall.newRealCall(client, requestCode);
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        AsyncCall(Callback responseCallback) {
            super("AsyncCall");
            this.responseCallback = responseCallback;
        }

        RealCall get() {
            return RealCall.this;
        }

        @Override
        protected void execute() {
            boolean signalledCallback = false;

            try {
                if (isCanceled()) {
                    signalledCallback = true;
                    responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                } else {
                    signalledCallback = true;
                    if (requestCode == 0) {

                        List<VideoItem> mm = DbHelper.getInstance().getVideoList();
                        Log.d("MRK", "mmm---, " + mm.size());
                        responseCallback.onResponse(RealCall.this, mm);
                    }
                }
            } catch (IOException e) {
                if (signalledCallback) {
                    // Do not signal the callback twice!
//                    Platform.get().log(INFO, "Callback failure for " + toLoggableString(), e);
                } else {
                    eventListener.callFailed(RealCall.this, e);
                    responseCallback.onFailure(RealCall.this, e);
                }
            } finally {
                client.dispatcher().finished(this);
            }
        }
    }

    String toLoggableString() {
        return (isCanceled() ? "canceled " : "");
    }

}
