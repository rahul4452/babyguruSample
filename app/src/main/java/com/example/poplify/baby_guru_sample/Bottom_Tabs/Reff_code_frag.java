package com.example.poplify.baby_guru_sample.Bottom_Tabs;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.poplify.baby_guru_sample.Login;
import com.example.poplify.baby_guru_sample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reff_code_frag extends Fragment {


    private static final String TAG = "Reff_code_frag";
    private Unbinder unbinder;
    MotionEvent event;
    Button app_btn,cancal_btn;
    Typeface regular,regularMon;
    TextView tv1,tv2,tb_title_ref;
    android.support.v7.widget.Toolbar toolbar;
    FragmentManager fragmentManager;

    public Reff_code_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reff_code_frag, container, false);
        final Fragment fragment = new Success_ref_frag();

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(),"Montserrat-Regular.otf");

        //Enter Refferal code to get
        tv1 =view.findViewById(R.id.txt_for_entr);
        tv1.setTypeface(regularMon);

        //butterknife
        unbinder = ButterKnife.bind(this,view);

        //Setting Up the toolbar  title
        /*toolbar = view.findViewById(R.id.toolbar_ref);
        tb_title_ref = view.findViewById(R.id.toolbar_title);
        tb_title_ref.setText(getResources().getString(R.string.toolbar_pay));
        tb_title_ref.setTypeface(regular);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar,new Paytab_frag()).commit();
            }
        });*/

        //refercode tv
        tv2 = view.findViewById(R.id.refer_code_txt);
        tv2.setTypeface(regularMon);

        cancal_btn = view.findViewById(R.id.cancel_btn);
        cancal_btn.setTypeface(regular);


        fragmentManager = getActivity().getSupportFragmentManager();


        app_btn = view.findViewById(R.id.apply_btn);
        app_btn.setTypeface(regular);
        app_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar,fragment).addToBackStack("Reff_code_frag");
                int goback = transaction.commit();
            }
        });


        return view;
    }



    @OnClick(R.id.reff_layout)
    public void closeKeyboard() {

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }



}
