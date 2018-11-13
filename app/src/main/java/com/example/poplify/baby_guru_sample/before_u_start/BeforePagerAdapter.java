package com.example.poplify.baby_guru_sample.before_u_start;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag.BeforeGuruTipFrag;
import com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag.BeforeSleepCoachingFrag;

public class BeforePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public BeforePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BeforeSleepCoachingFrag tab1 = new BeforeSleepCoachingFrag();
                return tab1;
            case 1:
                BeforeGuruTipFrag tab2 = new BeforeGuruTipFrag();
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
