package com.mrk.mrkplayer.threadpool;

public abstract class EventListener {
    public static final EventListener NONE = new EventListener() {
    };

    static Factory factory(final EventListener listener) {
        return new Factory() {
            public EventListener create(Call call) {
                return listener;
            }
        };
    }

    public void callStart(Call call) {
    }

    public void callEnd(Call call) {
    }

    public void callFailed(Call call, Exception ioe) {
    }

    public interface Factory {
        EventListener create(Call call);
    }
}
