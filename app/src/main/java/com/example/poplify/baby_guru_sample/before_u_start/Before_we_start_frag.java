package com.example.poplify.baby_guru_sample.before_u_start;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ExpandableListAdapt;
import com.example.poplify.baby_guru_sample.sleep_Timer.Timer_frag;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Before_we_start_frag extends Fragment {

    ExpandableListAdapt listAdapter;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Before_Holder before_holder;
    Typeface regular,regularMon;


    FragmentManager fragmentManager;
    ImageView iv;
    private static final String TAG = "Before_we_start_frag";

    public Before_we_start_frag() {
        // Required empty public constructor
    }


    class Before_Holder{
        android.support.v7.widget.Toolbar toolbar;
        private TextView it_also,tb_title_before;
        private Button start_btn;
        ExpandableListView expListView;

        public Toolbar getToolbar() {
            return toolbar;
        }

        public void setToolbar(Toolbar toolbar) {
            this.toolbar = toolbar;
        }

        public TextView getIt_also() {
            return it_also;
        }

        public void setIt_also(TextView it_also) {
            this.it_also = it_also;
        }

        public TextView getTb_title_before() {
            return tb_title_before;
        }

        public void setTb_title_before(TextView tb_title_before) {
            this.tb_title_before = tb_title_before;
        }

        public Button getStart_btn() {
            return start_btn;
        }

        public void setStart_btn(Button start_btn) {
            this.start_btn = start_btn;
        }

        public ExpandableListView getExpListView() {
            return expListView;
        }

        public void setExpListView(ExpandableListView expListView) {
            this.expListView = expListView;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.before_we_start_frag, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        //Setting fonts
        regular = Typeface.createFromAsset(getResources().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getResources().getAssets(),"Montserrat-Regular.otf");

        before_holder = new Before_Holder();
        TextView it_also = view.findViewById(R.id.its_also);
        Button start_btn = view.findViewById(R.id.let_start_btn);
        ExpandableListView expListView =  view.findViewById(R.id.lvexpand);
        TextView tb_title_before = view.findViewById(R.id.toolbar_title);

        before_holder.setTb_title_before(tb_title_before);
        before_holder.setIt_also(it_also);
        before_holder.setStart_btn(start_btn);
        before_holder.setExpListView(expListView);






        //textview
        before_holder.getIt_also().setTypeface(regularMon);

        before_holder.getStart_btn().setTypeface(regular);

        //Setting Up the toolbar  title

        view.setTag(before_holder);

        before_holder.getTb_title_before().setText(getResources().getString(R.string.before));
        //tb_title_before.setTextSize(12);
        tb_title_before.setTypeface(regular);

        //******button click to go to select child
        before_holder.getStart_btn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               replacementFragment(new Select_frag());
            }
        });



        //**************
        //***************


        // preparing list data
        new GetList().execute();


        //**************
        //***************

        //Checking which group is expanded and change the right indicator

        before_holder.getExpListView().setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int i, long l) {
                iv = view.findViewById(R.id.indicator_view);

                if(parent.isGroupExpanded(i))
                {
                    iv.setSelected(false);
                }
                else
                {

                    iv.setSelected(true);
                }

                return false;
            }
        });

        return view;
    }


    private void replacementFragment(Fragment fragment)
    {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack,0);

        Log.d("", "replacementFragment: fragmentPopped"+fragmentPopped);
        try {
            if (fragmentPopped!=true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
        When_not.add("Group2");
        List<String> What_age = new ArrayList<String>();
        What_age.add("Group3");
        List<String> When_do = new ArrayList<String>();
        When_do.add("Group4");
        List<String> Do_I_have = new ArrayList<String>();
        Do_I_have.add("Group5");


        listDataChild.put(listDataHeader.get(0), Which_method); // Header, Child data
        listDataChild.put(listDataHeader.get(1), When_not);
        listDataChild.put(listDataHeader.get(2), What_age);
        listDataChild.put(listDataHeader.get(3), When_do);
        listDataChild.put(listDataHeader.get(4), Do_I_have);

    }

    public  class GetList extends AsyncTask<String,Void,Void>
    {
        @Override
        protected void onPreExecute() {

            Toast.makeText(getContext(),"on Pre",Toast.LENGTH_SHORT);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Toast.makeText(getContext(),"on Post",Toast.LENGTH_SHORT);
            super.onPostExecute(aVoid);
        }


        @Override
        protected Void doInBackground(String... strings) {

            prepareListData();
            listAdapter = new ExpandableListAdapt(getContext(), listDataHeader, listDataChild);

            // setting list adapter
            before_holder.getExpListView().setAdapter(listAdapter);
            return null;
        }
    }
}

