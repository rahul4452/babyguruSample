package com.example.poplify.baby_guru_sample.reset_PWD;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.Login;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.ResetPassword;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import retrofit2.Call;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class Reset_password extends AppCompatActivity {

    EditText txt_new_pwd,txt_conf_pwd;
    TextView newpwd,confpwd,success_txt,toolbar_title;
    Toolbar toolbar;
    SaveData saveData;
    Button new_pwd_btn;
    ResetPassword resetPassword;
    Typeface regular,regularMon;
    String message ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_activity);

        saveData = new SaveData(getApplicationContext());
        Intent intent = getIntent();
       message = intent.getStringExtra("otp_email");

        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(),"Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(),"Montserrat-Regular.otf");

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(regular);
        toolbar_title.setText(getResources().getString(R.string.frg_btn));

        toolbar= findViewById(R.id.toolbar_reset);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset = new Intent(getApplicationContext(),Login.class);
                startActivity(reset);
            }
        });

        txt_new_pwd = findViewById(R.id.new_pwd_txt);
        txt_new_pwd.setTypeface(regularMon);
        txt_conf_pwd = findViewById(R.id.conf_pwd_txt);
        txt_conf_pwd.setTypeface(regularMon);


        newpwd = findViewById(R.id.new_pwd);
        newpwd.setTypeface(regularMon);

        confpwd = findViewById(R.id.conf_pwd);
        confpwd.setTypeface(regularMon);

        success_txt = findViewById(R.id.success_msg);
        success_txt.setTypeface(regularMon);

        new_pwd_btn = findViewById(R.id.submit_pwd);
        new_pwd_btn.setTypeface(regular);
        new_pwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new ResetPasswordData().execute();
            }
        });
    }

    private class ResetPasswordData extends AsyncTask<Void,Void,Void>{

        ResetPassword.User user;

        @Override
        protected void onPreExecute() {
            user = new ResetPassword().new User();
            user.setEmail(message);
            user.setPassword(txt_new_pwd.getText().toString());
            user.setConfirmPassword(txt_conf_pwd.getText().toString());

        }

        @Override
        protected Void doInBackground(Void... voids) {
            resetPassword = new ResetPassword();
            resetPassword.setUser(user);


            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseBody> response = service.passwordReset(resetPassword);
            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                    boolean success = response.isSuccessful();
                    if (!success) {
                        switch (response.code()) {
                            case 404:
                                Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_LONG).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                break;
                        }
                    } else {
                        Intent backToLogin = new Intent(getApplicationContext(), Login.class);
                        success_txt.setVisibility(View.VISIBLE);
                        startActivity(backToLogin);
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    call.cancel();
                    Log.e("TAG", "onFailure: " + t.toString());

                }
            });




            return null;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText)
        {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }
}
