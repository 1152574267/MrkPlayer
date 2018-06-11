package com.mrk.mrkplayer.util;

import android.Manifest;

public class PermissionUtil {
    public static final String[] REQUEST_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    public static final int REQUEST_PERMISSIONS_CODE = 100;
}
