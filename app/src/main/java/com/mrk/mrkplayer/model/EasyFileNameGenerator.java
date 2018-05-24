package com.mrk.mrkplayer.model;

import android.net.Uri;

import com.danikula.videocache.file.FileNameGenerator;

public class EasyFileNameGenerator implements FileNameGenerator {

    public String generate(String url) {
        Uri uri = Uri.parse(url);
        String videoId = uri.getQueryParameter("videoId");

        return videoId + ".mp4";
    }

}
