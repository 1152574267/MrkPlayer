package com.mrk.mrkplayer.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import com.mrk.mrkplayer.bean.FileItem;
import com.mrk.mrkplayer.decoration.MyDecoration;
import com.mrk.mrkplayer.util.DbHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFragment extends Fragment implements XRecyclerViewAdapter.OnItemClickListener, XRecyclerViewAdapter.OnItemLongClickListener {
    private static final String TAG = "DictionaryFragment";

    private Context mContext;
    private RecyclerView fileList;
    private XRecyclerViewAdapter<FileItem> mAdapter;

    private boolean isSdCardMounted;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        List<FileItem> mFileList = new ArrayList<FileItem>();
        mAdapter = new XRecyclerViewAdapter<FileItem>(mContext, mFileList);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);

        isSdCardMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
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

        fileList = (RecyclerView) view.findViewById(R.id.tablist);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
        fileList.setLayoutManager(layoutManager);
        fileList.addItemDecoration(new MyDecoration(mContext, MyDecoration.HORIZONTAL_LIST));
        fileList.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");

        startAsyncTask(Environment.getExternalStorageDirectory().getAbsolutePath());
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

        FileItem item = (FileItem) mAdapter.getItem(position);
        if (item.isDirectory()) {
            startAsyncTask(item.getFilePath());
        } else {
            Intent intent = new Intent(mContext, VideoActivity.class);
            intent.putExtra("videoPath", item.getFilePath());
            intent.putExtra("videoTitle", item.getFileName());
            mContext.startActivity(intent);
        }
    }

    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(mContext, "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
    }

    public void startAsyncTask(String path) {
        if (isSdCardMounted) {
            File file = new File(path);

            mAdapter.addItem(DbHelper.getInstance().getFileList(file));
            fileList.scrollToPosition(0);

            file = null;
        }
    }

}
