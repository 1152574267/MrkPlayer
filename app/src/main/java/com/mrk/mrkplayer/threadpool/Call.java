package com.mrk.mrkplayer.threadpool;

import java.util.List;

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
