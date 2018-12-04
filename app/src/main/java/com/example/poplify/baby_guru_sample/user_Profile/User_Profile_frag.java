package com.example.poplify.baby_guru_sample.user_Profile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.GetUserDetails;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
            tvContactUs, tvLegalArgu, tvLogoutUser, tvFirstChildName, tvSecondChildName, tvInvite;
    private Button editDetailbtn, chngePwdBtn;
    private SaveData saveData;
    FragmentManager fragmentManager;
    ProgressBar progressBar;
    ImageView addFirstChildIV, addSecondChildIV, inviteIconIV;

    ImageButton btnContactUs, btnLegalAgree, btnLogout;
    CircleImageView parentImage, firstChildImage, secondChildImage;
    private GetUserDetails serverExistUser;

    private GetUserDetails.UserLabels labels;


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
        if (token_header == null && email_header == null) {
            token_header = saveData.get("signToken");
            email_header = saveData.get("email");
        }

        String getUserUrl = "/user_details";

        progressBar.setVisibility(View.VISIBLE);

        Call<GetUserDetails> responseCall = service.getUserDetail(token_header, email_header, getUserUrl);
        responseCall.enqueue(new Callback<GetUserDetails>() {
            @Override
            public void onResponse(Call<GetUserDetails> call, Response<GetUserDetails> response) {

                progressBar.setVisibility(View.GONE);

                boolean success = response.isSuccessful();
                serverExistUser = response.body();

                if (!success) {
                    switch (response.code()){
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

                    saveData.save("changePwd",serverExistUser.getUserLabels().getButtons().getChangePassword());
                    setServerResponse(serverExistUser);
//                    Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserDetails> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
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


        tvSecondChildName = view.findViewById(R.id.secondChildName);
        tvSecondChildName.setTypeface(regularMon);


        tvInvite = view.findViewById(R.id.txtInvite);


        //Image View
        inviteIconIV = view.findViewById(R.id.inviteIcon);

        addFirstChildIV = view.findViewById(R.id.addChildFromUser);

        addSecondChildIV = view.findViewById(R.id.addSecondChildFromUser);


        //Simple button
        editDetailbtn = view.findViewById(R.id.detailEditParent);
        editDetailbtn.setTypeface(regularMon);

        chngePwdBtn = view.findViewById(R.id.chngPwdBtn);
        chngePwdBtn.setTypeface(regular);


        //Image Button
        btnContactUs = view.findViewById(R.id.contactUsBtn);


        btnLegalAgree = view.findViewById(R.id.legalAgreeBtn);


        btnLogout = view.findViewById(R.id.logoutBtn);
        btnLogout.setOnClickListener(this);

        //Image For parent
        parentImage = view.findViewById(R.id.parent_profile_pic);

        firstChildImage = view.findViewById(R.id.childPicInUser);
        firstChildImage.setVisibility(View.GONE);

        secondChildImage = view.findViewById(R.id.secondChildPicInUser);


        //Progressbar
        progressBar = view.findViewById(R.id.getUserProcessingPb);


    }


    private void setServerResponse(GetUserDetails serverExistUser) {

        labels = serverExistUser.getUserLabels();
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


        //My children Layout
        tvMyChildren.setText(labels.getLabels().getMyChildren());

        List<GetUserDetails.Child> childrenList = serverExistUser.getChildren();
        if (childrenList.size() == 0) {
            addFirstChildIV.setVisibility(View.VISIBLE);
            addFirstChildIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replacementFragment(new Add_child_tab_frag());
                }
            });
            tvFirstChildName.setVisibility(View.VISIBLE);
            tvFirstChildName.setText(labels.getLabels().getAddChild());

        } else if (childrenList.size() == 1) {

            if (addFirstChildIV.getVisibility() == View.VISIBLE) {
                addFirstChildIV.setVisibility(View.GONE);
            }

            firstChildImage.setVisibility(View.VISIBLE);
            for (GetUserDetails.Child child : childrenList) {

            }
            addSecondChildIV.setVisibility(View.VISIBLE);
            addSecondChildIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replacementFragment(new Add_child_tab_frag());
                }
            });
            tvSecondChildName.setVisibility(View.VISIBLE);
            tvSecondChildName.setText(labels.getLabels().getAddChild());
        } else if (childrenList.size() == 2) {
            if (addFirstChildIV.getVisibility() == View.VISIBLE && addSecondChildIV.getVisibility() == View.VISIBLE) {
                addFirstChildIV.setVisibility(View.GONE);
                addSecondChildIV.setVisibility(View.GONE);
            }

            firstChildImage.setVisibility(View.VISIBLE);
            secondChildImage.setVisibility(View.VISIBLE);

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
}
