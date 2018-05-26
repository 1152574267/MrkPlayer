package com.mrk.mrkplayer.easyijkplayer.utils;

public class MediaUtils {

    /**
     * 当前播放的是否是流媒体格式的视频
     */
    public static boolean isStreamingMedia(String uri) {
        boolean isStreamingMedia = false;

        if (uri != null
                && (uri.startsWith("rtmp://") || uri.startsWith("rtsp://")
                || (uri.startsWith("http://") && uri.endsWith(".m3u8"))
                || (uri.startsWith("http://") && uri.endsWith(".flv")))) {
            isStreamingMedia = true;
        } else {
            isStreamingMedia = false;
        }

        return isStreamingMedia;
    }

}
