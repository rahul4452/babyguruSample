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
    private Bundle bundle;
    private String SERIALIZED_KEY = "before_you_start";
    private MarkdownView markdownView;
    private List<BeforeYouStartResponse.Detail> beforeDetails;
    private List<String> listExpendData;

    private static final String TAG = "BeforeSleepCoachingFrag";
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
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                // setListViewHeight(expandableListView, i);
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                int height = 0;
                for (int i1 = 0; i1 < expListView.getChildCount(); i1++) {
                    height += expListView.getChildAt(i1).getMeasuredHeight();
                    height += expListView.getDividerHeight();
                }
                expListView.getLayoutParams().height = height  * 8;
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                int height = expListView.getHeight();
                expListView.getLayoutParams().height = height;
            }
        });

        return view;
    }


    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.AT_MOST);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
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
    }
}