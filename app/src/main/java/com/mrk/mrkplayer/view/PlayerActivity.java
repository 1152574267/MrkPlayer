package com.mrk.mrkplayer.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.easyijkplayer.listener.OnPlayerBackListener;
import com.mrk.mrkplayer.easyijkplayer.listener.OnShowThumbnailListener;
import com.mrk.mrkplayer.easyijkplayer.media.EasyVideoView;
import com.mrk.mrkplayer.easyijkplayer.media.PlayStateParams;

public class PlayerActivity extends Activity {
    private EasyVideoView player;
    private Context mContext;
    private View rootView;

    private String mVideoPath;
    public String url;
    private String mVideoUriStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        setContentView(rootView);

        url = null;
        mVideoPath = null;
        mVideoUriStr = null;
        mVideoPath = getIntent().getStringExtra("videoPath");
        mVideoUriStr = getIntent().getStringExtra("videoUri");
        if (mVideoPath != null) {
            url = mVideoPath;
        }
        if (mVideoUriStr != null) {
            url = mVideoUriStr;
        }

        player = new EasyVideoView(this, rootView)
                .setTitle(" ")
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideMenu(true)
                .showThumbnail(new OnShowThumbnailListener() {

                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(PlayerActivity.this)
                                .load(PlayerActivity.this.url)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(url)
                .setPlayerBackListener(new OnPlayerBackListener() {

                    @Override
                    public void onPlayerBack() {
                        finish();
                    }
                })
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

}
