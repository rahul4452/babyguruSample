package com.example.poplify.baby_guru_sample.before_u_start;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.choose_method.Choose_method_frag;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Select_frag extends Fragment {


    TextView txt,child_1,child_2;
    Typeface regular,regularMon;
    RoundedImageView select_child;
    FragmentManager fragmentManager;
    public Select_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_frag, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(),"Montserrat-Regular.otf");
        fragmentManager = getActivity().getSupportFragmentManager();


        txt = view.findViewById(R.id.selct_your);
        txt.setTypeface(regularMon);

        Spannable s2 = new SpannableString(getResources().getString(R.string.select_your));
        s2.setSpan(new ForegroundColorSpan(Color.rgb(0, 204, 246)),0,23,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt.setText(s2);



        //textview for setting child name
        child_1 = view.findViewById(R.id.child1_name);
        child_1.setTypeface(regularMon);



        Fragment f3 = fragmentManager.findFragmentByTag("Paytab_frag");
        Log.i("adfasdf", "onCreateView: f3 "+f3);

        //button for start session
        select_child = view.findViewById(R.id.add_child);
        select_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacementFragment(new Add_child_tab_frag());
            }
        });


        return view;
    }

    private void replacementFragment(Fragment fragment)
    {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack,0);

        Log.d("", "replacementFragment: fragmentPopped"+fragmentPopped);
        try {
            if (fragmentPopped!=true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
