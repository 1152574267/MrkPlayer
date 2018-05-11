package com.mrk.mrkplayer.threadpool;

import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.util.DbHelper;
import com.mrk.mrkplayer.util.Util;

import java.util.List;

final class RealCall<T> implements Call {
    final TaskClient client;
    private boolean executed;
    private int requestCode;

    private RealCall(TaskClient client, int requestCode) {
        this.client = client;
        this.requestCode = requestCode;
    }

    static RealCall newRealCall(TaskClient client, int requestCode) {
        RealCall call = new RealCall(client, requestCode);

        return call;
    }

    @Override
    public void execute() {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }

        try {
            client.dispatcher().executed(this);
        } catch (Exception e) {

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

        client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public void cancel() {

    }

    @Override
    public synchronized boolean isExecuted() {
        return executed;
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        AsyncCall(Callback responseCallback) {
            super("AsyncCall");
            this.responseCallback = responseCallback;
        }

        @Override
        protected void execute() {
            try {
                if (requestCode == Util.REQUEST_VIDEO) {
                    final List<VideoItem> videoList = DbHelper.getInstance().getVideoList();
                    responseCallback.onResponse(RealCall.this, videoList);
                }
            } catch (Exception e) {
                responseCallback.onFailure(RealCall.this, e);
            } finally {
                client.dispatcher().finished(this);
            }
        }
    }

}
