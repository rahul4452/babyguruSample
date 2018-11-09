package com.example.poplify.baby_guru_sample.before_u_start;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Before_we_start1 extends AppCompatActivity {

    ExpandableListAdapt listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Typeface regular,regularMon;
    TextView it_also,toolbar_title;
    Button start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_we_start1);


        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");



        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.before));
        toolbar_title.setTypeface(regular);


        //textview
        it_also = findViewById(R.id.its_also);
        it_also.setTypeface(regularMon);

        //expendable List
       start_btn = findViewById(R.id.let_start_btn);
       start_btn.setTypeface(regular);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvexpand);

        // preparing list data
        prepareListData();

        //listAdapter = new ExpandableListAdapt(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);





        //scroll expendable view as well as screen
       /* expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });*/


        //expendableList Code scrollview

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Which method?");
        listDataHeader.add("What age can you start?");
        listDataHeader.add("When not to start?");
        listDataHeader.add("When do I start?");
        listDataHeader.add("Do I have to stay in forever for naps?");

        // Adding child data
        List<String> Which_method = new ArrayList<String>();
        Which_method.add("If your little one is under 6 months we always recommend Cuddle, Calm & Continue. Over the 6 months picking them up can sometimes over stimulate them and make it worse so see how your little one responds and follow the Respond, Reassure and Repeat if more appropriate.");
        List<String> When_not = new ArrayList<String>();
        List<String> What_age = new ArrayList<String>();
        List<String> When_do = new ArrayList<String>();
        List<String> Do_I_have = new ArrayList<String>();


        listDataChild.put(listDataHeader.get(0), Which_method); // Header, Child data
        listDataChild.put(listDataHeader.get(1), When_not);
        listDataChild.put(listDataHeader.get(2), What_age);
        listDataChild.put(listDataHeader.get(3), When_do);
        listDataChild.put(listDataHeader.get(4), Do_I_have);

    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
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
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

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
}
