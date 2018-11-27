package com.example.poplify.baby_guru_sample.joyride_screen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.MainActivity;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;

import java.net.URI;

 public class JoyRide_scr1 extends AppCompatActivity  {


    Typeface regular,regularMon;
    TextView welcome_tv,see_tv;
    VideoView start_video;
    ImageView temp_image;
    MediaController mediaController;
    Button getStart_btn;
    SaveData saveData;
     Uri uri;
     private int flag=1;
     private int stopPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joy_ride_scr1_acti);

        saveData = new SaveData(getApplicationContext());

        initJoyRider();
        setupUI();



    }

     @Override
     protected void onStart() {

         super.onStart();

        String checkJoy = saveData.getString("login_token");

         if(!checkJoy.equals(null))
         {
             Intent home = new Intent(getApplicationContext(), Bottom_tabs.class);
             startActivity(home);
             finish();
         }

     }

     @Override
     public void onWindowFocusChanged(boolean hasFocus) {
         super.onWindowFocusChanged(hasFocus);
         if(!hasFocus) {
             showSystemUI();
         }
         else {
             hideSystemUI();
         }
     }

     private void hideSystemUI() {

         View decorView = getWindow().getDecorView();
         decorView.setSystemUiVisibility(
                 View.SYSTEM_UI_FLAG_IMMERSIVE
                         // Set the content to appear under the system bars so that the
                         // content doesn't resize when the system bars hide and show.
                         | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                         | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                         // Hide the nav bar and status bar
                         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                         );
     }

     private void showSystemUI() {
         View decorView = getWindow().getDecorView();
         decorView.setSystemUiVisibility(
                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                         | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
     }

     private void setupUI() {
         //Button
         getStart_btn = findViewById(R.id.get_start_btn);
         getStart_btn.setTypeface(regular);
         getStart_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent home = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(home);
                 finish();
             }
         });

         //Image View
         temp_image = findViewById(R.id.close_image);
         temp_image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try{

                     startingVideo();


                 }
                 catch (Exception e)
                 {
                     e.printStackTrace();
                 }
             }
         });


         //videoView
         start_video = findViewById(R.id.videoView);

         //setting up the textbox
         welcome_tv = findViewById(R.id.wetcome_txt);
         welcome_tv.setTypeface(regular);
         see_tv = findViewById(R.id.see_txt);
         see_tv.setTypeface(regularMon);
     }

     private void initJoyRider() {
         uri = getMedia();
         regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
         regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");


         //setting up the media controls

         mediaController = new MediaController(this);




     }

     private void startingVideo(){
        start_video.setVideoURI(uri);
        start_video.requestFocus();
        if(start_video.getVisibility()==View.GONE) {
            new PlayVideo().execute();
            start_video.setVisibility(View.VISIBLE);

        }
        else {
            new PlayVideo().execute();
        }
    }

    private Uri getMedia()
    {
        return Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.incredible);
    }

    private  void releaseMediaPlayer()
    {
        start_video.stopPlayback();
    }

     @Override
     protected void onStop() {
         super.onStop();
        // releaseMediaPlayer();
     }

     @Override
     protected void onResume() {
        if(flag == 0)
        {
            start_video.seekTo(stopPosition);
            getStart_btn.setVisibility(View.VISIBLE);
           start_video.start();

        }
         super.onResume();
     }

     @Override
     public void onBackPressed() {
        if(start_video.isPlaying())
        {
            releaseMediaPlayer();

            //
            welcome_tv.setVisibility(View.VISIBLE);
            see_tv.setVisibility(View.VISIBLE);
            getStart_btn.setVisibility(View.VISIBLE);
            temp_image.setVisibility(View.VISIBLE);
            start_video.setVisibility(View.GONE);

        }
        else {
            super.onBackPressed();
        }


     }

     @Override
     protected void onPause() {
         if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
             start_video.pause();
             flag =0;
             stopPosition=start_video.getCurrentPosition();
         }
         super.onPause();
     }

    public class PlayVideo extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {



            start_video.start();


           /* start_video.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getStart_btn.setVisibility(View.VISIBLE);
                    getStart_btn.setEnabled(false);

                }
            },15000);


            start_video.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(),"Skip Video",Toast.LENGTH_SHORT).show();
                    getStart_btn.setText("get started");
                    getStart_btn.setEnabled(true);
                    getStart_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent home = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(home);
                        }
                    });
                }
            },20000);*/

            start_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    start_video.setBackgroundResource(0);
                    mediaController.setAnchorView(start_video);
                    start_video.setMediaController(mediaController);
                    welcome_tv.setVisibility(View.GONE);
                    see_tv.setVisibility(View.GONE);
                    getStart_btn.setVisibility(View.GONE);
                    temp_image.setVisibility(View.GONE);


                }
            });

            start_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    getStart_btn.setVisibility(View.VISIBLE);
                    releaseMediaPlayer();
                }
            });

            start_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Toast.makeText(getApplicationContext(),"Sorry For Delay",Toast.LENGTH_LONG).show();
                    return false;
                }
            });
            return null;
       }



        @Override
        protected void onProgressUpdate(Void... values) {




            super.onProgressUpdate(values);
        }
    }
 }
