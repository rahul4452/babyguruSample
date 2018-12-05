package com.example.poplify.baby_guru_sample.choose_method;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_method_frag extends Fragment {

    TextView tb_title_choose;
    Typeface regular, regularMon;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    private SaveData saveData;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CryingScaleResponse serverExistUser;
    private ChooseMethodAdapter choosePagerAdapter;

    public Choose_method_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_method_frag, container, false);

        saveData = new SaveData(getContext());


        init(view);

        getResponseForCryingScale(view);
        setup(serverExistUser);




        return view;
    }

    private void sendDatatoAdapter(CryingScaleResponse serverExistUser) {

        //Setting Header Of Activity
        tb_title_choose.setText(serverExistUser.getCryingScaleLabel().getHeader().getCryingScale());


        tabLayout.addTab(tabLayout.newTab().setText(serverExistUser.getCryingScaleLabel().getHeader().getMethod1()));
        tabLayout.addTab(tabLayout.newTab().setText(serverExistUser.getCryingScaleLabel().getHeader().getMethod2()));

        setCustomFont();

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        choosePagerAdapter = new ChooseMethodAdapter(fragmentManager,tabLayout.getTabCount(),serverExistUser);

        viewPager.setAdapter(choosePagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
    }

    private void setup(CryingScaleResponse serverExistUser) {
        tb_title_choose.setTypeface(regular);


    }

    private void init(View view) {
        fragmentManager = getChildFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(), "Montserrat-Regular.otf");

        //Setting up the Toolbar
        toolbar = view.findViewById(R.id.toolbarchoose);
        tb_title_choose = view.findViewById(R.id.toolbar_title);

        tabLayout = view.findViewById(R.id.tableLayoutChooseMethod);
        viewPager = view.findViewById(R.id.pagerChooseMethod);

    }


    private void getResponseForCryingScale(final View view) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");

        String getUserUrl = "/get_crying_scale";


        Call<CryingScaleResponse> responseCall = service.getCryingScale(token_header, email_header,getUserUrl);
        responseCall.enqueue(new Callback<CryingScaleResponse>() {
            @Override
            public void onResponse(Call<CryingScaleResponse> call, Response<CryingScaleResponse> response) {


                boolean success = response.isSuccessful();
                serverExistUser = response.body();

                if (!success) {
                    switch (response.code()) {
                        case 400:
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

                    sendDatatoAdapter(serverExistUser);
                    //setServerResponse(serverExistUser);
                    //Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CryingScaleResponse> call, Throwable t) {

                call.cancel();
            }
        });
    }

    public void setCustomFont() {

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
    }

    private void replacementFragment(Fragment fragment) {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack, 0);

        Log.d("", "replacementFragment: fragmentPopped" + fragmentPopped);
        try {
            if (fragmentPopped != true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
