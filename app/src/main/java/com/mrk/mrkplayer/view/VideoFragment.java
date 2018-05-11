package com.mrk.mrkplayer.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.adapter.XRecyclerViewAdapter;
import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.decoration.MyDecoration;
import com.mrk.mrkplayer.threadpool.Call;
import com.mrk.mrkplayer.threadpool.Callback;
import com.mrk.mrkplayer.threadpool.TaskClient;
import com.mrk.mrkplayer.util.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment implements XRecyclerViewAdapter.OnItemClickListener, XRecyclerViewAdapter.OnItemLongClickListener {
    private static final String TAG = "VideoFragment";

    private Context mContext;
    private RecyclerView videoList;
    private XRecyclerViewAdapter<VideoItem> mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        DbHelper.getInstance().setContext(mContext);
        List<VideoItem> mVideoList = new ArrayList<VideoItem>();
        mAdapter = new XRecyclerViewAdapter<VideoItem>(mContext, mVideoList);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        videoList = (RecyclerView) view.findViewById(R.id.tablist);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
        videoList.setLayoutManager(layoutManager);
        videoList.addItemDecoration(new MyDecoration(mContext, MyDecoration.HORIZONTAL_LIST));
        videoList.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");

        startAsyncTask();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mContext = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint isVisibleToUser: " + isVisibleToUser);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(mContext, "onItemClick: " + position, Toast.LENGTH_SHORT).show();

        VideoItem item = (VideoItem) mAdapter.getItem(position);
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("videoPath", item.getVideoPath());
        intent.putExtra("videoTitle", item.getVideoName());
        mContext.startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(mContext, "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
    }

    public void startAsyncTask() {
        TaskClient client = new TaskClient();
        client.newCall(0).enqueue(new Callback<VideoItem>() {

            @Override
            public void onFailure(Call call, Exception e) {
                Log.d(TAG, "startAsyncTask exception: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, List<VideoItem> response) {
                mAdapter.addItem(response);
                videoList.scrollToPosition(0);
            }
        });
    }

}
