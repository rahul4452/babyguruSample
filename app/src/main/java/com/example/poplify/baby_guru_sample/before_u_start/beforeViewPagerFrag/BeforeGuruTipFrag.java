package com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poplify.baby_guru_sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeGuruTipFrag extends Fragment {


    public BeforeGuruTipFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_before_guru_tip, container, false);


        return view;
    }

}
