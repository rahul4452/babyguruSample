package com.example.poplify.baby_guru_sample.before_u_start;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.add_New_Baby_tab.Add_child_tab_frag;
import com.example.poplify.baby_guru_sample.child_profile.ChooseMethod;
import com.example.poplify.baby_guru_sample.choose_method.Choose_method_frag;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.ChildrenResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;
import com.example.poplify.baby_guru_sample.user_Profile.User_Profile_frag;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Select_frag extends Fragment implements View.OnClickListener {


    TextView child_1, screenTitle;
    WebView selectBaby;
    Typeface regular, regularMon;
    CircleImageView select_child;
    FragmentManager fragmentManager;
    RecyclerView selectFragRecycle;
    SaveData saveData;
    private ChildrenResponse existChildren;
    private String childIndex;
    private List<ChildrenResponse.Child> childrenList;
    private ArrayList<ChildList> childLists = new ArrayList<>();
    private RecyclerView.Adapter selectChildAdapter;
    private LinearLayout linearLayout, mainLayout;
    private LinearLayoutManager mLayoutManager;

    public Select_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_frag, container, false);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        saveData = new SaveData(getContext());
        initSelectFrag(view);

        setupSelectFrag(view);
        sendCallToServer(view);


        return view;
    }

    private void setupSelectFrag(View view) {

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


    public void sendCallToServer(View view) {
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


        childIndex = existChildren.getChildIndexLabel().getSleepCoaching();

        //top text
//        selectBaby.addStyleSheet(new Github());
//        selectBaby.loadMarkdown(childIndex);

        selectBaby.loadData(childIndex,"text/html","UTF-8");

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

        selectChildAdapter = new SelectChildAdapter(getContext(), childLists);

        if (childLists.size() != 2) {
            linearLayout.setVisibility(View.VISIBLE);
            selectFragRecycle.setAdapter(selectChildAdapter);

        } else {
            linearLayout.setVisibility(View.GONE);
            selectFragRecycle.setAdapter(selectChildAdapter);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_child:
                replacementFragment(new Add_child_tab_frag());
        }
    }

    public class SelectChildAdapter extends RecyclerView.Adapter<SelectChildAdapter.ViewHolder> {


        // List<Data> list = Collections.emptyList();

        Context contxt;
        ArrayList<ChildList> childFullList;
        ChildList childList;
        private static final String TAG = "SelectChildAdapter";

        // Provide a suitable constructor (depends on the kind of dataset).


        public SelectChildAdapter(Context contxt, ArrayList<ChildList> selectChildList) {
            this.contxt = contxt;
            this.childFullList = selectChildList;
        }

        // Provide a reference to the views for each data item.
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
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


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.showexistingchild, parent, false);

            // Set the view's size, margins, paddings and layout parameters.

            regular = Typeface.createFromAsset(contxt.getAssets(), "Comfortaa_Regular.ttf");
            regularMon = Typeface.createFromAsset(contxt.getAssets(), "Montserrat-Regular.otf");

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            childList = childFullList.get(position);
            try {
                if (childList.getChildImageUrl() != null) {
                    Glide.with(getContext())
                            .load(childList.getChildImageUrl())
                            .into(holder.imageUrls);
                } else {
                    holder.imageUrls.setImageResource(R.drawable.childbg);
                }
                holder.childName.setText(childList.getChildName());
                holder.childName.setTypeface(regularMon);
            } catch (Exception e) {
                e.printStackTrace();
            }


            holder.imageUrls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (position) {
                        case 0:
                            Bundle bundle = new Bundle();
                            bundle.putInt("childId", childFullList.get(position).getChildId());
                            if (!childFullList.get(position).isSavedMethod()) {
                                Timer_frag timer_frag = new Timer_frag();
                                timer_frag.setArguments(bundle);
                                replacementFragment(timer_frag);
                            } else {
                                ChooseMethod selectMethod = new ChooseMethod();
                                selectMethod.setArguments(bundle);
                                replacementFragment(selectMethod);
                            }

                            break;

                        case 1:
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("childId", childFullList.get(position).getChildId());
                            if (!childFullList.get(position).isSavedMethod()) {
                                Timer_frag timer_frag = new Timer_frag();
                                timer_frag.setArguments(bundle2);
                                replacementFragment(timer_frag);
                            } else {
                                ChooseMethod selectMethod = new ChooseMethod();
                                selectMethod.setArguments(bundle2);
                                replacementFragment(selectMethod);
                            }
                            break;
                    }
                }

            });
        }

        @Override
        public int getItemCount() {
            // Hackish: This is set to INT_MAX so that user has a lot of free space to move around to
            // make the view appear as infinite. This should be improved.
            return childFullList.size();
//        return mDataset.length;
        }

    }


    public void showHideFragment(final Fragment fragment) {

        assert getFragmentManager() != null;
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        fragTransaction.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);

        if (fragment.isHidden()) {
            fragTransaction.show(fragment);
            Log.d("hidden", "Show");
        } else {
            fragTransaction.hide(fragment);
            Log.d("Shown", "Hide");
        }

        fragTransaction.commit();
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
