package com.mrk.mrkplayer.model;

import android.support.v4.app.Fragment;

import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.view.DictionaryFragment;
import com.mrk.mrkplayer.view.OnlineVideoFragment;
import com.mrk.mrkplayer.view.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentGenerator {
    public static int[] strArr = new int[]{R.string.tab_video, R.string.tab_music, R.string.tab_dictionary};
    public static int[] drawableArr = new int[]{R.drawable.tab_video, R.drawable.tab_music, R.drawable.tab_dictionary};

    public static List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new VideoFragment());
        fragmentList.add(new OnlineVideoFragment());
        fragmentList.add(new DictionaryFragment());

        return fragmentList;
    }

}
