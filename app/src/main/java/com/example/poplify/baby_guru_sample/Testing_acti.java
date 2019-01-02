package com.example.poplify.baby_guru_sample;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

public class Testing_acti extends AppCompatActivity {

    private static final String TAG = "Testing_acti";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_acti);



    }


    public void tips() {

        //android.support.v7.app.AlertDialog.Builder dia_builder = new android.support.v7.app.AlertDialog.Builder(getContext(), R.style.GuruTheme);

        View mview = getLayoutInflater().inflate(R.layout.dialog_guru, null);

        Button ok_guru = mview.findViewById(R.id.guru_ok);
        Button contact_guru = mview.findViewById(R.id.guru_contact);
        TextView head_guru = mview.findViewById(R.id.guru_heading);


        //setting the text Font
//        head_guru.setTypeface(regular);
//        ok_guru.setTypeface(regularMon);
//        contact_guru.setTypeface(regularMon);


        //Setting Up the Recycler View
        RecyclerView recyclerView = mview.findViewById(R.id.guru_list);
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        List<String> headlist = Arrays.asList(getResources().getStringArray(R.array.c));
        List<String> bodylist = Arrays.asList(getResources().getStringArray(R.array.d));
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        // Recycle_Guru recycle_guru = new Recycle_Guru(headlist, bodylist, mview);
//
//
//        //call the recycler adapter
//
//        // recyclerView.setAdapter(recycle_guru);
//
//
//        //show in the dialog box
//        dia_builder.setView(mview);
//        android.support.v7.app.AlertDialog dialog = dia_builder.create();
//        dialog.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: activity restart");
     //   Intent  in = new Intent(getApplicationContext(),MainActivity.class);
       // startActivity(in);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: Activity Start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: activity resume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: Activity Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: Activity Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity Destroy");
    }
}
