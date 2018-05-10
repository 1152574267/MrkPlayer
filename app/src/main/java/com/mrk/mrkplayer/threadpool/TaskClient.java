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

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        Dispatcher dispatcher;
        EventListener.Factory eventListenerFactory;

        public Builder() {
            dispatcher = new Dispatcher();
            eventListenerFactory = EventListener.factory(EventListener.NONE);
        }

        Builder(TaskClient okHttpClient) {
            this.dispatcher = okHttpClient.dispatcher;
            this.eventListenerFactory = okHttpClient.eventListenerFactory;
        }

        public Builder dispatcher(Dispatcher dispatcher) {
            if (dispatcher == null) throw new IllegalArgumentException("dispatcher == null");
            this.dispatcher = dispatcher;
            return this;
        }

        public Builder eventListener(EventListener eventListener) {
            if (eventListener == null) throw new NullPointerException("eventListener == null");
            this.eventListenerFactory = EventListener.factory(eventListener);
            return this;
        }

        public Builder eventListenerFactory(EventListener.Factory eventListenerFactory) {
            if (eventListenerFactory == null) {
                throw new NullPointerException("eventListenerFactory == null");
            }
            this.eventListenerFactory = eventListenerFactory;
            return this;
        }

        public TaskClient build() {
            return new TaskClient(this);
        }
    }

}
