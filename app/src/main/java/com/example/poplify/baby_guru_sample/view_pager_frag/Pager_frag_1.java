package com.example.poplify.baby_guru_sample.view_pager_frag;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pager_frag_1 extends Fragment {

    Typeface bold,regular,regularMon;

    String nText="";

    public Pager_frag_1() {
        // Required empty public constructor
    }


    TextView inner_pager_tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pager_frag_1, container, false);

        regular = Typeface.createFromAsset(getActivity().getResources().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getResources().getAssets(),"Montserrat-Regular.otf");

        inner_pager_tv = view.findViewById(R.id.pager_inner_text);
        inner_pager_tv.setTypeface(regularMon);

        inner_pager_tv.setText(getResources().getString(R.string.pager_1));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (nText != "")
        {
            inner_pager_tv.setText(nText);
        }
    }


    public void changeText(String newText){
        nText=newText;
    }
}
