package com.example.poplify.baby_guru_sample.child_profile.showGraph;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.poplify.baby_guru_sample.child_profile.showGraph.All_Graph_frag;
import com.example.poplify.baby_guru_sample.child_profile.showGraph.Today_graph_frag;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Today_graph_frag tab1 = new Today_graph_frag();
                return tab1;
            case 1:
                All_Graph_frag tab2 = new All_Graph_frag();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
