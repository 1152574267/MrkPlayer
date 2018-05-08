package com.mrk.mrkplayer.util;

import android.content.Context;

public class DbHelper {
    private Context mContext;

    private DbHelper() {

    }

    public void attachContext(Context context) {
        mContext = context;
    }

    public static DbHelper getInstance() {
        return DbHelperHolder.instance;
    }

    static class DbHelperHolder {
        static final DbHelper instance = new DbHelper();
    }

}
