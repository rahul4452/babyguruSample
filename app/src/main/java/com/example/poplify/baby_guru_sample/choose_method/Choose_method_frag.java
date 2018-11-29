package com.example.poplify.baby_guru_sample.choose_method;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_method_frag extends Fragment {

    TextView tb_title_choose;
    Typeface regular,regularMon;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    public Choose_method_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_method_frag, container, false);

        fragmentManager = getFragmentManager();
        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(),"Montserrat-Regular.otf");



        //Setting up the Toolbar title
        toolbar = view.findViewById(R.id.toolbarchoose);
        tb_title_choose = view.findViewById(R.id.toolbar_title);
        tb_title_choose.setText(getResources().getString(R.string.choose_method));
        tb_title_choose.setTypeface(regular);




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
