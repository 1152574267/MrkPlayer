package com.mrk.mrkplayer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mrk.mrkplayer.adapter.XFragmentPagerAdapter;
import com.mrk.mrkplayer.model.FragmentGenerator;
import com.mrk.mrkplayer.view.DictionaryFragment;
import com.mrk.mrkrecorder.MediaRecorderActivity;
import com.mrk.mrkrecorder.model.MediaRecorderConfig;

public class MainActivity extends AppCompatActivity implements
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
        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }

        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_list);
        mViewPager = (ViewPager) findViewById(R.id.tab_viewpager);

        fpa = new XFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
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

    @Override
    public void onBackPressed() {
        final int index = mViewPager.getCurrentItem();
        switch (index) {
            case 0:
                exitActivity();
                break;
            case 1:
                exitActivity();
                break;
            case 2:
                DictionaryFragment fragment = (DictionaryFragment) (fpa.getItem(index));
                fragment.onBackPressed();
                break;
        }
    }

    public void exitActivity() {
        super.onBackPressed();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            MediaRecorderConfig config = new MediaRecorderConfig.Buidler()
                    .fullScreen(true)
                    .smallVideoWidth(0)
                    .smallVideoHeight(1080)
                    .recordTimeMax(15000)
                    .recordTimeMin(1500)
                    .maxFrameRate(20)
                    .videoBitrate(580000)
                    .captureThumbnailsTime(1)
                    .build();
            MediaRecorderActivity.goSmallVideoRecorder(this, config);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
