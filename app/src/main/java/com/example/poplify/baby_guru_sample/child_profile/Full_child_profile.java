package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.child_profile.ChooseMethod;
import com.example.poplify.baby_guru_sample.child_profile.showGraph.Graph_child;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Full_child_profile extends Fragment implements View.OnClickListener {

    TextView detail_txt, child_name_txt, baby_born_txt, baby_born_date_txt, baby_txt, baby_gen_txt, baby_history_txt, coachindMethodTv;
    TextView edit_btn, see_graph, changeMethodBtn, selectMethodTv;
    LinearLayout changeMthodLayout;
    CircleImageView childPic;
    TextView toolbarTitle;
    Typeface bold, regular, regularMon;
    List<String> session, session_date, session_time, session_time_dif;
    RecyclerView recyclerView;
    Child child_adapt;
    private String SERIALIZED_KEY = "childId";
    RecyclerView.LayoutManager layoutManager;
    SaveData saveData;
    Bundle bundle = new Bundle();
    Bundle bundle1 = new Bundle();
    private Integer childId;
    private ChildProfileResponse serverData;
    private TextView bornTxt;
    private Boolean genderSelected;
    private Integer genderId;
    private android.support.v4.app.FragmentManager fragmentManager;


    public Full_child_profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        assert bundle != null;
        childId = bundle.getInt(SERIALIZED_KEY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_child_profile_frag, container, false);


        saveData = new SaveData(getContext());

        initializer(view);//setting up textview,button and Font
        intilizeList();

        requestToServer(childId, view);


        recyclerView = view.findViewById(R.id.child_recyclerView);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        //call the recycler adapter
        child_adapt = new Child(session, session_date, session_time, session_time_dif);
        recyclerView.setAdapter(child_adapt);

        return view;
    }

    private void requestToServer(Integer childFirstId, final View view) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");

        Call<ChildProfileResponse> responseCall = service.showChildFile(token_header, email_header, childFirstId);
        responseCall.enqueue(new Callback<ChildProfileResponse>() {
            @Override
            public void onResponse(Call<ChildProfileResponse> call, Response<ChildProfileResponse> response) {

                boolean success = response.isSuccessful();
                serverData = response.body();
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
                    setDataToView(serverData);


                }
            }

            @Override
            public void onFailure(Call<ChildProfileResponse> call, Throwable t) {
                call.cancel();

            }
        });


    }

    private void setDataToView(ChildProfileResponse serverData) {

        ChildProfileResponse.Header header = serverData.getChildLabels().getChildProfileLabels().getHeader();
        ChildProfileResponse.Labels labels = serverData.getChildLabels().getChildProfileLabels().getLabels();
        ChildProfileResponse.Buttons buttons = serverData.getChildLabels().getChildProfileLabels().getButtons();
        ChildProfileResponse.Child childDetails = serverData.getChild();

        //toolbar
        toolbarTitle.setText(header.getChildProfile());

        //First Layout
        detail_txt.setText(labels.getDetails());

        edit_btn.setText(buttons.getEdit());


        //Change Method Layout
        coachindMethodTv.setText(labels.getCoachingMethod());

        changeMethodBtn.setText(buttons.getChange());


        //SleepCoaching History Layout
        baby_history_txt.setText(labels.getSleepCoachingHistory());
        see_graph.setText(buttons.getGraph());


        //Setting Up the Child Profile
        if (childDetails.getName() != null) {
            child_name_txt.setText(childDetails.getName());
        }


        bornTxt.setText(labels.getBornOn());

        baby_born_date_txt.setText(childDetails.getDob());

        baby_born_txt.setText(labels.getBaby());

        List<ChildProfileResponse.Gender> genderChild = serverData.getChild().getGender();

        for (ChildProfileResponse.Gender gender : genderChild) {
            //genderId = gender.getId();
            genderSelected = gender.getSelected();
            if (genderSelected.equals(true) && gender.getName() != null) {
                baby_gen_txt.setText(gender.getName());
                bundle1.putInt("genderId", gender.getId());
            } else {
                baby_gen_txt.setText(gender.getDefaultName());
            }
        }

        if (childDetails.getImageUrl() != null) {
            String childImagePic = childDetails.getImageUrl();
            try {
                Glide.with(this)
                        .load(childImagePic)
                        .into(childPic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void intilizeList() {
        session = new ArrayList<String>();
        session.add("Session 1");
        session.add("Session 2");
        session.add("Session 3");
        session.add("Session 4");
        session.add("Session 4");

        session.size();

        session_date = new ArrayList<String>();
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");

        session_time = new ArrayList<String>();
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");

        session_time_dif = new ArrayList<String>();
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");


    }

    private void initializer(View view) {

        fragmentManager = getFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        //Button
        edit_btn = view.findViewById(R.id.child_edit_btn);
        edit_btn.setTypeface(regular);
        edit_btn.setOnClickListener(this);

        see_graph = view.findViewById(R.id.see_graph_btn);
        see_graph.setTypeface(regular);
        see_graph.setOnClickListener(this);

        //Method Layout
        coachindMethodTv = view.findViewById(R.id.coaching_method_tv);
        coachindMethodTv.setTypeface(regularMon);

        changeMethodBtn = view.findViewById(R.id.chnge_method_btn);
        changeMethodBtn.setTypeface(regularMon);
        changeMethodBtn.setOnClickListener(this);

        selectMethodTv = view.findViewById(R.id.select_method_tv);
        selectMethodTv.setTypeface(regularMon);

        toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(regular);

        //Change Method Layout


        //Child Layout
        childPic = view.findViewById(R.id.childProfilePicDetails);

        child_name_txt = view.findViewById(R.id.childran_name);
        child_name_txt.setTypeface(regularMon);

        baby_born_txt = view.findViewById(R.id.baby_with_gender);
        baby_born_txt.setTypeface(regularMon);

        bornTxt = view.findViewById(R.id.born_txt);
        bornTxt.setTypeface(regularMon);

        baby_born_date_txt = view.findViewById(R.id.born_date);
        baby_born_date_txt.setTypeface(regularMon);


        baby_gen_txt = view.findViewById(R.id.gender_txt);
        baby_gen_txt.setTypeface(regularMon);


        baby_history_txt = view.findViewById(R.id.sleep_history);
        baby_history_txt.setTypeface(regular);

        //all TextViews

        detail_txt = view.findViewById(R.id.detail_child);
        detail_txt.setTypeface(regular);


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        int btn = view.getId();

        switch (btn) {
            case R.id.child_edit_btn:
                bundle1.putInt("childIdforUpdate", childId);
                bundle1.putString("childName", child_name_txt.getText().toString().trim());
                bundle1.putString("childDob", baby_born_date_txt.getText().toString().trim());
                bundle1.putSerializable("labels", serverData.getChildLabels().getChildProfileLabels());
                if (serverData.getChild().getImageUrl() != null) {
                    bundle1.putString("childimageUrl", serverData.getChild().getImageUrl());
                }
                Add_child_tab_frag updateChild = new Add_child_tab_frag();
                updateChild.setArguments(bundle1);
                //replacementFragment(updateChild);
                ((Bottom_tabs) Objects.requireNonNull(getActivity())).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, updateChild, true);
                break;

            case R.id.see_graph_btn:
                Intent graph = new Intent(getContext(), Graph_child.class);
                startActivity(graph);
                break;

            case R.id.chnge_method_btn:
                Bundle changeMethodDAta = new Bundle();
                changeMethodDAta.putInt("childId", serverData.getChild().getId());
                ChooseMethod cm = new ChooseMethod();
                cm.setArguments(changeMethodDAta);
                if (saveData.getBoolean("userProfile")) {
                    ((Bottom_tabs) Objects.requireNonNull(getActivity())).pushFragments(Bottom_tabs.TAB_USER_PROFILE, cm, true);
                   // saveData.remove("userProfile");
                } else if(saveData.getBoolean("fullChildProfile")){
                    ((Bottom_tabs) Objects.requireNonNull(getActivity())).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, cm, true);
                }
                //  replacementFragment(cm);
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

    private class Child extends RecyclerView.Adapter<Child.Child_Holder> {


        List<String> lsession, lsession_date, lsession_time, lsession_time_dif;

        public Child(List<String> lsession, List<String> lsession_date, List<String> lsession_time, List<String> lsession_time_dif) {
            this.lsession = lsession;
            this.lsession_date = lsession_date;
            this.lsession_time = lsession_time;
            this.lsession_time_dif = lsession_time_dif;
        }

        public class Child_Holder extends RecyclerView.ViewHolder {
            TextView tv_session, tv_child_time, tv_date, tv_diff_time;

            public Child_Holder(@NonNull View itemView) {

                super(itemView);
                tv_session = itemView.findViewById(R.id.session_tv);
                tv_child_time = itemView.findViewById(R.id.child_date_tv);
                tv_date = itemView.findViewById(R.id.main_time_tv);
                tv_diff_time = itemView.findViewById(R.id.child_time_tv);
            }
        }


        @NonNull
        @Override
        public Child_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_profile_recycler, viewGroup, false);
            Child_Holder holder = new Child_Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Child_Holder child_holder, int i) {

            String s1, s2, s3, s4;
            s1 = lsession.get(i);
            s2 = lsession_time.get(i);
            s3 = lsession_date.get(i);
            s4 = lsession_time_dif.get(i);

            child_holder.tv_session.setText(s1);
            child_holder.tv_child_time.setText(s2);
            child_holder.tv_date.setText(s3);
            child_holder.tv_diff_time.setText(s4);

        }


        @Override
        public int getItemCount() {
            return lsession.size();
        }
    }
}
