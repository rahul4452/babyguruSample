package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.before_u_start.Select_frag;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.ChildrenResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectChildSeeProfile extends Fragment {

    SaveData saveData;
    TextView child_1, screenTitle;
    WebView selectBaby;
    Typeface regular, regularMon;
    CircleImageView select_child;
    FragmentManager fragmentManager;
    RecyclerView selectFragRecycle;
    private ChildrenResponse existChildren;
    private String childIndex;
    private List<ChildrenResponse.Child> childrenList;
    private ArrayList<ChildList> childLists = new ArrayList<>();
    private RecyclerView.Adapter seeChildAdapter;
    private LinearLayout linearLayout, mainLayout;
    private LinearLayoutManager mLayoutManager;

    public SelectChildSeeProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_frag, container, false);

        saveData = new SaveData(getContext());
        initSelectFrag(view);


        sendCallToServer(view);

        return view;
    }

    private void sendCallToServer(View view) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");

        Call<ChildrenResponse> responseCall = service.getSelectChild(token_header, email_header);
        responseCall.enqueue(new Callback<ChildrenResponse>() {
            @Override
            public void onResponse(Call<ChildrenResponse> call, Response<ChildrenResponse> response) {

                boolean success = response.isSuccessful();
                existChildren = response.body();

                if (!success) {
                    switch (response.code()) {
                        case 404:
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                TSnackbar snackbar = TSnackbar.make(getView().findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
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
                    //progressBar.setVisibility(View.GONE);
                    //saveData.save("changePwd", serverExistUser.getUserLabels().getButtons().getChangePassword());


                    getServerResponse(existChildren);


                    // Toast.makeText(getContext(), "Details updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChildrenResponse> call, Throwable t) {

                call.cancel();
                //progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getServerResponse(ChildrenResponse existChildren) {
        childIndex = existChildren.getChildIndexLabel().getChildIndex();


        selectBaby.loadData(childIndex, "text/html", "UTF-8");

        //set Header of screen
        screenTitle.setText(existChildren.getChildIndexLabel().getSelectChild());

        childrenList = existChildren.getChild();

        for (int i = 0; i < childrenList.size(); i++) {
            ChildList list_of_child = new ChildList();
            list_of_child.setChildImageUrl(childrenList.get(i).getImageUrl());
            list_of_child.setChildName(childrenList.get(i).getName());
            list_of_child.setChildId(childrenList.get(i).getId());
            list_of_child.setSavedMethod(childrenList.get(i).getSavedMethod());
            if (childLists.size() != 2) {
                childLists.add(list_of_child);
            }
        }

        seeChildAdapter = new SeeChildAdapter(getContext(), childLists);

        if (childLists.size() != 2) {
            linearLayout.setVisibility(View.VISIBLE);
            selectFragRecycle.setAdapter(seeChildAdapter);

        } else {
            linearLayout.setVisibility(View.GONE);
            selectFragRecycle.setAdapter(seeChildAdapter);
        }
    }

    class SeeChildAdapter extends RecyclerView.Adapter<SeeChildAdapter.ViewHolder> {

        Context contxt;
        ArrayList<ChildList> childFullList;
        ChildList childList;

        public SeeChildAdapter(Context contxt, ArrayList<ChildList> childFullList) {
            this.contxt = contxt;
            this.childFullList = childFullList;

        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView imageUrls;
            public TextView childName, childId;

            public ViewHolder(View v) {
                super(v);
                imageUrls = v.findViewById(R.id.existingChildImage);
                childName = v.findViewById(R.id.existingChildName);
                //   childId = v.findViewById(R.id.getChildId);
            }
        }


        @NonNull
        @Override
        public SeeChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.showexistingchild, viewGroup, false);

            // Set the view's size, margins, paddings and layout parameters.

            regular = Typeface.createFromAsset(contxt.getAssets(), "Comfortaa_Regular.ttf");
            regularMon = Typeface.createFromAsset(contxt.getAssets(), "Montserrat-Regular.otf");

            return new ViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

            childList = childFullList.get(i);
            try {
                if (childList.getChildImageUrl() != null) {
                    Glide.with(getContext())
                            .load(childList.getChildImageUrl())
                            .into(viewHolder.imageUrls);
                } else {
                    viewHolder.imageUrls.setImageResource(R.drawable.childbg);
                }
                viewHolder.childName.setText(childList.getChildName());
                viewHolder.childName.setTypeface(regularMon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            viewHolder.imageUrls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (i) {
                        case 0:
                            Bundle bundle = new Bundle();
                            bundle.putInt("childId", childFullList.get(i).getChildId());
                            Full_child_profile full_child_profile = new Full_child_profile();
                            full_child_profile.setArguments(bundle);
                            saveData.save("fullChildProfile",true);
                            ((Bottom_tabs)getActivity()).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, full_child_profile,true);
                            //replacementFragment(full_child_profile);
                            break;

                        case 1:
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("childId", childFullList.get(i).getChildId());
                            Full_child_profile full_child_profile2 = new Full_child_profile();
                            full_child_profile2.setArguments(bundle2);
                            saveData.save("fullChildProfile",true);
                            ((Bottom_tabs)getActivity()).pushFragments(Bottom_tabs.TAB_CHILD_PROFILE, full_child_profile2,true);
                           // replacementFragment(full_child_profile2);
                            break;
                    }
                }

            });
        }


        @Override
        public int getItemCount() {
            return childFullList.size();
        }
    }

    private void initSelectFrag(View view) {
        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(), "Montserrat-Regular.otf");
        fragmentManager = getActivity().getSupportFragmentManager();

        //textview for setting child name
        child_1 = view.findViewById(R.id.child1_name);

        mainLayout = view.findViewById(R.id.firstchildImageLayout);

        //setting up the header
        screenTitle = view.findViewById(R.id.toolbar_title);
        screenTitle.setTypeface(regular);

        selectBaby = view.findViewById(R.id.selct_your);

        //button for start session
        select_child = view.findViewById(R.id.add_child);

        //Recycler View
        selectFragRecycle = view.findViewById(R.id.my_recycler_view_child_SelectFrag);
        selectFragRecycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        selectFragRecycle.setLayoutManager(mLayoutManager);

        //Linear Layout
        linearLayout = view.findViewById(R.id.imageLayoutSelectChild);

        child_1.setTypeface(regularMon);
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
