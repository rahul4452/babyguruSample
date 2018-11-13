package com.example.poplify.baby_guru_sample.before_u_start;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Before_we_start_frag extends Fragment implements View.OnClickListener{

    ExpandableListAdapt listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Typeface regular,regularMon;
    private TextView it_also,tb_title_before;
    private Button start_btn;
    ExpandableListView expListView;
    TabLayout tabLayout ;
    FragmentManager fragmentManager;
    ImageView iv;
    ViewPager viewPager;
    SaveData saveData;
    BeforePagerAdapter beforePagerAdapter;
    private static final String TAG = "Before_we_start_frag";
    private BeforeYouStartResponse serverExistUser;

    public Before_we_start_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.before_we_start_frag, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        saveData = new SaveData(getContext());
        initBefore(view);
        setupBefore(view);
        //setupExpendableList(view);
        setupTablayout(view);

        callBeforeApi(view);
        // preparing list data
        //new GetList().execute();

        return view;
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
                serverExistUser = response.body();

                if (!success) {
                    switch (response.code()){
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
                    setServerResponse(serverExistUser);
                    Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BeforeYouStartResponse> call, Throwable t) {

            }

        });

    }

    private void setServerResponse(BeforeYouStartResponse serverExistUser) {

        //setting Toolbar Title
        tb_title_before.setText(serverExistUser.getBeforeYouStart().getTitle());


    }


    private void initBefore(View view) {
        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(),"Montserrat-Regular.otf");

        //toolbar Title
        tb_title_before = view.findViewById(R.id.toolbar_title);

        //tb_title_before.setTextSize(12);
        tb_title_before.setTypeface(regular);
    }

    private void setupBefore(View view) {


        it_also = view.findViewById(R.id.its_also);
        start_btn = view.findViewById(R.id.let_start_btn);
        //******button click to go to select child
        start_btn.setOnClickListener(this);


       // expListView =  view.findViewById(R.id.lvexpand);
        tabLayout = view.findViewById(R.id.tabLayoutBefore);

        viewPager =view.findViewById(R.id.pagerForBefore);


    }

    private void setupTablayout(View view) {
        tabLayout.addTab(tabLayout.newTab().setText("sleep coaching"));
        tabLayout.addTab(tabLayout.newTab().setText("guru's tips"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        beforePagerAdapter = new BeforePagerAdapter(fragmentManager,tabLayout.getTabCount());
        viewPager.setAdapter(beforePagerAdapter);
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

    //Checking which group is expanded and change the right indicator
    private void setupExpendableList(View view) {
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int i, long l) {
                iv = view.findViewById(R.id.indicator_view);

                if(parent.isGroupExpanded(i))
                {
                    iv.setSelected(false);
                }
                else
                {

                    iv.setSelected(true);
                }

                return false;
            }
        });
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

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Which method?");
        listDataHeader.add("What age can you start?");
        listDataHeader.add("When not to start?");
        listDataHeader.add("When do I start?");
        listDataHeader.add("Do I have to stay in forever for naps?");

        // Adding child data
        List<String> Which_method = new ArrayList<>();
        Which_method.add("If your little one is under 6 months we always recommend Cuddle, Calm & Continue. Over the 6 months picking them up can sometimes over stimulate them and make it worse so see how your little one responds and follow the Respond, Reassure and Repeat if more appropriate.");
        List<String> When_not = new ArrayList<>();
        When_not.add("Group2");
        List<String> What_age = new ArrayList<>();
        What_age.add("Group3");
        List<String> When_do = new ArrayList<>();
        When_do.add("Group4");
        List<String> Do_I_have = new ArrayList<>();
        Do_I_have.add("Group5");


        listDataChild.put(listDataHeader.get(0), Which_method); // Header, Child data
        listDataChild.put(listDataHeader.get(1), When_not);
        listDataChild.put(listDataHeader.get(2), What_age);
        listDataChild.put(listDataHeader.get(3), When_do);
        listDataChild.put(listDataHeader.get(4), Do_I_have);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.let_start_btn:
                replacementFragment(new Select_frag());
                break;
                default:
                    Toast.makeText(getContext(), "another Button", Toast.LENGTH_LONG).show();
        }
    }

    public  class GetList extends AsyncTask<String,Void,Void>
    {
        @Override
        protected void onPreExecute() {

            Toast.makeText(getContext(),"on Pre",Toast.LENGTH_SHORT);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Toast.makeText(getContext(),"on Post",Toast.LENGTH_SHORT);
            super.onPostExecute(aVoid);
        }


        @Override
        protected Void doInBackground(String... strings) {

            prepareListData();
            listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);
            return null;
        }
    }
}

