package com.mrk.mrkplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mrk.mrkplayer.decoration.MyDecoration;
import com.mrk.mrkplayer.adapter.XRecyclerViewAdapter;
import com.mrk.mrkplayer.bean.TabItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements XRecyclerViewAdapter.OnItemClickListener, XRecyclerViewAdapter.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        RecyclerView tabList = (RecyclerView) findViewById(R.id.tablist);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        tabList.setLayoutManager(layoutManager);

        // 创建数据集
        int[] arr = new int[]{R.string.tab_video, R.string.tab_music, R.string.tab_dictionary};
        int[] drawableArr = new int[]{R.drawable.tab_video, R.drawable.tab_music, R.drawable.tab_dictionary};
        List<TabItem> mDataList = new ArrayList<TabItem>();
        for (int i = 0; i < 3; i++) {
            TabItem item = new TabItem();
            item.setTv(getResources().getString(arr[i]));
            item.setIcon(drawableArr[i]);
            mDataList.add(item);
        }

        tabList.addItemDecoration(new MyDecoration(this, MyDecoration.HORIZONTAL_LIST));
        XRecyclerViewAdapter adapter = new XRecyclerViewAdapter(this, mDataList);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        tabList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getApplicationContext(), "onItemClick: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(getApplicationContext(), "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

}
