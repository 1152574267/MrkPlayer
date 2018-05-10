package com.mrk.mrkplayer.threadpool;

public interface Call extends Cloneable {
    void execute();

    void enqueue(Callback responseCallback);

    void cancel();

    boolean isExecuted();

    boolean isCanceled();

    Call clone();

    interface Factory {
        Call newCall(int requestCode);
    }
}
