package com.mrk.mrkplayer;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.mrk.mrkplayer.model.EasyFileNameGenerator;

public class EasyVideoApplication extends Application {
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        EasyVideoApplication app = (EasyVideoApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
//        return new HttpProxyCacheServer.Builder(this)
//                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
//                .maxCacheFilesCount(20)
//                .fileNameGenerator(new EasyFileNameGenerator())
//                .build();
        return new HttpProxyCacheServer(this);
    }

}
