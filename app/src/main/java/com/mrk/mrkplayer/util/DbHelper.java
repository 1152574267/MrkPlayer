package com.mrk.mrkplayer.util;

import android.content.Context;
import android.database.Cursor;

import com.mrk.mrkplayer.bean.VideoItem;
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
        final String[] videoThumbColumns = MediaDataGenerator.VideoDataGenerator.videoThumbColumns;

        Cursor cursor = context.getContentResolver().query
                (MediaDataGenerator.videoUri,
                        videoColumns,
                        null,
                        null,
                        videoColumns[3]);
        cursor.moveToFirst();
        do {
            String name = cursor.getString(cursor.getColumnIndex(videoColumns[1]));
            VideoItem item = new VideoItem();
            item.setName(name);
            videoList.add(item);
        } while (cursor.moveToNext());
        cursor.close();
        cursor = null;

//        Cursor thumbCursor = context.getContentResolver().query(
//                MediaDataGenerator.videoThumbUri,
//                videoThumbColumns,
//                null,
//                null,
//                videoThumbColumns[0]);
//        thumbCursor.moveToFirst();
//        do {
//            String path = thumbCursor.getString(thumbCursor.getColumnIndex(videoThumbColumns[1]));
//        } while (thumbCursor.moveToNext());
//        thumbCursor.close();
//        thumbCursor = null;

        return videoList;
    }

}
