package com.example.poplify.baby_guru_sample.choose_method;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;

import java.io.Serializable;

public class ChooseMethodAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private CryingScaleResponse responseSendToViewpager;
    Bundle bundle = new Bundle();

    public ChooseMethodAdapter(FragmentManager fm, int mNumOfTabs, CryingScaleResponse cryingScaleResponse) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
        this.responseSendToViewpager = cryingScaleResponse;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                bundle.putSerializable("method1", responseSendToViewpager);
                Choose_mthd_recomm_frag tab1 = new Choose_mthd_recomm_frag();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                bundle.putSerializable("method2",  responseSendToViewpager);
                Choose_mthd_other_frag tab2 = new Choose_mthd_other_frag();
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
