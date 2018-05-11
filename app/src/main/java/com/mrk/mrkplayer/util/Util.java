package com.mrk.mrkplayer.util;

import java.util.concurrent.ThreadFactory;

public final class Util {
    public static final int REQUEST_VIDEO = 0;

    public static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() {

            @Override
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }

}
