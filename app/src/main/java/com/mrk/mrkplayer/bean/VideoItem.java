package com.mrk.mrkplayer.bean;

import java.io.Serializable;

public class VideoItem implements Serializable {
    private String mThumbPath;
    private String mName;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setThumbPath(String thumbPath) {
        mThumbPath = thumbPath;
    }

    public String getThumbPath() {
        return mThumbPath;
    }
}
