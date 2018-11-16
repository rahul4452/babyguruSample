package com.example.poplify.baby_guru_sample;


import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class Testing_acti extends AppCompatActivity {

    private static final String TAG = "Testing_acti";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_acti);

        final CollapsingToolbarLayout collapsingToolbarLayout =  findViewById(R.id.collapsingToolbar);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Title");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");      //carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

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
