package com.example.poplify.baby_guru_sample.Bottom_Tabs;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.firstTab.beforeYouStart.Before_we_start_frag;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paytab_frag extends Fragment {


    private Unbinder unbinder;
    Typeface regular, regularMon;
    EditText enter_amt;
    TextView reff_code, txt1, txt2,tb_title_pay;
    MotionEvent event;
    Button pay;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentActivity fragmentActivity;

    public Paytab_frag() {
        // Required empty public constructor
    }

    private static final String TAG = "Paytab_frag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.paytab_frag, container, false);

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        final Fragment fragment = new Reff_code_frag();
        fragmentManager = getActivity().getSupportFragmentManager();
       getFragmentManager().findFragmentByTag(TAG);


        fragmentActivity = getActivity();
        //tv before enter amount
        txt1 = view.findViewById(R.id.pay_txt);
        txt1.setTypeface(regularMon);




        enter_amt = view.findViewById(R.id.amt_txt);

        //Setting Up the toolbar  title
        toolbar = view.findViewById(R.id.toolbar_pay);
        tb_title_pay = view.findViewById(R.id.toolbar_title);
        tb_title_pay.setText(getResources().getString(R.string.toolbar_pay));
        tb_title_pay.setTypeface(regular);

        /*toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pay_back = new Intent(getActivity(),Login.class);
                startActivity(pay_back);
            }
        });*/


        //butterknife
        unbinder = ButterKnife.bind(this, view);


        //tv after enter amount
        txt2 = view.findViewById(R.id.pay_txt2);
        txt2.setTypeface(regularMon);

        reff_code = view.findViewById(R.id.ref_code_txt);
        reff_code.setTypeface(regularMon);
        Spannable s = new SpannableString(getResources().getString(R.string.refe_link));
        int reff_length = s.length();
        Log.d("Lenght", "onCreateView: reff lenght " + reff_length);
        s.setSpan(new ForegroundColorSpan(Color.rgb(214, 153, 255)), 0, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        reff_code.setText(s);

        pay = view.findViewById(R.id.pay_btn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar, new Before_we_start_frag()).addToBackStack("pay");
                int goback =  transaction.commit();
            }
        });
        pay.setTypeface(regular);




        reff_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.fragment_container_navbar, fragment).commit();

            }
        });


        return view;
    }


    @OnClick(R.id.pay_layout)
    void closeKeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
