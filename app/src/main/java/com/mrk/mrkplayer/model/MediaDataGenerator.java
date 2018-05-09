package com.mrk.mrkplayer.model;

import android.net.Uri;
import android.provider.MediaStore;

public class MediaDataGenerator {
    public static final Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    public static final Uri videoThumbUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;

    public static class VideoDataGenerator {
        public static final String[] videoColumns = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DEFAULT_SORT_ORDER,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_ID,
                MediaStore.Video.Media.CATEGORY,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.RESOLUTION
        };

        public static final String[] videoThumbColumns = {
                MediaStore.Video.Thumbnails._ID,
                MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID,
                MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER
        };

    }

}