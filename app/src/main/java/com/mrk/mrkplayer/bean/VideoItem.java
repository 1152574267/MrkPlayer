package com.mrk.mrkplayer.bean;

import java.io.Serializable;

public class VideoItem implements Serializable {
    private String mPath;
    private String mDisplayName;

    public void setVideoName(String displayName) {
        mDisplayName = displayName;
    }

    public String getVideoName() {
        return mDisplayName;
    }

    public void setVideoPath(String path) {
        mPath = path;
    }

    public String getVideoPath() {
        return mPath;
    }
}
