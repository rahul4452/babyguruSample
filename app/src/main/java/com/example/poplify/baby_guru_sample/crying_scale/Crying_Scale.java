package com.example.poplify.baby_guru_sample.crying_scale;


import android.content.res.ColorStateList;
import android.graphics.Color;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.ProgressBarAnimation;


public class Crying_Scale extends AppCompatActivity implements View.OnClickListener {

    Button done_cry;
    ImageView cry1,cry2,cry3,cry4,cry5;
    RoundCornerProgressBar pb;
    ProgressBarAnimation mProgressAnimation;
    TextView chng_txt,txt1_chnge,txt2_chnge,tb_title_cry;
    Typeface regular,regularMon;
    Toolbar toolbar;
    android.support.v7.app.AlertDialog.Builder level3_dialog,level_4_dialog;
    android.support.v7.app.AlertDialog level_3,level_4;
    View mview,mview2 ;
    int progressStatus =0;


    private  Handler ne  = new Handler();

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crying__scale_acti);

        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");

        txt1_chnge = findViewById(R.id.cry_txt1);
        txt1_chnge.setTypeface(regularMon);


        toolbar = findViewById(R.id.crying_toolbar);
        tb_title_cry = findViewById(R.id.toolbar_title);
        tb_title_cry.setText(getResources().getString(R.string.crying_head));
        tb_title_cry.setTypeface(regular);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        txt2_chnge = findViewById(R.id.cry_txt2);
        txt2_chnge.setTypeface(regularMon);

        done_cry = findViewById(R.id.cry_done);
        done_cry.setOnClickListener(this);

        Spannable s = new SpannableString(getResources().getString(R.string.crying_ques));
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.selet_child_inner)), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt2_chnge.setText(s);

        cry1 = findViewById(R.id.cry_lev_1);
        cry1.setOnClickListener(this);

        cry2 = findViewById(R.id.cry_lev_2);
        cry2.setOnClickListener(this);

        cry3 = findViewById(R.id.cry_lev_3);
        cry3.setOnClickListener(this);

        cry4 = findViewById(R.id.cry_lev_4);
        cry4.setOnClickListener(this);

        cry5 = findViewById(R.id.cry_lev_5);
        cry5.setOnClickListener(this);

        pb = (RoundCornerProgressBar) findViewById(R.id.progressBar);

        chng_txt = findViewById(R.id.cry_txt_chnge);

    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

//==============================================================================================================================================================================================
            case R.id.cry_lev_1:
                chng_txt.setText(getResources().getString(R.string.lev_1));

                if (progressStatus <= 100) {
                    progressStatus = 20;
                }
                Log.d("", "onClick: progress status" + pb.getProgress()+"and aasdad"+pb.getProgress());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                          if (progressStatus < pb.getProgress()) { //setting up color in new thread
                                pb.setProgress(20);
                              pb.setProgressColor(getResources().getColor(R.color.tab4_color));
                            }
                            else {
                                pb.setProgress(20);
                                pb.setProgressColor(getResources().getColor(R.color.tab4_color));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                if (view.getId() == R.id.cry_done) {
                    Toast.makeText(getApplicationContext(), "cry 1", Toast.LENGTH_SHORT).show();
                }
                break;


//==============================================================================================================================================================================================
            case R.id.cry_lev_2:

                chng_txt.setText(getResources().getString(R.string.lev_2));


               Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressStatus <= 100) {            //setting up color in new thread
                                progressStatus = 40;
                            }
                            Log.d("", "onClick: progress status" + pb.getProgress()+"and aasdad"+pb.getProgress());
                            if (progressStatus > pb.getProgress()) {
                                mProgressAnimation = new ProgressBarAnimation(pb, pb.getProgress(), progressStatus);
                                mProgressAnimation.setDuration(600);

                                pb.startAnimation(mProgressAnimation);

                            } else {
                                pb.setProgress(progressStatus);
                                pb.setProgressColor(getResources().getColor(R.color.tab2_color));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ne.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pb.setProgressColor(getResources().getColor(R.color.tab2_color));
                            }
                        }, 400);
                    }
                });

               t2.start();



                if (view.getId() == R.id.cry_done) {
                    Toast.makeText(getApplicationContext(), "cry 2", Toast.LENGTH_SHORT).show();
                }
                break;


//====================================================================================================================================================================================================
            case R.id.cry_lev_3:

                Log.d("", "onClick: progress status" + pb.getProgress());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressStatus <= 100) {
                                progressStatus = 60;
                            }
                            Log.d("", "onClick: progress status" + pb.getProgress());
                            if (progressStatus > pb.getProgress()) {
                                mProgressAnimation = new ProgressBarAnimation(pb, pb.getProgress(), progressStatus);

                                mProgressAnimation.setDuration(600);

                                pb.startAnimation(mProgressAnimation);


                            } else {
                                pb.setProgress(progressStatus);

                                pb.setProgressColor((getResources().getColor(R.color.tab1_color)));

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ne.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pb.setProgressColor(getResources().getColor(R.color.tab1_color));
                            }
                        }, 500);
                    }
                }).start();


                chng_txt.setText(getResources().getString(R.string.lev_3));
                if (view.getId() == R.id.cry_done) {
                    Toast.makeText(getApplicationContext(), "cry 3", Toast.LENGTH_SHORT).show();
                }
                break;
