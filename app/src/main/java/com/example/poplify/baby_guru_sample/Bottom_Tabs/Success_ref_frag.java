package com.example.poplify.baby_guru_sample.Bottom_Tabs;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.Login;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.before_u_start.Before_we_start1;
import com.example.poplify.baby_guru_sample.before_u_start.Before_we_start_frag;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Success_ref_frag extends Fragment {

    Button pay_suc_ref;
    TextView txt1,txt2,tb_title_ref_suc;
    Typeface regular,regularMon;
    FragmentManager fragmentManager;
    android.support.v7.widget.Toolbar toolbar;
    private Unbinder unbinder;

    public Success_ref_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.success_ref_frag, container, false);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        fragmentManager = getActivity().getSupportFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(),"Montserrat-Regular.otf");

        //yippee
        txt1 = view.findViewById(R.id.txt_yippee);
        txt1.setTypeface(regularMon);

        //Setting Up the toolbar  title
        /*toolbar = view.findViewById(R.id.toolbar_ref_suc);
        tb_title_ref_suc = view.findViewById(R.id.toolbar_title);
        tb_title_ref_suc.setText(getResources().getString(R.string.toolbar_pay));
        tb_title_ref_suc.setTypeface(regular);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar,new Reff_code_frag()).commit();
            }
        });*/

        //pay to get start

        txt2 = view.findViewById(R.id.pay_to_start);
        txt2.setTypeface(regularMon);

        //butterknife
        unbinder = ButterKnife.bind(this,view);


        pay_suc_ref = view.findViewById(R.id.pay_btn_after_ref);
        pay_suc_ref.setTypeface(regular);
        pay_suc_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentTransaction transaction =  fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar,new Before_we_start_frag()).addToBackStack("Reff_code_frag");
               int goback = transaction.commit();
            }
        });
        return view;
    }

    @OnClick(R.id.success_layout)
    public void closeKeyboard() {

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
