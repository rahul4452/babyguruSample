package com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.XMLReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
    HashMap<String, List<String>> listDataChild;
    private ImageView iv;
    private TextView it_also;
    private Bundle bundle;
    private String SERIALIZED_KEY = "before_you_start";
    private MarkdownView markdownView;
    private List<BeforeYouStartResponse.Detail> beforeDetails;
    private List<String> listExpendData;
    private int lastExpandedPosition = -1;

    // Inflate the layout for this fragment
    View view;

    public BeforeSleepCoachingFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        beforeYouStart = (BeforeYouStartResponse.BeforeYouStart) bundle.getSerializable(SERIALIZED_KEY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.before_sleep_coaching_frag, container, false);

        markdownView = view.findViewById(R.id.markDownView);
        expListView = view.findViewById(R.id.lvexpand);

        markdownView.addStyleSheet(new Github());
        markdownView.loadMarkdown(beforeYouStart.getDescription());

        setupExpendableList();

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
        return view;
    }


    private void setupExpendableList() {

        listDataHeader = new ArrayList<>();

        listDataChild = new HashMap<String, List<String>>();

        // listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);

//        prepareListData();
//
//        beforeDetails = beforeYouStart.getDetails();
//
//
//        for (BeforeYouStartResponse.Detail detailList : beforeDetails) {
//
//            listExpendData = new ArrayList<>();
//            listDataHeader.add(detailList.getQuestion());
//            listExpendData.add(detailList.getAnswers());
//            listDataChild.put(detailList.getQuestion(), listExpendData);
//        }

        // Adding child data
        listDataHeader.add("Which method?");
        listDataHeader.add("What age can you start?");
        listDataHeader.add("When not to start?");
        listDataHeader.add("When do I start?");
        listDataHeader.add("Do I have to stay in forever for naps?");
        // Adding child data
        List<String> Which_method = new ArrayList<>();
        Which_method.add("group1");
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
        listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

    }

    public class ExpandableListDataPump {
        public HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


            List<String> cricket = new ArrayList<String>();
            cricket.add("India");
            cricket.add("Pakistan");
            cricket.add("Australia");
            cricket.add("England");
            cricket.add("South Africa");

            List<String> football = new ArrayList<String>();
            football.add("Brazil");
            football.add("Spain");
            football.add("Germany");
            football.add("Netherlands");
            football.add("Italy");

            List<String> basketball = new ArrayList<String>();
            basketball.add("United States");
            basketball.add("Spain");
            basketball.add("Argentina");
            basketball.add("France");
            basketball.add("Russia");

            expandableListDetail.put("CRICKET TEAMS", cricket);
            expandableListDetail.put("FOOTBALL TEAMS", football);
            expandableListDetail.put("BASKETBALL TEAMS", basketball);
            return expandableListDetail;
        }
    }


}