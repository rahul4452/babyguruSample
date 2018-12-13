package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ChildList;
import com.example.poplify.baby_guru_sample.adapter.FullDesc;
import com.example.poplify.baby_guru_sample.adapter.GlobalBus;
import com.example.poplify.baby_guru_sample.choose_method.ChooseMethodAdapter;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMethod extends Fragment {

    TextView toolbarTitle;
    RecyclerView chooseMethodRecycle;
    private int[] methodImage = {R.drawable.method_one_patch, R.drawable.method_second_patch};
    private Bundle bundle = new Bundle();
    private ChildProfileResponse.SleepCoachingDetails recycleData;
    private String header, selectMethodButton;
    private List<ChildProfileResponse.ChooseMethod> chooseMethodData;
    private ArrayList<ChildList> childLists = new ArrayList<>();
    private ArrayList<FullDesc> list = new ArrayList<>();

    private ChooseMethodRecycler chooseMethodRecycler;
    private LinearLayoutManager mLayoutManager;
    private Typeface regular, regularMon;
    private FragmentManager fragmentManager;

    public ChooseMethod() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      GlobalBus.getBus().register(this);

        bundle = getArguments();
        if (bundle != null) {
            recycleData = (ChildProfileResponse.SleepCoachingDetails) bundle.getSerializable("changeMethodDetails");
            header = bundle.getString("headerTitle");
            selectMethodButton = bundle.getString("selectMethod");
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
        return view;
    }

    private void setupChooseMethod(View view) {
        toolbarTitle.setText(header);
        toolbarTitle.setTypeface(regularMon);

        //Setup Recycler View For Choose Method

        chooseMethodRecycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        chooseMethodRecycle.setLayoutManager(mLayoutManager);

        chooseMethodData = recycleData.getChooseMethods();
        for (int i = 0; i < chooseMethodData.size(); i++) {
            ChildList methodList = new ChildList();
            //FullDesc fullDesc = new FullDesc();
           // fullDesc.setData((ArrayList<String>) chooseMethodData.get(i).getFullDescription());
            methodList.setFullDescription((ArrayList<String>) chooseMethodData.get(i).getFullDescription());
            methodList.setListenToSam(chooseMethodData.get(i).getListenToSam());
            methodList.setMethodTypeLabe(chooseMethodData.get(i).getLabel());
            methodList.setChooseMethpdTitle(chooseMethodData.get(i).getTitle());
            methodList.setReadMore(chooseMethodData.get(i).getReadMore());
            methodList.setShortDescription(chooseMethodData.get(i).getShortDescription());

            if (childLists.size() != chooseMethodData.size()) {
                childLists.add(methodList);
               // list.add(fullDesc);
            }
        }



        chooseMethodRecycler = new ChooseMethodRecycler(getContext(), childLists, methodImage);
        chooseMethodRecycle.setAdapter(chooseMethodRecycler);
        chooseMethodRecycler.notifyDataSetChanged();


    }

    private void intilisingChooseMehod(View view) {
        toolbarTitle = view.findViewById(R.id.toolbar_title);

        chooseMethodRecycle = view.findViewById(R.id.choose_Method_Recycler);
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");
    }


    private class ChooseMethodRecycler extends RecyclerView.Adapter<ChooseMethodRecycler.ChooseMethodHolder> {


        Context context;
        ArrayList<ChildList> chooseMethodList;
        //ArrayList<FullDesc> fullDescs;
        ChildList chooseList;
        ArrayList<FullDesc> fullDesc =new ArrayList<>();
        int[] image;

        public ChooseMethodRecycler(Context context, ArrayList<ChildList> childLists, int[] methodImage) {
            this.context = context;
            this.chooseMethodList = childLists;
            this.image = methodImage;
            //this.fullDescs = list;
        }

        public class ChooseMethodHolder extends RecyclerView.ViewHolder {
            TextView methodName, readMoreBtn, methodType;
            MarkdownView webViewChoose;
            ImageView methodImage;
            ConstraintLayout constraintLayout;

            public ChooseMethodHolder(@NonNull View itemView) {

                super(itemView);
                methodType = itemView.findViewById(R.id.methodTypeTxt);
                methodType.setTypeface(regular);

                methodName = itemView.findViewById(R.id.headerChoose);
                methodName.setTypeface(regularMon);

                readMoreBtn = itemView.findViewById(R.id.read_more_tv);
                readMoreBtn.setTypeface(regular);

                webViewChoose = itemView.findViewById(R.id.webViewChoose);

                methodImage = itemView.findViewById(R.id.method_image);
                constraintLayout = itemView.findViewById(R.id.constraintLayoutChoose);
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

            int imageID = image[i];
            chooseList = chooseMethodList.get(i);
            //fullDesc = fullDescs.get(i);
            try {
                holder.methodImage.setImageResource(imageID);
                holder.methodName.setText(chooseList.getChooseMethpdTitle());
                holder.readMoreBtn.setText(chooseList.getReadMore());
                holder.webViewChoose.loadData(chooseList.getShortDescription(), "text/html", "UTF-8");
                holder.methodType.setText(chooseList.getMethodTypeLabe());
                final Bundle selectMethod = new Bundle();
                holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (i)
                        {
                            case 0:
//                                selectMethod.putParcelableArrayList("parceList",fullDesc);
                                //selectMethod.putParcelableArrayList("fullDesc", chooseMethodList.get(i).getFullDescription());
                                selectMethod.putString("header",chooseMethodList.get(i).getMethodTypeLabe());
                                selectMethod.putString("methodName",chooseMethodList.get(i).getChooseMethpdTitle());
                                selectMethod.putString("listenToSam",chooseMethodList.get(i).getListenToSam());

                                Show_Choose_Method show_choose_methodKt = new Show_Choose_Method();
                                show_choose_methodKt.setArguments(selectMethod);
                                replacementFragment(show_choose_methodKt);


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
