package com.example.poplify.baby_guru_sample.adapter;


import android.content.Context;
import android.database.DataSetObserver;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

import java.util.HashMap;
import java.util.List;

//import br.tiagohm.markdownview.MarkdownView;


public class ExpandableListAdapt extends BaseExpandableListAdapter {

    private Context context;
    private List<String> header;
    private HashMap<String, List<String>> expendedData;
    private WebView markdownView;
    private TextView txtlistHeader;
    private ImageView imageView;
    private String childtext;
    private int color[] = {
            R.color.light_purple, R.color.tab4_color, R.color.bef_list_1, R.color.bef_list_4,
            R.color.selet_child_inner
    };
    LinearLayout childBglaout;

    public ExpandableListAdapt(Context context, List<String> header, HashMap<String, List<String>> expendedData) {
        this.context = context;
        this.header = header;
        this.expendedData = expendedData;
    }

    @Override
    public int getGroupCount() {
        return header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expendedData.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.header.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expendedData.get(this.header.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group, null);

        String headTitle = (String) getGroup(groupPosition);
        txtlistHeader = convertView.findViewById(R.id.lblListheader);
        Typeface regularMon = Typeface.createFromAsset(convertView.getResources().getAssets(), "Montserrat-Regular.otf");

        GradientDrawable bgShape = (GradientDrawable) txtlistHeader.getBackground();
        bgShape.setColor(convertView.getResources().getColor(color[groupPosition % color.length]));

        imageView = convertView.findViewById(R.id.ivGroupIndicator);
        imageView.setSelected(isExpanded);


//        if (groupPosition % 2 == 0) {
//            txtlistHeader.setBackground(context.getDrawable(R.drawable.bef_list_1_head));//pink
//        } else if (groupPosition % 3 == 0) {
//            txtlistHeader.setBackground(context.getDrawable(R.drawable.bef_list_2_head));//green
//        } else if (groupPosition % 5 == 0) {
//            txtlistHeader.setBackground(context.getDrawable(R.drawable.bef_list_3_head));//purple
//        } else {
//            txtlistHeader.setBackground(context.getDrawable(R.drawable.bef_list_4_head));//yellow
//        }
        txtlistHeader.setTypeface(regularMon);
        txtlistHeader.setText(headTitle);


        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        LayoutInflater inflate = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(R.layout.list_item_child, parent, false);

        childtext = (String) getChild(groupPosition, childPosition);
        markdownView = view.findViewById(R.id.lblListitem);
        childBglaout = view.findViewById(R.id.childBgLayout);

        GradientDrawable bgShape = (GradientDrawable) childBglaout.getBackground();
        bgShape.setStroke(2,color[groupPosition%color.length]);

        markdownView.loadData(childtext, "text/html", "UTF-8");


        //bgShape.setColor(view.getResources().getColor(color[groupPosition % color.length]));
//        if (groupPosition % 2 == 0) {
//
//            childBglaout.setBackground(context.getDrawable(R.drawable.button_with_border));//pink
//        } else if (groupPosition % 3 == 0) {
//            childBglaout.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//green
//        } else if (groupPosition % 5 == 0) {
//
//            childBglaout.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//purple
//        } else {
//            childBglaout.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//yellow
//        }


        //Setting Resource


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}