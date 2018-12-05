package com.example.poplify.baby_guru_sample.Bottom_navbar;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.Bottom_Tabs.Paytab_frag;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.before_u_start.Before_we_start_frag;
import com.example.poplify.baby_guru_sample.child_profile.Full_child_profile;
import com.example.poplify.baby_guru_sample.choose_method.Choose_method_frag;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.UserDetailAddResponse;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;
import com.example.poplify.baby_guru_sample.user_Profile.Basic_detail_frag;
import com.example.poplify.baby_guru_sample.user_Profile.User_Profile_frag;

public class Bottom_tabs extends AppCompatActivity {


    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    Typeface regular,regularMon;
    SaveData saveData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tabs_activity);
        fragmentManager = getSupportFragmentManager();
        saveData = new SaveData(getApplicationContext());


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        fragmentManager.beginTransaction().add(R.id.fragment_container_navbar,new Before_we_start_frag()).commit();


        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");
        UserDetailAddResponse.User user = new UserDetailAddResponse().new User();



        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

        final View activityRootView = findViewById(R.id.bottom_layout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();

                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }else{
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                }
            }
        });


    }





    private void navigationBackground(int actionId)
    {
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++)
        {
            MenuItem item = bottomNavigationView.getMenu().getItem(i);
            boolean isChecked = item.getItemId() == actionId;
            item.setChecked(isChecked);

            if(i==0&&isChecked==true)
            {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab1);
            }
            else if(i==1&&isChecked==true)
            {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab2);
            }
            else if(i==2&&isChecked==true)
            {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab3);
            }
            else if(i==3&&isChecked==true)
            {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab4);
            }
        }
    }


    int goback;

    @Override
    public void onBackPressed() {



        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStackImmediate();

        }

    }

    Fragment selectFrag=null;

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


            navigationBackground(menuItem.getItemId());


            try {


                switch (menuItem.getItemId()) {

                    case R.id.tab1:
                        //viewFragment(new Paytab_frag(),"Frag_pay");
                        selectFrag = new Before_we_start_frag();
                        break;

                    case R.id.tab2:
                        //viewFragment(new Add_child_tab_frag(),"Frag_other");
                            selectFrag = new User_Profile_frag();

                        break;

                    case R.id.tab3:
                        // viewFragment(new Paytab_frag(),"Frag_other");
                        selectFrag = new Choose_method_frag();
                        break;

                    case R.id.tab4:
                        // viewFragment(new Add_child_tab_frag(),"Frag_other");
                        selectFrag = new Full_child_profile();

                        break;

                    default:

                        break;
                }
            }
            catch (Exception e )
            {
                e.printStackTrace();
            }
            replacementFragment(selectFrag);

            return true;
        }
    };

    private void replacementFragment(Fragment fragment)
    {
        String backstack = null;
        String fragmentTag = null;

        int count = getSupportFragmentManager().getBackStackEntryCount();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack,0);
        try {
            if (fragmentPopped!=true) {
                ft.add(R.id.fragment_container_navbar, fragment, fragmentTag).commit();
            }
            //ft.addToBackStack(backstack)
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}



