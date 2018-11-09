package com.example.poplify.baby_guru_sample.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.view_pager_frag.Pager_frag_1;

public class MyAdapter extends FragmentPagerAdapter {

    Pager_frag_1 pf1;
    Pager_frag_1 pf2;

    Context mContext;

    public MyAdapter(FragmentManager fm,Context c) {
        super(fm);
        this.mContext = c;
    }

    public void setFragments(Context c){

        // Set up the simple base fragments
        pf1 = new Pager_frag_1();
        pf2 = new Pager_frag_1();


        Resources res = c.getResources();

        pf1.changeText(res.getString(R.string.pager_1));
        pf2.changeText("This is Fragment B!");


    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        if(i == 0){
            frag = pf1;
        }
        else if(i == 1){
            frag = pf2;
        }

        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
