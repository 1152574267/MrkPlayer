package com.mrk.mrkplayer.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mrk.mrkplayer.R;

public class MusicFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MusicFragment";

    private Context mContext;
    private EditText mUri;
    private Button mPlayOnlineBt;
    private Button mPlayShandongBt;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_music, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUri = (EditText) view.findViewById(R.id.et_uri);
        mPlayOnlineBt = (Button) view.findViewById(R.id.bt_play_online);
        mPlayShandongBt = (Button) view.findViewById(R.id.bt_play_shandongweishi);
        mPlayOnlineBt.setOnClickListener(this);
        mPlayShandongBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String uri = null;

        switch (view.getId()) {
            case R.id.bt_play_online:
                uri = mUri.getText().toString().trim();
                break;
            case R.id.bt_play_shandongweishi:
                uri = "http://ivi.bupt.edu.cn/hls/sdhd.m3u8";
                break;
        }
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("videoUri", uri);
        intent.putExtra("videoTitle", "sdhd.m3u8");
        mContext.startActivity(intent);
    }

}
