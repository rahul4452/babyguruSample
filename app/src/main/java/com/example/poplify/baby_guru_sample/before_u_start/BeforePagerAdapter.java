package com.example.poplify.baby_guru_sample.before_u_start;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag.BeforeGuruTipFrag;
import com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag.BeforeSleepCoachingFrag;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;

public class BeforePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private BeforeYouStartResponse responseSendToViewpager;
    Bundle bundle = new Bundle();


    public BeforePagerAdapter(FragmentManager fm, int NumOfTabs, BeforeYouStartResponse serverExistUser) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.responseSendToViewpager = serverExistUser;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                bundle.putSerializable("before_you_start", responseSendToViewpager.getBeforeYouStart());
                BeforeSleepCoachingFrag tab1 = new BeforeSleepCoachingFrag();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                bundle.putSerializable("gurusTips", responseSendToViewpager.getGuruTips());
                BeforeGuruTipFrag tab2 = new BeforeGuruTipFrag();
                tab2.setArguments(bundle);
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
