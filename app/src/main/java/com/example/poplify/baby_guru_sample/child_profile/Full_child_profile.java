package com.example.poplify.baby_guru_sample.child_profile;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Full_child_profile extends Fragment implements View.OnClickListener {

    TextView detail_txt,parent_name_txt,baby_born_txt,baby_born_date_txt,baby_txt,baby_gen_txt,baby_his_txt;
    Button edit_btn,see_graph;
    Typeface bold,regular,regularMon;
    List<String> session,session_date,session_time,session_time_dif;
    RecyclerView recyclerView;
    Child child_adapt;
    RecyclerView.LayoutManager layoutManager;


    public Full_child_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_child_profile_frag, container, false);

        intilizeList();


        recyclerView = view.findViewById(R.id.child_recyclerView);
        layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        //call the recycler adapter
        child_adapt = new Child(session,session_date,session_time,session_time_dif);
        recyclerView.setAdapter(child_adapt);

        Initializer(view);//setting up textview,button and Font
        return view;
    }

    private void intilizeList() {
        session = new ArrayList<String>();
        session.add("Session 1");
        session.add("Session 2");
        session.add("Session 3");
        session.add("Session 4");
        session.add("Session 4");

        session.size();

        session_date = new ArrayList<String>();
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");
        session_date.add("14-Fed-2018");

        session_time = new ArrayList<String>();
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");
        session_time.add("21:30 - 22:04");

        session_time_dif = new ArrayList<String>();
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");
        session_time_dif.add("34 mins");


    }

    private void Initializer(View view) {

        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(),"Montserrat-Regular.otf");

        //Button
        edit_btn = view.findViewById(R.id.edit_btn);
        edit_btn.setTypeface(regular);
        edit_btn.setOnClickListener(this);

        see_graph = view.findViewById(R.id.see_graph_btn);
        see_graph.setTypeface(regular);
        see_graph.setOnClickListener(this);


        //all TextViews

        detail_txt = view.findViewById(R.id.detail);
        detail_txt.setTypeface(regular);


        parent_name_txt = view.findViewById(R.id.parent_name);
        parent_name_txt.setTypeface(regularMon);


        baby_born_txt = view.findViewById(R.id.baby_txt);
        baby_born_txt.setTypeface(regularMon);


        baby_born_date_txt = view.findViewById(R.id.born_date);
        baby_born_date_txt.setTypeface(regularMon);

        baby_txt = view.findViewById(R.id.baby_txt);
        baby_txt.setTypeface(regularMon);


        baby_gen_txt = view.findViewById(R.id.gender_txt);
        baby_gen_txt.setTypeface(regularMon);


        baby_his_txt = view.findViewById(R.id.sleep_history);
        baby_his_txt.setTypeface(regular);

    }


    @Override
    public void onClick(View view) {
        int btn = view.getId();

        switch (btn)
        {
            case R.id.edit_btn:

                break;

            case R.id.see_graph_btn:
                Intent graph = new Intent(getContext(),Graph_child.class);
                startActivity(graph);
                break;
        }
    }


    private class Child extends RecyclerView.Adapter<Child.Child_Holder>{


        List<String> lsession,lsession_date,lsession_time,lsession_time_dif;

        public Child(List<String> lsession, List<String> lsession_date, List<String> lsession_time, List<String> lsession_time_dif) {
            this.lsession = lsession;
            this.lsession_date = lsession_date;
            this.lsession_time = lsession_time;
            this.lsession_time_dif = lsession_time_dif;
        }

        public class Child_Holder extends RecyclerView.ViewHolder{
            TextView tv_session,tv_child_time,tv_date,tv_diff_time;

            public Child_Holder(@NonNull View itemView) {

                super(itemView);
                tv_session = itemView.findViewById(R.id.session_tv);
                tv_child_time = itemView.findViewById(R.id.child_date_tv);
                tv_date = itemView.findViewById(R.id.main_time_tv);
                tv_diff_time = itemView.findViewById(R.id.child_time_tv);
            }
        }


        @NonNull
        @Override
        public Child_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_profile_recycler,viewGroup,false);
            Child_Holder holder = new Child_Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Child_Holder child_holder, int i) {

            String s1,s2,s3,s4;
            s1=lsession.get(i);
            s2=lsession_time.get(i);
            s3=lsession_date.get(i);
            s4 = lsession_time_dif.get(i);

           child_holder.tv_session.setText(s1);
           child_holder.tv_child_time.setText(s2);
           child_holder.tv_date.setText(s3);
           child_holder.tv_diff_time.setText(s4);

        }



        @Override
        public int getItemCount() {
            return lsession.size();
        }
    }
}
