package com.mrk.mrkplayer.threadpool;

public class TaskClient implements Cloneable, Call.Factory {
    final Dispatcher dispatcher;

    public TaskClient() {
        this(new Builder());
    }

    TaskClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    public Dispatcher dispatcher() {
        return dispatcher;
    }

    @Override
    public Call newCall(int requestCode) {
        return RealCall.newRealCall(this, requestCode);
    }

    public static final class Builder {
        Dispatcher dispatcher;

        public Builder() {
            dispatcher = new Dispatcher();
        }

        public TaskClient build() {
            return new TaskClient(this);
        }
    }

}
