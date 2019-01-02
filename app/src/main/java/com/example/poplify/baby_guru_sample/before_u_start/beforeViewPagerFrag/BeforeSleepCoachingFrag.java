package com.example.poplify.baby_guru_sample.before_u_start.beforeViewPagerFrag;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;
import com.example.poplify.baby_guru_sample.custom_expendable.NonScrollExpandableListView;
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

//import br.tiagohm.markdownview.MarkdownView;
//import br.tiagohm.markdownview.css.styles.Github;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeSleepCoachingFrag extends Fragment {

    ExpandableListAdapt listAdapter;
    ExpandableListView expListView;
    BeforeYouStartResponse.BeforeYouStart beforeYouStart;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    NestedScrollView scrollBody;
    private Bundle bundle;
    private String SERIALIZED_KEY = "before_you_start";
    private WebView markdownView;
    private List<BeforeYouStartResponse.Detail> beforeDetails;
    private List<String> listExpendData;

    private static final String TAG = "BeforeSleepCoachingFrag";
    // Inflate the layout for this fragment
    View view;
    private ImageView imageView;

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


        expListView = view.findViewById(R.id.lvexpand);

        container = (ViewGroup) getLayoutInflater().inflate(R.layout.expandheader,expListView,false);

        expListView.addHeaderView(container);
//        scrollBody = view.findViewById(R.id.nest);
        markdownView = view.findViewById(R.id.markDownView);
        markdownView.loadData(beforeYouStart.getDescription(), "text/html", "UTF-8");


        setupExpendableList();

        expListView.measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
               // setListViewHeight(expandableListView, i);

//                imageView = view.findViewById(R.id.ivGroupIndicator);
//                if(expandableListView.isGroupExpanded(i))
//                {
//                    imageView.setSelected(true);
//                }
//                else{
//                    imageView.setSelected(false);
//                    // Expanded ,Do your Staff
//
//                }


                return false;
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            int previousGroup = -1;

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                expListView.collapseGroup(previousGroup);
                return false;
            }
        });


        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {

                if (groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;


            }
        });

        return view;
    }


    private void setExpandableListViewHeight(ExpandableListView listView) {
        try {
            ExpandableListAdapter listAdapter =  listView.getExpandableListAdapter();
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getGroupCount(); i++) {
                View listItem = listAdapter.getGroupView(i, false, null, listView);

                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
            if (height < 10) height = 200;
            params.height = height;
            listView.setLayoutParams(params);
            listView.requestLayout();
//            scrollBody.post(new Runnable() {
//                public void run() {
//                    scrollBody.fullScroll(NestedScrollView.FOCUS_UP);
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, view, listView);

            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
//        if (height < 10)
//            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void setupExpendableList() {

        listDataHeader = new ArrayList<>();

        listDataChild = new HashMap<String, List<String>>();

        beforeDetails = beforeYouStart.getDetails();

        for (BeforeYouStartResponse.Detail details : beforeDetails) {
            listExpendData = new ArrayList<>();
            listDataHeader.add(details.getQuestion());
            listExpendData.add(details.getAnswers());
            listDataChild.put(details.getQuestion(), listExpendData);
        }
        listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
       // setExpandableListViewHeight(expListView);
        //setListViewHeight(expListView, i);
    }
}