//==============================================================================================================================================================================================
            case R.id.cry_lev_4:


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressStatus <= 100) {
                                progressStatus = 80;
                            }
                            Log.d("", "onClick: progress status" + pb.getProgress());
                            if (progressStatus > pb.getProgress()) {
                                mProgressAnimation = new ProgressBarAnimation(pb, pb.getProgress(), progressStatus);

                                mProgressAnimation.setDuration(600);
                                pb.startAnimation(mProgressAnimation);

                            } else {

                                pb.setProgress(progressStatus);
                                pb.setProgressColor(getResources().getColor(R.color.bef_list_1));

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ne.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pb.setProgressColor(getResources().getColor(R.color.bef_list_1));
                            }
                        }, 650);
                    }
                }).start();


                chng_txt.setText(getResources().getString(R.string.lev_4));
                if (view.getId() == R.id.cry_done) {
                    Toast.makeText(getApplicationContext(), "cry 4", Toast.LENGTH_SHORT).show();
                }
                break;
//==============================================================================================================================================================================================
            case R.id.cry_lev_5:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressStatus <= 100) {
                                progressStatus = 100;
                            }
                            Log.d("", "onClick: progress status" + pb.getProgress());
                            if (progressStatus > pb.getProgress()) {
                                mProgressAnimation = new ProgressBarAnimation(pb, pb.getProgress(), progressStatus);
                                pb.setProgress(progressStatus);

                                mProgressAnimation.setDuration(600);
                                pb.startAnimation(mProgressAnimation);

                            } else {

                                pb.setProgress(progressStatus);
                                pb.setProgressColor((Color.rgb(255, 74, 82)));

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ne.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pb.setProgressColor(Color.rgb(255, 74, 82));
                            }
                        }, 750);
                    }
                }).start();


                chng_txt.setText(getResources().getString(R.string.lev_5));
                if (view.getId() == R.id.cry_done) {
                    Toast.makeText(getApplicationContext(), "cry 5", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                Toast.makeText(getApplicationContext(), "default setting", Toast.LENGTH_SHORT).show();
                break;
        }
//==============================================================================================================================================================================================
        //==============================================================================================================================================================================================
        //==============================================================================================================================================================================================
        //==============================================================================================================================================================================================


        if(view.getId() == R.id.cry_done && progressStatus == 20){
            level3_dialog = new android.support.v7.app.AlertDialog.Builder(this, R.style.GuruTheme);
            mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
            TextView text1, text2, text_icon;
            Button alert_3_btn;

            //**********************************
            level3_dialog.setView(mview);

            level_3 = level3_dialog.create();
            level_3.show();
            level_3.setCanceledOnTouchOutside(false);
            //*********************************

            text1 = mview.findViewById(R.id.txt_4_alert);
            text2 = mview.findViewById(R.id.txt_cry_alert_2);
            text_icon = mview.findViewById(R.id.level_4_back);
            text_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    level_3.cancel();

                }
            });


            text1.setTypeface(regularMon);
            text2.setTypeface(regularMon);


            alert_3_btn = mview.findViewById(R.id.btn_alert_1);
            alert_3_btn.setTypeface(regular);
        }else if(view.getId() == R.id.cry_done && progressStatus == 40){
            level3_dialog = new android.support.v7.app.AlertDialog.Builder(this, R.style.GuruTheme);
            mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
            TextView text1, text2, text_icon;
            Button alert_3_btn;

            //**********************************
            level3_dialog.setView(mview);

            level_3 = level3_dialog.create();
            level_3.show();
            level_3.setCanceledOnTouchOutside(false);
            //*********************************

            text1 = mview.findViewById(R.id.txt_4_alert);
            text2 = mview.findViewById(R.id.txt_cry_alert_2);
            text_icon = mview.findViewById(R.id.level_4_back);
            text_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    level_3.cancel();

                }
            });


            text1.setTypeface(regularMon);
            text2.setTypeface(regularMon);


            alert_3_btn = mview.findViewById(R.id.btn_alert_1);
            alert_3_btn.setTypeface(regular);

        }else if (view.getId() == R.id.cry_done && progressStatus == 60) {        //progress at 60
            level3_dialog = new android.support.v7.app.AlertDialog.Builder(this, R.style.GuruTheme);
            mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
            TextView text1, text2, text_icon;
            Button alert_3_btn;

            //**********************************
            level3_dialog.setView(mview);

            level_3 = level3_dialog.create();
            level_3.show();
            level_3.setCanceledOnTouchOutside(false);
            //*********************************

            text1 = mview.findViewById(R.id.txt_4_alert);
            text2 = mview.findViewById(R.id.txt_cry_alert_2);
            text_icon = mview.findViewById(R.id.level_4_back);
            text_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    level_3.cancel();

                }
            });


            text1.setTypeface(regularMon);
            text2.setTypeface(regularMon);


            alert_3_btn = mview.findViewById(R.id.btn_alert_1);
            alert_3_btn.setTypeface(regular);

        } else if (view.getId() == R.id.cry_done && progressStatus == 80) {         //progress at 80
            //alert for cry level 4

            level_4_dialog = new android.support.v7.app.AlertDialog.Builder(Crying_Scale.this, R.style.GuruTheme);

            mview2 = getLayoutInflater().inflate(R.layout.dialog_cry_4, null);
            final TextView text_alert_4, text_back;
            Button alert_consis_btn, alert_inconsis_btn;

            //set back button icon
            level_4_dialog.setIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp)).show();


            text_alert_4 = mview2.findViewById(R.id.txt_4_alert);
            text_alert_4.setTypeface(regularMon);

            alert_consis_btn = mview2.findViewById(R.id.consis_btn);
            alert_consis_btn.setTypeface(regular);

            //**********************************
            level_4_dialog.setView(mview2);
            level_4 = level_4_dialog.create();
            level_4.setCanceledOnTouchOutside(false); //stop closing alert on outside screen
            level_4.show();
            //*********************************

            text_back = mview2.findViewById(R.id.txt_back_1);
            text_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    level_4.cancel();
                }
            });


            //click on consistent button

            alert_consis_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        level_4.cancel();
                        level3_dialog = new android.support.v7.app.AlertDialog.Builder(Crying_Scale.this, R.style.GuruTheme);
                        mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
                        TextView text1, text2, text_icon;
                        Button alert_3_btn;

                        //**********************************
                        level3_dialog.setView(mview);
                        level_3 = level3_dialog.create();
                        level_3.setCanceledOnTouchOutside(false);
                        level_3.show();
                        //*********************************

                        text1 = mview.findViewById(R.id.txt_4_alert);
                        text2 = mview.findViewById(R.id.txt_cry_alert_2);
                        text_icon = mview.findViewById(R.id.level_4_back);
                        text_icon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                level_4.show();
                                level_3.cancel();

                            }
                        });


                        text1.setTypeface(regularMon);
                        text2.setTypeface(regularMon);


                        alert_3_btn = mview.findViewById(R.id.btn_alert_1);
                        alert_3_btn.setTypeface(regular);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });


            //click on Inconsistent Button
            alert_inconsis_btn = mview2.findViewById(R.id.inconsis);
            alert_inconsis_btn.setTypeface(regular);

            alert_inconsis_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        level_4.cancel();
                        level3_dialog = new android.support.v7.app.AlertDialog.Builder(Crying_Scale.this, R.style.GuruTheme);
                        mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
                        TextView text1, text2, text_icon;
                        Button alert_3_btn;

                        //**********************************
                        level3_dialog.setView(mview);
                        level_3 = level3_dialog.create();
                        level_3.setCanceledOnTouchOutside(true);
                        level_3.show();

                        //*********************************
                        text1 = mview.findViewById(R.id.txt_4_alert);
                        text2 = mview.findViewById(R.id.txt_cry_alert_2);
                        text_icon = mview.findViewById(R.id.level_4_back);
                        text_icon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                level_4.show();
                                level_3.cancel();
                            }
                        });
                        text1.setTypeface(regularMon);
                        text2.setTypeface(regularMon);

                        alert_3_btn = mview.findViewById(R.id.btn_alert_1);
                        alert_3_btn.setTypeface(regular);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (view.getId() == R.id.cry_done && progressStatus == 100) {            //progress at 100
            level3_dialog = new android.support.v7.app.AlertDialog.Builder(this, R.style.GuruTheme);
            mview = getLayoutInflater().inflate(R.layout.dialog_cry_3, null);
            TextView text1, text2, text_icon;
            Button alert_3_btn;

            //**********************************
            level3_dialog.setView(mview);

            level_3 = level3_dialog.create();
            level_3.show();
            level_3.setCanceledOnTouchOutside(false);
            //*********************************

            text1 = mview.findViewById(R.id.txt_4_alert);
            text2 = mview.findViewById(R.id.txt_cry_alert_2);
            text_icon = mview.findViewById(R.id.level_4_back);
            text_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    level_3.cancel();

                }
            });


            text1.setTypeface(regularMon);
            text2.setTypeface(regularMon);


            alert_3_btn = mview.findViewById(R.id.btn_alert_1);
            alert_3_btn.setTypeface(regular);

        }
    }


}
