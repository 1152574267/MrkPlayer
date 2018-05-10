package com.mrk.mrkplayer.threadpool;

public interface Call extends Cloneable {
    void execute();

    void enqueue(Callback responseCallback);

    void cancel();

    boolean isExecuted();

    interface Factory {
        Call newCall(int requestCode);
    }
}
