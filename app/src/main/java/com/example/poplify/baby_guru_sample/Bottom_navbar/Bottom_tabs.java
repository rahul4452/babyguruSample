package com.example.poplify.baby_guru_sample.Bottom_navbar;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.InternetConnection;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.firstTab.beforeYouStart.Before_we_start_frag;
import com.example.poplify.baby_guru_sample.firstTab.Select_frag;
import com.example.poplify.baby_guru_sample.child_profile.SelectChildSeeProfile;
import com.example.poplify.baby_guru_sample.thirdTab.Choose_method_frag;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.UserDetailAddResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.example.poplify.baby_guru_sample.secondTab.User_Profile_frag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bottom_tabs extends AppCompatActivity {


    private HashMap<String, Stack<Fragment>> mStacks;
    public static final String TAB_TIMER = "tabTimer";
    public static final String TAB_USER_PROFILE = "tabUser";
    public static final String TAB_CRYING_SCALE = "tabCrying";
    public static final String TAB_CHILD_PROFILE = "tabChild";
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    Typeface regular, regularMon;
    SaveData saveData;
    private BeforeYouStartResponse serverExistUser1;
    private String mCurrentTab;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tabs_activity);
        fragmentManager = getSupportFragmentManager();
        saveData = new SaveData(getApplicationContext());
        regular = Typeface.createFromAsset(getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.otf");

        getBeforeYouDetails();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

        mStacks = new HashMap<>();
        mStacks.put(TAB_TIMER, new Stack<Fragment>());
        mStacks.put(TAB_USER_PROFILE, new Stack<Fragment>());
        mStacks.put(TAB_CRYING_SCALE, new Stack<Fragment>());
        mStacks.put(TAB_CHILD_PROFILE, new Stack<Fragment>());

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        bottomNavigationView.setSelectedItemId(R.id.tab1);

        //Setting fonts

        UserDetailAddResponse.User user = new UserDetailAddResponse().new User();

        final View activityRootView = findViewById(R.id.bottom_layout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();

                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    private void navigationBackground(int actionId) {
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem item = bottomNavigationView.getMenu().getItem(i);
            boolean isChecked = item.getItemId() == actionId;
            item.setChecked(isChecked);

            if (i == 0 && isChecked) {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab1);
            } else if (i == 1 && isChecked == true) {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab2);
            } else if (i == 2 && isChecked == true) {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab3);
            } else if (i == 3 && isChecked == true) {
                bottomNavigationView.setItemBackgroundResource(R.drawable.tab4);
            }
        }
    }


    int count;


    Fragment selectFrag = null;


    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            navigationBackground(menuItem.getItemId());
            try {
                switch (menuItem.getItemId()) {

                    case R.id.tab1:
                        //viewFragment(new Paytab_frag(),"Frag_pay");
//                        if(!serverExistUser1.getHasChild()) {
////                            selectFrag = new Before_we_start_frag();
//
//                        }else {
//                            selectFrag = new Select_frag();
//                        }

                        selectedTab(TAB_TIMER);
                        break;

                    case R.id.tab2:
                        //viewFragment(new Add_child_tab_frag(),"Frag_other");
                        //selectFrag = new User_Profile_frag();
                        selectedTab(TAB_USER_PROFILE);
                        break;

                    case R.id.tab3:
                        // viewFragment(new Paytab_frag(),"Frag_other");
                        //                       selectFrag = new Choose_method_frag();
                        selectedTab(TAB_CRYING_SCALE);
                        break;

                    case R.id.tab4:
                        // viewFragment(new Add_child_tab_frag(),"Frag_other");
//                        selectFrag = new SelectChildSeeProfile();
                        selectedTab(TAB_CHILD_PROFILE);
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //replacementFragment(selectFrag);

            return true;
        }
    };

    private void selectedTab(String tabId) {

        mCurrentTab = tabId;

        if (mStacks.get(tabId).size() == 0) {
            /*
             *    First time this tab is selected. So add first fragment of that tab.
             *    Dont need animation, so that argument is false.
             *    We are adding a new fragment which is not present in stack. So add to stack is true.
             */
            if (tabId.equals(TAB_TIMER)) {
                if (serverExistUser1.getHasChild()) {
                    pushFragments(tabId, new Select_frag(), true);
                } else {
                    pushFragments(tabId, new Before_we_start_frag(), true);
                }

            } else if (tabId.equals(TAB_USER_PROFILE)) {
                pushFragments(tabId, new User_Profile_frag(), true);
            } else if (tabId.equals(TAB_CRYING_SCALE)) {
                pushFragments(tabId, new Choose_method_frag(), true);
            } else if (tabId.equals(TAB_CHILD_PROFILE)) {
                pushFragments(tabId, new SelectChildSeeProfile(), true);
            }
        } else {
            /*
             *    We are switching tabs, and target tab is already has atleast one fragment.
             *    No need of animation, no need of stack pushing. Just show the target fragment
             */
            pushFragments(tabId, mStacks.get(tabId).lastElement(), false);
        }
    }

    public void pushFragments(String tabId, Fragment fragment, boolean shouldAdd) {
        if (shouldAdd)
            mStacks.get(tabId).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_container_navbar, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
//        count = getSupportFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getSupportFragmentManager().popBackStackImmediate();
//
//        }

        if (mStacks.get(mCurrentTab).size() == 1) {
            // We are already showing first fragment of current tab, so when back pressed, we will finish this activity..
            finish();
            return;
        }

        /* Goto previous fragment in navigation stack of this tab */
        popFragments();

    }

    public void popFragments() {
        Fragment fragment = mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

        /*pop current fragment from stack.. */
        mStacks.get(mCurrentTab).pop();

        /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_container_navbar, fragment);
        ft.commit();
    }

    private void gotoFragment(Fragment selectedFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, selectedFragment);
        fragmentTransaction.commit();
    }

    private void getBeforeYouDetails() {
        {

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
                                    TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                    TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                    textView.setTextColor(Color.WHITE);
                                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                    Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                    snackbar.show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }
                    } else {

                        assert serverExistUser1 != null;
                        if (!serverExistUser1.getHasChild()) {
                            bottomNavigationView.setSelectedItemId(R.id.tab1);
                        } else {
                            selectedTab(TAB_TIMER);
                        }

                    }
                }

                @Override
                public void onFailure(Call<BeforeYouStartResponse> call, Throwable t) {
                    //call.cancel();
                    if (t instanceof SocketTimeoutException) {
                        if (InternetConnection.checkConnection((getApplicationContext()))) {
                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.internetMsg), Snackbar.LENGTH_LONG);
                            View view = snack.getView();
                            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                            params.gravity = Gravity.TOP;
                            view.setLayoutParams(params);
                            snack.show();
                        } else {
                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Problem with server", Snackbar.LENGTH_LONG);
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
    }


    private void replacementFragment(Fragment fragment) {
        String backstack = null;
        String fragmentTag = null;

        int count = getSupportFragmentManager().getBackStackEntryCount();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack, 0);
        try {
            if (fragmentPopped != true) {
                ft.add(R.id.fragment_container_navbar, fragment, fragmentTag).commit();
            }
            //ft.addToBackStack(backstack)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



