package com.mrk.mrkplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;

import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.cache.MemoryLruCache;
import com.mrk.mrkplayer.model.MediaDataGenerator;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private DbHelper() {

    }

    public static DbHelper getInstance() {
        return DbHelperHolder.instance;
    }

    private static class DbHelperHolder {
        private static final DbHelper instance = new DbHelper();
    }

    public List<VideoItem> getVideoList(Context context) {
        List<VideoItem> videoList = new ArrayList<VideoItem>();
        videoList.clear();
        final String[] videoColumns = MediaDataGenerator.VideoDataGenerator.videoColumns;

        Cursor cursor = context.getContentResolver().query
                (MediaDataGenerator.videoUri,
                        videoColumns,
                        null,
                        null,
                        videoColumns[3]);
        cursor.moveToFirst();
        do {
            String name = cursor.getString(cursor.getColumnIndex(videoColumns[1]));
            String path = cursor.getString(cursor.getColumnIndex(videoColumns[4]));
            VideoItem item = new VideoItem();
            item.setVideoName(name);
            item.setVideoPath(path);
            videoList.add(item);
        } while (cursor.moveToNext());
        cursor.close();
        cursor = null;

        return videoList;
    }

    //根据路径得到视频缩略图
    public static Bitmap getVideoThumb(String videoPath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(videoPath);
        Bitmap bitmap = null;
        bitmap = mmr.getFrameAtTime();
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 180, 180, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        MemoryLruCache.getInstance().putBitmapToMemoryLruCache(MemoryLruCache.getInstance().getCountFromMemLruCache(), bitmap);

        return bitmap;
    }

}
