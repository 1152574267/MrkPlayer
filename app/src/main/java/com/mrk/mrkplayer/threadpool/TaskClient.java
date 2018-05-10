package com.mrk.mrkplayer.threadpool;

public class TaskClient implements Cloneable, Call.Factory {
    final Dispatcher dispatcher;
    final EventListener.Factory eventListenerFactory;

    public TaskClient() {
        this(new Builder());
    }

    TaskClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
        this.eventListenerFactory = builder.eventListenerFactory;
    }

    public Dispatcher dispatcher() {
        return dispatcher;
    }

    public EventListener.Factory eventListenerFactory() {
        return eventListenerFactory;
    }

    @Override
    public Call newCall(int requestCode) {
        return RealCall.newRealCall(this, requestCode);
    }

    public static final class Builder {
        Dispatcher dispatcher;
        EventListener.Factory eventListenerFactory;

        public Builder() {
            dispatcher = new Dispatcher();
            eventListenerFactory = EventListener.factory(EventListener.NONE);
        }

        public TaskClient build() {
            return new TaskClient(this);
        }
    }

}
