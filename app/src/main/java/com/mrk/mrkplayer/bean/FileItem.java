package com.mrk.mrkplayer.bean;

import java.io.Serializable;

public class FileItem implements Serializable {
    private int mIcon;
    private String mName;
    private String mPath;
    private boolean mIsDirectory;

    public void setFileName(String name) {
        mName = name;
    }

    public String getFileName() {
        return mName;
    }

    public void setFileIcon(int icon) {
        mIcon = icon;
    }

    public int getFileIcon() {
        return mIcon;
    }

    public void setIsDirectory(boolean isDirectory) {
        mIsDirectory = isDirectory;
    }

    public boolean isDirectory() {
        return mIsDirectory;
    }

    public void setFilePath(String path) {
        mPath = path;
    }

    public String getFilePath() {
        return mPath;
    }

}
