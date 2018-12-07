package com.example.poplify.baby_guru_sample.user_Profile;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.child_profile.Full_child_profile;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.GetUserDetails;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.poplify.baby_guru_sample.forgot_pwd.Forgot_pwd.fragmentManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class User_Profile_frag extends Fragment implements View.OnClickListener {


    private Typeface regular, regularMon;
    private TextView detailTextView, tvParentName, tvParentRelation, tvParentEmail, tvMyChildren, tvChngePwd,
            tvContactUs, tvLegalArgu, tvLogoutUser, tvFirstChildName, tvSecondChildName, tvInvite, tvFaq, tvNewsLetter, tvAppDemo, toolbarUser;
    private TextView editDetailbtn, chngePwdBtn, newsLetterBtn;
    private SaveData saveData;
    FragmentManager fragmentManager;
    ProgressBar progressBar;
    CircleImageView addFirstChildIV;
    RecyclerView.Adapter UserChildAdapter;
    ImageButton btnContactUs, btnLegalAgree, btnLogout, btnappDemo, btnFaq;
    CircleImageView parentImage;
    private GetUserDetails serverExistUser;
    Bundle bun = new Bundle();
    private GetUserDetails.UserLabels labels;
    private RecyclerView recycleviewChild;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ChildList> childLists = new ArrayList<>();
    private LinearLayout firstLayout;


    public User_Profile_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.user__profile_frag, container, false);
        init();
        setupUi(view);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");
        String getUserUrl = "/user_details";

        progressBar.setVisibility(View.VISIBLE);

        Call<GetUserDetails> responseCall = service.getUserDetail(token_header, email_header, getUserUrl);
        responseCall.enqueue(new Callback<GetUserDetails>() {
            @Override
            public void onResponse(Call<GetUserDetails> call, Response<GetUserDetails> response) {

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
                    progressBar.setVisibility(View.GONE);
                    saveData.save("changePwd", serverExistUser.getUserLabels().getButtons().getChangePassword());
                    setServerResponse(serverExistUser);
//                    Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserDetails> call, Throwable t) {

                progressBar.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }


    //******************************** ALL Methods ******************************//

    private void init() {

        //setting up the fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");
        fragmentManager = getFragmentManager();
        saveData = new SaveData(getContext());


    }

    private void setupUi(View view) {

        detailTextView = view.findViewById(R.id.userDetailTv);
        detailTextView.setTypeface(regular);


        toolbarUser = view.findViewById(R.id.toolbar_title);
        toolbarUser.setTypeface(regular);

        tvParentName = view.findViewById(R.id.parentNameTv);
        tvParentName.setTypeface(regularMon);

        tvParentRelation = view.findViewById(R.id.parentRelationTV);
        tvParentRelation.setTypeface(regularMon);

        tvParentEmail = view.findViewById(R.id.parentEmailTV);
        tvParentEmail.setTypeface(regularMon);

        tvMyChildren = view.findViewById(R.id.myChildrenTV);
        tvMyChildren.setTypeface(regular);

        tvChngePwd = view.findViewById(R.id.chngPwdTV);
        tvChngePwd.setTypeface(regular);

        tvContactUs = view.findViewById(R.id.contactUsTV);
        tvContactUs.setTypeface(regular);

        tvLegalArgu = view.findViewById(R.id.legalAgreeTV);
        tvLegalArgu.setTypeface(regular);

        tvLogoutUser = view.findViewById(R.id.logoutTv);
        tvLogoutUser.setTypeface(regular);

        tvFirstChildName = view.findViewById(R.id.addFirstChildTv);
        tvFirstChildName.setTypeface(regularMon);

        tvAppDemo = view.findViewById(R.id.appDemo);
        tvAppDemo.setTypeface(regularMon);

        tvNewsLetter = view.findViewById(R.id.newslettertv);
        tvNewsLetter.setTypeface(regularMon);

        tvFaq = view.findViewById(R.id.faqtv);
        tvFaq.setTypeface(regularMon);

//        tvSecondChildName = view.findViewById(R.id.secondChildName);
//        tvSecondChildName.setTypeface(regularMon);


        tvInvite = view.findViewById(R.id.txtInvite);
        tvInvite.setTypeface(regular);


        recycleviewChild = view.findViewById(R.id.my_recycler_view_child);
        recycleviewChild.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleviewChild.setLayoutManager(mLayoutManager);

        //Image View
        addFirstChildIV = view.findViewById(R.id.addChildFromUser);

        //addSecondChildIV = view.findViewById(R.id.addSecondChildFromUser);


        //Simple button
        editDetailbtn = view.findViewById(R.id.detailEditParent);
        editDetailbtn.setTypeface(regularMon);

        chngePwdBtn = view.findViewById(R.id.chngPwdBtn);
        chngePwdBtn.setTypeface(regular);

        newsLetterBtn = view.findViewById(R.id.newsletterBtn);
        newsLetterBtn.setTypeface(regular);

        //Image Button
        btnContactUs = view.findViewById(R.id.contactUsBtn);

        btnFaq = view.findViewById(R.id.faqBtn);

        btnappDemo = view.findViewById(R.id.appDemoBtn);

        btnLegalAgree = view.findViewById(R.id.legalAgreeBtn);

        btnLogout = view.findViewById(R.id.logoutBtn);
        btnLogout.setOnClickListener(this);

        //Image For parent
        parentImage = view.findViewById(R.id.parent_profile_pic);


        //Progressbar
        progressBar = view.findViewById(R.id.getUserProcessingPb);

        //Linear Layout
        firstLayout = view.findViewById(R.id.firstchildImageLayout);
//        secondLayout = view.findViewById(R.id.secondChildLayout);


    }


    private void setServerResponse(final GetUserDetails serverExistUser) {

        labels = serverExistUser.getUserLabels();

        //toolbar
        toolbarUser.setText(serverExistUser.getUserLabels().getHeader().getUserProfile());

        //detail label
        detailTextView.setText(labels.getLabels().getDetails());
        editDetailbtn.setText(labels.getButtons().getEdit());
        editDetailbtn.setOnClickListener(this);


        //Parent Information
        tvParentName.setText(serverExistUser.getUser().getName());

        List<GetUserDetails.Relation> realtionList = serverExistUser.getRelations();

        for (GetUserDetails.Relation relation : realtionList) {
            if (relation.getSelected().equals(true) && tvParentRelation.getText() == "") {
                tvParentRelation.setText(relation.getName());
            } else {
                // Toast.makeText(getContext(),relation.getName(),Toast.LENGTH_SHORT);
            }
        }

        //Parent Image
        String imageName = serverExistUser.getUser().getImageUrl();

        try {
            Glide.with(this)
                    .load(imageName)
                    .into(parentImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //parent Email
        tvParentEmail.setText(saveData.get("login_email"));


        saveData.save("addChild", labels.getLabels().getAddChild());

        //My children Layout
        tvMyChildren.setText(labels.getLabels().getMyChildren());


        final List<GetUserDetails.Child> childrenList = serverExistUser.getChildren();

        for (int i = 0; i < childrenList.size(); i++) {
            ChildList list_of_child = new ChildList();
            list_of_child.setChildImageUrl(childrenList.get(i).getImageUrl());
            list_of_child.setChildName(childrenList.get(i).getName());
            list_of_child.setChildId(childrenList.get(i).getId());

            if(childLists.size()!=2) {
                childLists.add(list_of_child);
            }
        }


            UserChildAdapter = new UserChildAdapter(getContext(), childLists);


        if (childrenList.size() != 2) {
            firstLayout.setVisibility(View.VISIBLE);
            recycleviewChild.setAdapter(UserChildAdapter);

        } else {
            firstLayout.setVisibility(View.GONE);
            recycleviewChild.setAdapter(UserChildAdapter);

        }


        tvInvite.setText(labels.getButtons().getSendInvite());

        //Last Layout
        chngePwdBtn.setText(labels.getButtons().getEdit());
        chngePwdBtn.setOnClickListener(this);
        tvChngePwd.setText(labels.getButtons().getChangePassword());
        tvContactUs.setText(labels.getButtons().getContactUs());
        tvLegalArgu.setText(labels.getButtons().getLegalAgreement());
        tvLogoutUser.setText(labels.getButtons().getLogout());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.logoutBtn:
                Toast.makeText(getContext(), "Logout Not Happen", Toast.LENGTH_LONG).show();
                break;
            case R.id.detailEditParent:
                replacementFragment(new Basic_detail_frag());
                break;
            case R.id.chngPwdBtn:
                Intent chngpwd = new Intent(getContext(), ChangePassword.class);
                startActivity(chngpwd);
                break;
            default:
                Toast.makeText(getContext(), "another button", Toast.LENGTH_LONG).show();
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

    public class UserChildAdapter extends RecyclerView.Adapter<UserChildAdapter.ViewHolder> {


        // List<Data> list = Collections.emptyList();

        Context contxt;
        ArrayList<ChildList> childNameList;
        ChildList childList;
        private static final String TAG = "UserChildAdapter";

        // Provide a suitable constructor (depends on the kind of dataset).


        public UserChildAdapter(Context contxt, ArrayList<ChildList> childId) {
            this.contxt = contxt;
            this.childNameList = childId;
        }

        // Provide a reference to the views for each data item.
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView imageUrls;
            public TextView childName, childId;

            public ViewHolder(View v) {
                super(v);
                imageUrls = v.findViewById(R.id.showChild);
                childName = v.findViewById(R.id.childname);
                childId = v.findViewById(R.id.getChildId);
            }
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.show_child_user_profile, parent, false);

            // Set the view's size, margins, paddings and layout parameters.

            regular = Typeface.createFromAsset(contxt.getAssets(), "Comfortaa_Regular.ttf");
            regularMon = Typeface.createFromAsset(contxt.getAssets(), "Montserrat-Regular.otf");

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            childList = childNameList.get(position);
            try {
                if (childList.getChildImageUrl() != null) {
                    Glide.with(getContext())
                            .load(childList.getChildImageUrl())
                            .into(holder.imageUrls);
                } else {
                    holder.imageUrls.setImageResource(R.drawable.childbg);
                }
                holder.childName.setText(childList.getChildName());
            } catch (Exception e) {
                e.printStackTrace();
            }


            holder.imageUrls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (position) {
                        case 0:
                            bun.putInt("childId", childNameList.get(position).getChildId());
                            Full_child_profile full_child_profile = new Full_child_profile();
                            full_child_profile.setArguments(bun);
                            replacementFragment(full_child_profile);
                            break;

                        case 1:
                            bun.putInt("childId", childNameList.get(position).getChildId());
                            Full_child_profile full_child_profile2 = new Full_child_profile();
                            full_child_profile2.setArguments(bun);
                            replacementFragment(full_child_profile2);
                            break;
                    }


                }

            });

        }

        @Override
        public int getItemCount() {
            // Hackish: This is set to INT_MAX so that user has a lot of free space to move around to
            // make the view appear as infinite. This should be improved.
            return childLists.size();
//        return mDataset.length;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            // Hackish: Set to the middle position so that user can scroll in either direction for a
            // long time. This eventually needs to be improved to wrap better.
            recyclerView.scrollToPosition(Integer.MAX_VALUE / 2);
        }
    }
}
