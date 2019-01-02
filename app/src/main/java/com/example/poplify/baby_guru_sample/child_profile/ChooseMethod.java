package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.Events;
import com.example.poplify.baby_guru_sample.adapter.FullDesc;
import com.example.poplify.baby_guru_sample.adapter.GlobalBus;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import br.tiagohm.markdownview.MarkdownView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMethod extends Fragment {

    TextView toolbarTitle;
    RecyclerView chooseMethodRecycle;
    private int[] methodImage = {R.drawable.method_one_patch, R.drawable.method_second_patch};
    private Bundle bundle = new Bundle();

    private ArrayList<ChildList> childLists = new ArrayList<>();
    private ArrayList<FullDesc> list = new ArrayList<>();

    private ChooseMethodRecycler chooseMethodRecycler;
    private LinearLayoutManager mLayoutManager;
    private Typeface regular, regularMon;
    private FragmentManager fragmentManager;
    private int childIdMethod;
    private SaveData saveData;
    private ChildProfileResponse serverData;
    private List<ChildProfileResponse.ChooseMethod> chooseMethodData;

    public ChooseMethod() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bundle = getArguments();

//            recycleData = (ChildProfileResponse.SleepCoachingDetails) bundle.getSerializable("changeMethodDetails");
//            header = bundle.getString("headerTitle");
//            selectMethodButton = bundle.getString("selectMethod");

        if (bundle != null) {

            childIdMethod = bundle.getInt("childId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_method, container, false);
        fragmentManager = getFragmentManager();

        intilisingChooseMehod(view);
        setupChooseMethod(view);
        requestToServer(childIdMethod, view);

        return view;
    }

    private void setupChooseMethod(View view) {
        toolbarTitle.setTypeface(regularMon);

        //Setup Recycler View For Choose Method
    }

    private void intilisingChooseMehod(View view) {
        toolbarTitle = view.findViewById(R.id.toolbar_title);
        saveData = new SaveData(getContext());
        chooseMethodRecycle = view.findViewById(R.id.choose_Method_Recycler);

        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");
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
                    setData(serverData.getSleepCoachingDetails());
                    toolbarTitle.setText(serverData.getSleepCoachingLabels().getHeader().getChooseMethod());

                }
            }

            @Override
            public void onFailure(Call<ChildProfileResponse> call, Throwable t) {
                call.cancel();

            }
        });


    }

    private void setData(ChildProfileResponse.SleepCoachingDetails sleepCoachingDetails) {

        chooseMethodData = sleepCoachingDetails.getChooseMethods();
        for (int i = 0; i < chooseMethodData.size(); i++) {
            ChildList methodList = new ChildList();
            // FullDesc fullDesc = new FullDesc();
            methodList.setFullDescription((ArrayList<String>) chooseMethodData.get(i).getFullDescription());
            methodList.setListenToSam(chooseMethodData.get(i).getListenToSam());
            methodList.setMethodTypeLabe(chooseMethodData.get(i).getLabel());
            methodList.setChooseMethpdTitle(chooseMethodData.get(i).getTitle());
            methodList.setReadMore(chooseMethodData.get(i).getReadMore());
            methodList.setShortDescription(chooseMethodData.get(i).getShortDescription());
            //methodList.setChildId(chooseMethodData.get(i).getId());


            if (childLists.size() != chooseMethodData.size()) {
                childLists.add(methodList);
                // list.add(fullDesc);
            }
        }
        chooseMethodRecycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        chooseMethodRecycle.setLayoutManager(mLayoutManager);
        chooseMethodRecycler = new ChooseMethodRecycler(getContext(), childLists, methodImage);
        chooseMethodRecycle.setAdapter(chooseMethodRecycler);
        chooseMethodRecycler.notifyDataSetChanged();
    }


    private class ChooseMethodRecycler extends RecyclerView.Adapter<ChooseMethodRecycler.ChooseMethodHolder> {

        Context context;
        ArrayList<ChildList> chooseMethodList;
        //ArrayList<FullDesc> fullDescs;
        ChildList chooseList;
        // ArrayList<FullDesc> fullDesc =new ArrayList<>();
        int[] image;

        public ChooseMethodRecycler(Context context, ArrayList<ChildList> childLists, int[] methodImage) {
            this.context = context;
            this.chooseMethodList = childLists;
            this.image = methodImage;
            //this.fullDescs = list;
        }

        public class ChooseMethodHolder extends RecyclerView.ViewHolder {
            TextView methodName, readMoreBtn, methodType;
            WebView webViewChoose;
            ImageView methodImage;
            FrameLayout constraintLayout;

            public ChooseMethodHolder(@NonNull View itemView) {

                super(itemView);
                methodType = itemView.findViewById(R.id.methodTypeTxt);
                methodType.setTypeface(regular);

                methodName = itemView.findViewById(R.id.headerChoose);
                methodName.setTypeface(regularMon);

                readMoreBtn = itemView.findViewById(R.id.read_more_tv);
                readMoreBtn.setTypeface(regular);

                webViewChoose = itemView.findViewById(R.id.webviewChoose);

                //methodImage = itemView.findViewById(R.id.method_image);
                constraintLayout = itemView.findViewById(R.id.constraintLayoutChoose);
                constraintLayout.bringToFront();
            }
        }

        @NonNull
        @Override
        public ChooseMethodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.change_method_recycler, viewGroup, false);
            ChooseMethodHolder holder = new ChooseMethodHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ChooseMethodHolder holder, final int i) {


            chooseList = chooseMethodList.get(i);
            //fullDesc = fullDescs.get(i);
            try {
                //holder.methodImage.setImageResource(imageID);

                holder.methodName.setText(chooseList.getChooseMethpdTitle());
                holder.readMoreBtn.setText(chooseList.getReadMore());
                holder.webViewChoose.loadData(chooseList.getShortDescription(), "text/html", "UTF-8");
                holder.methodType.setText(chooseList.getMethodTypeLabe());
                final Bundle selectMethod = new Bundle();
                holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (i) {
                            case 0:
                                GlobalBus.getBus().postSticky(new Events.paasArrayList(chooseMethodList.get(i).getFullDescription()));
                                selectMethod.putInt("childId", childIdMethod);
                                selectMethod.putString("header", chooseMethodList.get(i).getMethodTypeLabe());
                                selectMethod.putString("methodName", chooseMethodList.get(i).getChooseMethpdTitle());
                                selectMethod.putString("listenToSam", chooseMethodList.get(i).getListenToSam());
                                selectMethod.putInt("methodId", chooseMethodData.get(i).getId());
                                // selectMethod.putString("readMore", chooseMethodList.get(i).getReadMore());
                                //GlobalBus.getBus().post(chooseMethodList);
                                ShowSelectMethodFrag showSelectMethodFrag = new ShowSelectMethodFrag();
                                showSelectMethodFrag.setArguments(selectMethod);

                                //For BackPressed
                                if (saveData.getBoolean("timer")) {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_TIMER, showSelectMethodFrag, true);
                                    saveData.remove("timer");
                                } else if (saveData.getBoolean("userProfile")) {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_USER_PROFILE, showSelectMethodFrag, true);
                                    saveData.remove("userProfile");
                                } else {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, showSelectMethodFrag, true);
                                    saveData.remove("fullChildProfile");
                                }
                                //replacementFragment(showSelectMethodFrag);
                                break;
                            case 1:
                                GlobalBus.getBus().postSticky(new Events.paasArrayList(chooseMethodList.get(i).getFullDescription()));
                                selectMethod.putInt("childId", chooseMethodList.get(i).getChildId());
                                selectMethod.putString("header", chooseMethodList.get(i).getMethodTypeLabe());
                                selectMethod.putString("methodName", chooseMethodList.get(i).getChooseMethpdTitle());
                                selectMethod.putString("listenToSam", chooseMethodList.get(i).getListenToSam());
                                selectMethod.putInt("methodId", chooseMethodData.get(i).getId());
                                // selectMethod.putString("readMore", chooseMethodList.get(i).getReadMore());
                                //GlobalBus.getBus().post(chooseMethodList);
                                ShowSelectMethodFrag showSelectMethodFrag2 = new ShowSelectMethodFrag();
                                showSelectMethodFrag2.setArguments(selectMethod);
                                //For BackPressed
                                if (saveData.getBoolean("timer")) {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_TIMER, showSelectMethodFrag2, true);
                                    saveData.remove("timer");
                                } else if (saveData.getBoolean("userProfile")) {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_USER_PROFILE, showSelectMethodFrag2, true);
                                    saveData.remove("userProfile");
                                } else {
                                    ((Bottom_tabs) getActivity()).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, showSelectMethodFrag2, true);
                                    saveData.remove("fullChildProfile");
                                }
                                //replacementFragment(showSelectMethodFrag2);
                                break;
                        }
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        public int getItemCount() {
            return chooseMethodList.size();
        }
    }


    @org.greenrobot.eventbus.Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.paasArrayList syncStatusMessage) {
        //Toast.makeText(getActivity(), syncStatusMessage.getChildFullDescription().get(1), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        GlobalBus.getBus().unregister(this);
    }
}
