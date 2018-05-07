package com.mrk.mrkplayer.bean;

import java.io.Serializable;

public class TabItem implements Serializable {
    private int mIcon;
    private String mTv;

    public void setTv(String tv) {
        mTv = tv;
    }

    public String getTv() {
        return mTv;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public int getIcon() {
        return mIcon;
    }
}
