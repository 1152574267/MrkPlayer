package com.mrk.mrkplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;

import com.mrk.mrkplayer.bean.FileItem;
import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.cache.MemoryLruCache;
import com.mrk.mrkplayer.model.MediaDataGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DbHelper {
    private Context mContext;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        return DbHelperHolder.instance;
    }

    private static class DbHelperHolder {
        private static final DbHelper instance = new DbHelper();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<VideoItem> getVideoList() {
        List<VideoItem> videoList = new ArrayList<VideoItem>();
        videoList.clear();
        final String[] videoColumns = MediaDataGenerator.VideoDataGenerator.videoColumns;

        Cursor cursor = mContext.getContentResolver().query
                (MediaDataGenerator.videoUri,
                        videoColumns,
                        null,
                        null,
                        videoColumns[3]);

        if (cursor != null) {
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
        }

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

    public List<FileItem> getFileList(File file) {
        List<FileItem> fileList = new ArrayList<FileItem>();
        fileList.clear();

        if (file.exists() && file.canRead() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                FileItem item = new FileItem();
                item.setFileName(files[i].getName());
                item.setIsDirectory(files[i].isDirectory());
                item.setFilePath(files[i].getAbsolutePath());
                fileList.add(item);
            }
        }

        if (fileList != null && (fileList.size() > 0)) {
            sortByName(fileList);
        }
        return fileList;
    }

    public void sortByName(List<FileItem> fileList) {
        Collections.sort(fileList, new Comparator<FileItem>() {

            @Override
            public int compare(FileItem o1, FileItem o2) {
                if (o1.isDirectory() && !o2.isDirectory()) {
                    return -1;
                }

                if (!o1.isDirectory() && o2.isDirectory()) {
                    return 1;
                }

                return o1.getFileName().compareTo(o2.getFileName());
            }
        });
    }

    public static boolean isVideo(String filename) {
        int i = filename.indexOf('.');
        if (i != -1) {
            filename = filename.substring(i);
            if (filename.equalsIgnoreCase(".mp4")
                    || filename.equalsIgnoreCase(".3gp")
                    || filename.equalsIgnoreCase(".wmv")
                    || filename.equalsIgnoreCase(".ts")
                    || filename.equalsIgnoreCase(".rmvb")
                    || filename.equalsIgnoreCase(".mov")
                    || filename.equalsIgnoreCase(".m4v")
                    || filename.equalsIgnoreCase(".avi")
                    || filename.equalsIgnoreCase(".m3u8")
                    || filename.equalsIgnoreCase(".3gpp")
                    || filename.equalsIgnoreCase(".3gpp2")
                    || filename.equalsIgnoreCase(".mkv")
                    || filename.equalsIgnoreCase(".flv")
                    || filename.equalsIgnoreCase(".divx")
                    || filename.equalsIgnoreCase(".f4v")
                    || filename.equalsIgnoreCase(".rm")
                    || filename.equalsIgnoreCase(".asf")
                    || filename.equalsIgnoreCase(".ram")
                    || filename.equalsIgnoreCase(".mpg")
                    || filename.equalsIgnoreCase(".v8")
                    || filename.equalsIgnoreCase(".swf")
                    || filename.equalsIgnoreCase(".m2v")
                    || filename.equalsIgnoreCase(".asx")
                    || filename.equalsIgnoreCase(".ra")
                    || filename.equalsIgnoreCase(".ndivx")
                    || filename.equalsIgnoreCase(".xvid")) {
                return true;
            }
        }
        return false;
    }

}
