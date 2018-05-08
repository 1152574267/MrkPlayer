package com.mrk.mrkplayer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.mrk.mrkplayer.adapter.XFragmentPagerAdapter;
import com.mrk.mrkplayer.model.FragmentGenerator;

public class MainActivity extends FragmentActivity implements
        TabLayout.OnTabSelectedListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter fpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_list);
        mViewPager = (ViewPager) findViewById(R.id.tab_viewpager);

        fpa = new XFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(fpa);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initData() {
        mTabLayout.removeAllTabs();

        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setIcon(FragmentGenerator.drawableArr[i]).setText(FragmentGenerator.strArr[i]));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        onTabItemSelected(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void onTabItemSelected(int position) {
        Fragment fragment = fpa.getItem(position);
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
