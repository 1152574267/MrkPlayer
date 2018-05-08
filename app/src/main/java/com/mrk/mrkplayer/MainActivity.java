package com.mrk.mrkplayer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.mrk.mrkplayer.model.FragmentGenerator;

import java.util.List;

public class MainActivity extends FragmentActivity implements /*XRecyclerViewAdapter.OnItemClickListener, XRecyclerViewAdapter.OnItemLongClickListener
        , */TabLayout.OnTabSelectedListener {
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_list);

        mFragmentList = FragmentGenerator.getFragmentList();

        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initData() {
//        RecyclerView tabList = (RecyclerView) findViewById(R.id.tablist);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
//        tabList.setLayoutManager(layoutManager);

//        List<TabItem> mDataList = new ArrayList<TabItem>();
        for (int i = 0; i < 3; i++) {
//            TabItem item = new TabItem();
//            item.setTv(getResources().getString(arr[i]));
//            item.setIcon(drawableArr[i]);
//            mDataList.add(item);
            mTabLayout.addTab(mTabLayout.newTab().setIcon(FragmentGenerator.drawableArr[i]).setText(FragmentGenerator.strArr[i]));
        }

//        tabList.addItemDecoration(new MyDecoration(this, MyDecoration.HORIZONTAL_LIST));
//        XRecyclerViewAdapter adapter = new XRecyclerViewAdapter(this, mDataList);
//        adapter.setOnItemClickListener(this);
//        adapter.setOnItemLongClickListener(this);
//        tabList.setAdapter(adapter);
    }

//    @Override
//    public void onItemClick(int position) {
//        Toast.makeText(getApplicationContext(), "onItemClick: " + position, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onItemLongClick(int position) {
//        Toast.makeText(getApplicationContext(), "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        onTabItemSelected(position);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            if (i == position) {
                mTabLayout.getTabAt(i).setIcon(R.drawable.ic_launcher);
            } else {
                mTabLayout.getTabAt(i).setIcon(android.R.drawable.btn_dialog);
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = mFragmentList.get(0);
                break;
            case 1:
                fragment = mFragmentList.get(1);
                break;
            case 2:
                fragment = mFragmentList.get(2);
                break;
            default:
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.tab_container, fragment).commitAllowingStateLoss();
        }
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
