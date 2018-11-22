package com.example.poplify.baby_guru_sample.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.before_u_start.Before_we_start_frag;

import java.util.HashMap;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;

public class ExpandableListAdapt implements ExpandableListAdapter {

    private Context context;
    private List<String> list_header;
    private HashMap<String,List<String>> list_data_child;

    Group_Holder group_holder;
    private MarkdownView txtListchild;


    public ExpandableListAdapt(Context context, List<String> list_header, HashMap<String, List<String>> list_data_child) {
        this.context = context;
        this.list_header = list_header;
        this.list_data_child = list_data_child;

    }

    @Override
    public Object getChild(int i, int i1) {
        return this.list_data_child.get(this.list_header.get(i)).get(i1);
    }


    @Override
    public int getChildrenCount(int i) {
        return this.list_data_child.get(this.list_header.get(i)).size();
    }




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public View getChildView(int i, int i1, boolean islastChild, View view, ViewGroup viewGroup) {

        String childtext = (String) getChild(i,i1);
        if(view == null)
        {
            LayoutInflater inflate  = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflate.inflate(R.layout.list_item_child,null);

           txtListchild = view.findViewById(R.id.lblListitem);
//            child_viewholder.setTxtListchild(txtListchild);


            /////
            Typeface regularMon = Typeface.createFromAsset(view.getResources().getAssets(),"Montserrat-Regular.otf");





            //Using Holder  Class to maintain the efficency and flexibilty

//            Child_Viewholder child_viewholder1 = (Child_Viewholder) view.getTag();

            //Setting Resource

            if(i%2==0)
            {

                txtListchild.setBackground(context.getDrawable(R.drawable.bef_list_1_text));//pink
            }
            else if (i%3==0)
            {
                txtListchild.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//green
            }

            else if (i%5==0){

                txtListchild.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//purple
            }
            else {
               txtListchild.setBackground(context.getDrawable(R.drawable.bef_list_2_text));//yellow
            }

            txtListchild.loadMarkdown(childtext);

        }


        return view;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }


    class Group_Holder{
        private TextView txtlistHeader;

        public TextView getTxtlistHeader() {
            return txtlistHeader;
        }

        public void setTxtlistHeader(TextView txtlistHeader) {
            this.txtlistHeader = txtlistHeader;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getGroupView(int i, boolean b, View Rootview, ViewGroup viewGroup) {


        String headTitle = (String) getGroup(i);

        if(Rootview==null)
        {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Rootview = inflater.inflate(R.layout.list_group,null);

            TextView txtlistHeader =  Rootview.findViewById(R.id.lblListheader);
            group_holder = new Group_Holder();
            group_holder.setTxtlistHeader(txtlistHeader);
            Rootview.setTag(group_holder);

        }

        Typeface regularMon = Typeface.createFromAsset(Rootview.getResources().getAssets(),"Montserrat-Regular.otf");
        Group_Holder group_holder1 = (Group_Holder) Rootview.getTag();

        if(i%2==0)
        {


            group_holder1.getTxtlistHeader().setBackground(context.getDrawable(R.drawable.bef_list_1_head));//pink
        }
        else if (i%3==0)
        {
            group_holder1.getTxtlistHeader().setBackground(context.getDrawable(R.drawable.bef_list_2_head));//green
        }

        else if (i%5==0){

            group_holder1.getTxtlistHeader().setBackground(context.getDrawable(R.drawable.bef_list_3_head));//purple
        }
        else {
            group_holder1.getTxtlistHeader().setBackground(context.getDrawable(R.drawable.bef_list_4_head));//yellow
        }


        group_holder1.getTxtlistHeader().setTypeface(regularMon);
        group_holder1.getTxtlistHeader().setText(headTitle);

        return Rootview;
    }



    @Override
    public Object getGroup(int i) {
        return this.list_header.get(i);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return this.list_header.size();
    }


    @Override
    public long getGroupId(int i) {
        return i;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void onGroupExpanded(int i) {

        //isExpanded=true;

    }

    @Override
    public void onGroupCollapsed(int i) {
        //isExpanded=false;
    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
