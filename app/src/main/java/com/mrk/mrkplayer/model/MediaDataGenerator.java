package com.mrk.mrkplayer.model;

import android.net.Uri;
import android.provider.MediaStore;

public class MediaDataGenerator {
    public static final Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    public static class VideoDataGenerator {
        public static final String[] videoColumns = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.RESOLUTION
        };
    }

}
