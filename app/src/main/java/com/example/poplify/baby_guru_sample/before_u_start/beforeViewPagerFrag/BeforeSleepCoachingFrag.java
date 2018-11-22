package com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;

import org.xml.sax.XMLReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeSleepCoachingFrag extends Fragment {

    ExpandableListAdapt listAdapter;
    ExpandableListView expListView;
    BeforeYouStartResponse.BeforeYouStart beforeYouStart;
    List<String> listDataHeader;
    HashMap<String,List<String>>  listDataChild;
    private ImageView iv;
    private Bundle bundle;
    private String SERIALIZED_KEY = "before_you_start";
    private MarkdownView markdownView;
    private List<BeforeYouStartResponse.Detail> beforeDetails;
    private List<String> listChildData;


    public BeforeSleepCoachingFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //***********************************************************//
        //****************** Get Server response from another fragment*******************//
        bundle = getArguments();
        beforeYouStart = (BeforeYouStartResponse.BeforeYouStart) bundle.getSerializable(SERIALIZED_KEY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.before_sleep_coaching_frag, container, false);
        markdownView = view.findViewById(R.id.markDownView);

        try{
            markdownView.addStyleSheet(new Github());
            markdownView.loadMarkdown(beforeYouStart.getDescription());
            expListView = view.findViewById(R.id.lvexpand);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ViewCompat.setNestedScrollingEnabled(expListView,true);
//            }

            setupExpendableList(view);
        }catch (Exception e )
        {
         e.printStackTrace();
        }


        return view;
    }


    private void setupExpendableList(View view) {
        prepareListData();
        listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int i, long l) {
                iv = view.findViewById(R.id.indicator_view);

                if (parent.isGroupExpanded(i)) {
                    iv.setSelected(false);
                } else {
                    iv.setSelected(true);
                }
                return false;
            }
        });
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listChildData = new ArrayList<String>();
        listDataChild = new HashMap<>();

        try {
            beforeDetails = beforeYouStart.getDetails();


            for (BeforeYouStartResponse.Detail detailList : beforeDetails)
            {

                listDataHeader.add(detailList.getQuestion());
                listChildData.add(detailList.getAnswers());
                listDataChild.put(listDataHeader.get(detailList.getId()),listChildData);

                //listDataChild.put(listDataHeader.get(detailList.getId()),detailList.getAnswers())
                //listDataChild.put(listDataHeader,detailList.getAnswers());
            }




            // Adding child data
//        listDataHeader.add("Which method?");
//        listDataHeader.add("What age can you start?");
//        listDataHeader.add("When not to start?");
//        listDataHeader.add("When do I start?");
//        listDataHeader.add("Do I have to stay in forever for naps?");
//
//
//
//            // Adding child data
//            List<String> Which_method = new ArrayList<>();
//            Which_method.add("If your little one is under 6 months we always recommend Cuddle, Calm & Continue. Over the 6 months picking them up can sometimes over stimulate them and make it worse so see how your little one responds and follow the Respond, Reassure and Repeat if more appropriate.");
//            List<String> When_not = new ArrayList<>();
//            When_not.add("Group2");
//            List<String> What_age = new ArrayList<>();
//            What_age.add("Group3");
//            List<String> When_do = new ArrayList<>();
//            When_do.add("Group4");
//            List<String> Do_I_have = new ArrayList<>();
//            Do_I_have.add("Group5");
//
//
//            listDataChild.put(listDataHeader.get(0), Which_method); // Header, Child data
//            listDataChild.put(listDataHeader.get(1), When_not);
//            listDataChild.put(listDataHeader.get(2), What_age);
//            listDataChild.put(listDataHeader.get(3), When_do);
//            listDataChild.put(listDataHeader.get(4), Do_I_have);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
