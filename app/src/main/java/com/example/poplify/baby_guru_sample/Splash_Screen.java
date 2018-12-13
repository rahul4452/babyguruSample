package com.example.poplify.baby_guru_sample;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.joyride_screen.JoyRide_scr1;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;

public class Splash_Screen extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        saveData = new SaveData(getApplicationContext());

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if(!saveData.getBoolean("isUserLogin")){
                    startActivity(new Intent(Splash_Screen.this, JoyRide_scr1.class));
                    finish();

                }else{
                    startActivity(new Intent(Splash_Screen.this, Bottom_tabs.class));
                    finish();

                }
            }
        }, SPLASH_TIME_OUT);
    }
}
