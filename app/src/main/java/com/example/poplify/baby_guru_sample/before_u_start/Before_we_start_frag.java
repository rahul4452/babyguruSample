package com.example.poplify.baby_guru_sample.before_u_start;


import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.R;

import com.example.poplify.baby_guru_sample.adapter.InternetConnection;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Before_we_start_frag extends Fragment {


    Typeface regular, regularMon;
    private TextView it_also, tb_title_before;
    private Button start_btn;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;

    ViewPager viewPager;
    SaveData saveData;
    BeforePagerAdapter beforePagerAdapter;
    private static final String TAG = "Before_we_start_frag";
    private BeforeYouStartResponse serverExistUser1;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    private CoordinatorLayout mainLayout;
    private LinearLayout btnlayout;


    public Before_we_start_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.before_we_start_frag, container, false);
        fragmentManager = getChildFragmentManager();
        saveData = new SaveData(getContext());

        initBefore(view);


        //  if (InternetConnection.checkConnection(Objects.requireNonNull(getContext()))) {


//        } else {
//            TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content), getResources().getString(R.string.internetMsg), TSnackbar.LENGTH_LONG);
//            snackbar.setActionTextColor(Color.WHITE);
//            View snackbarView = snackbar.getView();
//            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
//            TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//            textView.setTextColor(Color.WHITE);
//            textView.setGravity(Gravity.CENTER_HORIZONTAL);
//          //  Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
//            snackbar.show();
//       }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callBeforeApi(view);
    }

    private void callBeforeApi(final View view) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");

        String getBeforeUrl = "/get_before_start_details";

        Call<BeforeYouStartResponse> responseCall = service.getBeforeDetail(token_header, email_header, getBeforeUrl);
        responseCall.enqueue(new Callback<BeforeYouStartResponse>() {
            @Override
            public void onResponse(Call<BeforeYouStartResponse> call, Response<BeforeYouStartResponse> response) {

                boolean success = response.isSuccessful();
                serverExistUser1 = response.body();

                if (!success) {
                    switch (response.code()) {
                        case 500:
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);
                                View snackbarView = snackbar.getView();
                                snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                snackbar.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                } else {

                    // saveData.save("changePwd",serverExistUser.getUserLabels().getButtons().getChangePassword());


                    setServerResponse(serverExistUser1);


//                    Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BeforeYouStartResponse> call, Throwable t) {
                //call.cancel();
                if (t instanceof SocketTimeoutException) {
                    if (InternetConnection.checkConnection((getContext()))) {
                        Snackbar snack = Snackbar.make(view.findViewById(android.R.id.content), getResources().getString(R.string.internetMsg), Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        snack.show();
                    } else {
                        Snackbar snack = Snackbar.make(view.findViewById(android.R.id.content), "Problem with server", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        snack.show();
                    }
                }
                // Toast.makeText(getContext(), "not login", Toast.LENGTH_LONG).show();
            }

        });

    }


    private void setServerResponse(BeforeYouStartResponse serverExistUser) {

        try {
            //setting Toolbar Title
            tb_title_before.setText(serverExistUser.getBeforeYouStart().getTitle());
            tabLayout.addTab(tabLayout.newTab().setText(serverExistUser.getSleepCoachingLabels().getHeader().getSleepCoaching()));
            tabLayout.addTab(tabLayout.newTab().setText(serverExistUser.getSleepCoachingLabels().getHeader().getGuruTips()));

            setCustomFont();

            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            beforePagerAdapter = new BeforePagerAdapter(fragmentManager, tabLayout.getTabCount(), serverExistUser);

            viewPager.setAdapter(beforePagerAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


            start_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Bottom_tabs)getActivity()).pushFragments(Bottom_tabs.TAB_TIMER, new Select_frag(),true);
                    //replacementFragment(new Select_frag());
                }
            });

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        // setupTablayout();


    }


    public void setCustomFont() {

        try {
            ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
            int tabsCount = vg.getChildCount();

            for (int j = 0; j < tabsCount; j++) {
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

                int tabChildsCount = vgTab.getChildCount();

                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        //Put your font in assests folder
                        //assign name of the font here (Must be case sensitive)
                        ((TextView) tabViewChild).setTypeface(regularMon);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initBefore(View view) {

        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(), "Montserrat-Regular.otf");

        //collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbar);
        appBarLayout = view.findViewById(R.id.app_bar_layout);

        //********************************************************
        //=========  Appbar layout hiding bottom shadow ==========
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.1f));
            appBarLayout.setStateListAnimator(stateListAnimator);
        }

        //*********************************************************
        //toolbar Title
        tb_title_before = view.findViewById(R.id.toolbar_title);

        mainLayout = view.findViewById(R.id.cord);
        btnlayout = view.findViewById(R.id.letStartBtnLayout);


        //*********************************************************
        // tb_title_before.setTextSize(12);
        tb_title_before.setTypeface(regular);

        tabLayout = view.findViewById(R.id.tabLayoutBefore);

        viewPager = view.findViewById(R.id.pagerForBefore);
        start_btn = view.findViewById(R.id.let_start_btn);
        start_btn.setTypeface(regular);
    }


    //Checking which group is expanded and change the right indicator

    private void replacementFragment(Fragment fragment) {
        String backstack = null;
        String fragmentTag = null;

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fm.popBackStackImmediate(backstack, 0);

        Log.d("", "replacementFragment: fragmentPopped" + fragmentPopped);
        try {
            if (!fragmentPopped) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

