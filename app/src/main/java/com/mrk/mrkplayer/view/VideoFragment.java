package com.mrk.mrkplayer.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.adapter.XRecyclerViewAdapter;
import com.mrk.mrkplayer.bean.VideoItem;
import com.mrk.mrkplayer.decoration.MyDecoration;
import com.mrk.mrkplayer.util.DbHelper;

import java.util.List;

public class VideoFragment extends Fragment implements XRecyclerViewAdapter.OnItemClickListener, XRecyclerViewAdapter.OnItemLongClickListener {
    private Context mContext;
    private RecyclerView videoList;

    List<VideoItem> mVideoList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_video, container, false);
        videoList = (RecyclerView) view.findViewById(R.id.tablist);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
        videoList.setLayoutManager(layoutManager);

        mVideoList = DbHelper.getInstance().getVideoList(mContext);
        XRecyclerViewAdapter<VideoItem> adapter = new XRecyclerViewAdapter(mContext, mVideoList);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        videoList.addItemDecoration(new MyDecoration(mContext, MyDecoration.HORIZONTAL_LIST));
        videoList.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mContext = null;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(mContext, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(mContext, "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
    }

}
