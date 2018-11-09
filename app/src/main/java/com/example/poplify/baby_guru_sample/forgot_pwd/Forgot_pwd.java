package com.example.poplify.baby_guru_sample.forgot_pwd;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.poplify.baby_guru_sample.Login;
import com.example.poplify.baby_guru_sample.MainActivity;
import com.example.poplify.baby_guru_sample.R;

public class Forgot_pwd extends AppCompatActivity {

    Typeface regular,regularMon;
    TextView toolbar_title;
    android.support.v7.widget.Toolbar toolbar;

    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.forgot_pwd_activity);
        if(findViewById(R.id.frag_container)!= null)
        {
            if(savedInstanceState != null)
            {
                return;
            }
            fragmentManager.beginTransaction().replace(R.id.frag_container,new Forgot_frag()).commit();
        }

        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");

        //setting toolbar header
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.frgt_head));
        toolbar_title.setTypeface(regular);
        toolbar = findViewById(R.id.toolbar_frgt);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(getApplicationContext(),Login.class);
                startActivity(log);

            }
        });



    }
}
