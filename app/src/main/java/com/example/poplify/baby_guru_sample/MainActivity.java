package com.example.poplify.baby_guru_sample;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.adapter.SaveData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login,btn_sign;
    private static final String TAG = "MainActivity";
    ImageView blink;
    Typeface bold,regular,regularMon;
    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");


        saveData = new SaveData(getApplicationContext());


        blink=findViewById(R.id.slide_up_view);

        //setting first welcome window
        btn_sign  = findViewById(R.id.sign_up);
        //btn_sign.setVisibility(View.GONE);
        btn_sign.setOnClickListener(this);
        btn_login  = findViewById(R.id.login);
        btn_login.setOnClickListener(this);
        //btn_login.setVisibility(View.GONE);
        btn_sign.setTypeface(regular);  //Comfortaa
        btn_login.setTypeface(regular); //Comfortaa




    }

    @Override
    protected void onStart() {
        super.onStart();

       /*String checkJoy = saveData.getString("login_token");


        if(!checkJoy.equals(null))
        {

            Intent home = new Intent(getApplicationContext(), Bottom_tabs.class);
            startActivity(home);
            finish();
        }
        else {
            btn_sign.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    protected void onResume() {
        try {

            blink.setVisibility(View.VISIBLE);
            Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_up_bottom);

                blink.startAnimation(slide_up);



        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.sign_up:
                Log.d(TAG, "onClick: askkldsaklsdnakl");
                Intent sign = new Intent(getApplicationContext(), Sign_up.class);
                startActivity(sign);
                //finish();
                break;

            case R.id.login:
                Log.d(TAG, "onClick: askkldsaklsdnakl"+R.id.sign_up);
                Intent log = new Intent(getApplicationContext(), Login.class);
                startActivity(log);
                //finish();
                break;
            default:
                Toast.makeText(getApplicationContext(), "selectButton", Toast.LENGTH_LONG);
        }
    }



}
