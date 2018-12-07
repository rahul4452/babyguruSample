package com.example.poplify.baby_guru_sample.child_profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poplify.baby_guru_sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMethod extends Fragment {


    public ChooseMethod() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_method, container, false);
    }

}
