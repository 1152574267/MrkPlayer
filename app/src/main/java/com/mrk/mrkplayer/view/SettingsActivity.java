package com.mrk.mrkplayer.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.mrk.mrkplayer.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Fragment newFragment = SettingsFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.fl_container, newFragment).commitAllowingStateLoss();
    }

}
