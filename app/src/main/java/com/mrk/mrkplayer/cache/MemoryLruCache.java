package com.mrk.mrkplayer.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryLruCache {
    private LruCache<String, Bitmap> mMemoryLruCache;

    public static MemoryLruCache getInstance() {
        return MemoryLruCacheHolder.instance;
    }

    private static class MemoryLruCacheHolder {
        private static final MemoryLruCache instance = new MemoryLruCache();
    }

    private MemoryLruCache() {
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = memorySize / 8;
        mMemoryLruCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public void putBitmapToMemoryLruCache(String imageUrl, Bitmap bmp) {
        if (getBitmapFromMemLruCache(imageUrl) == null) {
            mMemoryLruCache.put(imageUrl, bmp);
        }
    }

    public Bitmap getBitmapFromMemLruCache(String imageUrl) {
        return mMemoryLruCache.get(imageUrl);
    }

    public String getCountFromMemLruCache() {
        return String.valueOf(mMemoryLruCache.size());
    }

}
