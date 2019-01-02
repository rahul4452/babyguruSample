package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.Events;
import com.example.poplify.baby_guru_sample.adapter.GlobalBus;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.MyMessageResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowSelectMethodFrag extends Fragment implements View.OnClickListener {


    private String header;
    private String methodName, samWord, readMore;
    TextView methodTitle, wordOfSam, toolbarTitle;
    Typeface regular, regularMon;
    List<String> tempList;
    Button selectMethodBtn;
    Events.paasArrayList list;
    private FragmentManager fragmentManager;
    private RecyclerView recycler;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<String> listingProperty;
    private ArrayList<ChildList> listData;
    private RecyclerView.Adapter selectMethodRecycler;
    private int methodId;
    private int childId;
    private SaveData saveData;

    public ShowSelectMethodFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        GlobalBus.getBus().register(this);
        Bundle bundle;
        bundle = getArguments();

        if (!bundle.equals(null)) {
            childId = bundle.getInt("childId");
            header = bundle.getString("header");
            methodName = bundle.getString("methodName");
            samWord = bundle.getString("listenToSam");
            readMore = bundle.getString("readMore");
            methodId = bundle.getInt("methodId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show__choose__method, container, false);

        list = GlobalBus.getBus().getStickyEvent(Events.paasArrayList.class);

        initUi(view);
        setupUi();

        return view;
    }

    private void setupUi() {
        wordOfSam.setText(samWord);
        methodTitle.setText(methodName);
        toolbarTitle.setText(header);
        selectMethodBtn.setTypeface(regular);
        selectMethodBtn.setOnClickListener(this);
        recycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(mLayoutManager);
        listingProperty = list.getChildFullDescription();
        List<String> kas = new ArrayList<>();
        kas.addAll(listingProperty);

        selectMethodRecycler = new SelectMethodRecycler(getContext(), kas);
        recycler.setAdapter(selectMethodRecycler);
        selectMethodRecycler.notifyDataSetChanged();

    }

    private void initUi(View view) {

        fragmentManager = getFragmentManager();
        saveData = new SaveData(getContext());

        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(regular);

        methodTitle = view.findViewById(R.id.blueHeaderChoose);
        methodTitle.setTypeface(regular);

        wordOfSam = view.findViewById(R.id.listenSamChoose);
        wordOfSam.setTypeface(regularMon);

        selectMethodBtn = view.findViewById(R.id.select_method_btn);

        recycler = view.findViewById(R.id.open_choose_recycler);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_method_btn:
                callToServer(childId, v, methodId);
        }
    }

    private void callToServer(int childId, final View view, int methodId) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");


        //JsonObject root = new JsonObject();
        JsonObject user = new JsonObject();

        try {
            user.addProperty("method_id", methodId);
            //root.add("",user);
        } catch (JsonIOException w) {
            w.printStackTrace();
        }


        Call<MyMessageResponse> calling = service.setPreferMethod(token_header, email_header, childId, user);

        calling.enqueue(new Callback<MyMessageResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<MyMessageResponse> call, Response<MyMessageResponse> response) {
                boolean success = response.isSuccessful();

                MyMessageResponse serverMessage = response.body();
                ResponseBody errorMsg = response.errorBody();
                if (!success) {
                    try {
                        //JsonObject changePwdObj = new JsonObject().get(response.body().toString()).getAsJsonObject();
                        assert errorMsg != null;
                        TSnackbar snackbar = TSnackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), serverMessage.getMessage(), TSnackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // JSONObject jObjError = new JSONObject(response.body().getString("message"));
                        //String errorString = jObjError.get("error").toString();
                        assert serverMessage != null;
                        TSnackbar snackbar = TSnackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), serverMessage.getMessage(), TSnackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        snackbar.show();



                        assert getFragmentManager() != null;
                        getActivity().onBackPressed();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyMessageResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private class SelectMethodRecycler extends RecyclerView.Adapter<SelectMethodRecycler.SelectMethodHolder> {

        Context context;
        List<String> fullDescData;

        public SelectMethodRecycler(Context context, List<String> listData) {
            this.context = context;
            this.fullDescData = listData;
        }

        public class SelectMethodHolder extends RecyclerView.ViewHolder {
            TextView methodName;
            // ImageView methodImage;

            public SelectMethodHolder(@NonNull View itemView) {
                super(itemView);
                methodName = itemView.findViewById(R.id.fullDescriptionList);
                methodName.setTypeface(regular);
            }
        }

        @NonNull
        @Override
        public SelectMethodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_method, viewGroup, false);
            SelectMethodHolder holder = new SelectMethodHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SelectMethodHolder holder, final int i) {
            String data = fullDescData.get(i);
            try {

                holder.methodName.setText(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return fullDescData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        GlobalBus.getBus().unregister(this);
    }

}
