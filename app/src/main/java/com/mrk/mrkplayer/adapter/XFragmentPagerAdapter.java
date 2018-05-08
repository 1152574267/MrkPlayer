package com.mrk.mrkplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mrk.mrkplayer.model.FragmentGenerator;

import java.util.List;

public class XFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public XFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragmentList = FragmentGenerator.getFragmentList();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);

        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
