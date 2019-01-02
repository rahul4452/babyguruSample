package com.example.poplify.baby_guru_sample.user_Profile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.MainActivity;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    TextView oldPwdTV, newPwdTv, confNewPwdTv;
    Button submitBtn;
    EditText edOldPwd, edNewPwd, edConfNewPwdTv;
    Toolbar toolbarChngPwd;
    SaveData saveData;
    String token_header, email_header;

    private static final String TAG = "ChangePassword";
    private ResponseBody serverResponse;
    private TextView toolbar_title;
    private Typeface regular;
    private Typeface regularMon;
    private String oldPwd, newPwd, confirmPwd;
    private String regexPassword = ".{8,}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_acti);


        init();

        setup();
    }

    private void init() {

        saveData = new SaveData(getApplicationContext());

        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.otf");

        toolbarChngPwd = findViewById(R.id.chngPwdTB);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(regular);
        toolbar_title.setText(saveData.get("changePwd"));


        toolbarChngPwd.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarChngPwd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //header For api
        token_header = saveData.get("login_token");
        email_header = saveData.get("login_email");

    }

    private void setup() {
        //TextViews
        oldPwdTV = findViewById(R.id.oldPwdTxt);
        oldPwdTV.setTypeface(regularMon);

        newPwdTv = findViewById(R.id.newPwdTxt);
        newPwdTv.setTypeface(regularMon);

        confNewPwdTv = findViewById(R.id.confirmPwd);
        confNewPwdTv.setTypeface(regularMon);

        //Buttons
        submitBtn = findViewById(R.id.submitChngPwd);
        submitBtn.setTypeface(regular);
        submitBtn.setOnClickListener(this);

        //edittexts
        edOldPwd = findViewById(R.id.oldPwdEd);
        edNewPwd = findViewById(R.id.newPwdEd);
        edConfNewPwdTv = findViewById(R.id.confirPwdEd);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitChngPwd:
                oldPwd = edOldPwd.getText().toString().trim();
                newPwd = edNewPwd.getText().toString();
                confirmPwd = edConfNewPwdTv.getText().toString().trim();


                boolean emptyField = validFunction();

                if(emptyField) {
                    callApi();
                }

                break;
            default:
                Toast.makeText(getApplicationContext(), "another button", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validFunction() {
        if (!oldPwd.matches("")) {
            if (!oldPwd.matches(regexPassword)) {
                edOldPwd.setError(getResources().getString(R.string.invalid_password));
                edOldPwd.setBackgroundResource(R.drawable.error_bg);
                edOldPwd.requestFocus();
                return false;
            } else if (!newPwd.matches("")) {
                if (!newPwd.matches(regexPassword)) {
                    edNewPwd.setError(getResources().getString(R.string.invalid_password));
                    edNewPwd.setBackgroundResource(R.drawable.error_bg);
                    edNewPwd.requestFocus();
                    return false;
                } else if (!confirmPwd.matches("")) {
                    if (!confirmPwd.matches(newPwd)) {
                        edNewPwd.setBackgroundResource(R.drawable.button_with_border);
                        edConfNewPwdTv.setError(getResources().getString(R.string.invalid_password));
                        edConfNewPwdTv.setBackgroundResource(R.drawable.error_bg);
                        edConfNewPwdTv.requestFocus();
                        return false;
                    } else {
                        edConfNewPwdTv.setBackgroundResource(R.drawable.button_with_border);
                        return true;
                    }
                } else {
                    edConfNewPwdTv.setError(getResources().getString(R.string.invalid_password));
                    edConfNewPwdTv.setBackgroundResource(R.drawable.error_bg);
                    edConfNewPwdTv.requestFocus();
                    return false;
                }
            } else {
                edOldPwd.setBackgroundResource(R.drawable.button_with_border);
                edNewPwd.setError(getResources().getString(R.string.invalid_password));
                edNewPwd.setBackgroundResource(R.drawable.error_bg);
                edNewPwd.requestFocus();
                return false;
            }
        } else {
            edOldPwd.setError(getResources().getString(R.string.invalid_password));
            edOldPwd.setBackgroundResource(R.drawable.error_bg);
            edOldPwd.requestFocus();
            return false;
        }
    }

    private void callApi() {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        JsonObject root = new JsonObject();
        JsonObject user = new JsonObject();

        user.addProperty("password", edOldPwd.getText().toString().trim());
        user.addProperty("password_confirmation", edConfNewPwdTv.getText().toString().trim());
        user.addProperty("current_password", edNewPwd.getText().toString());


        root.add("user", user);


        Log.d(TAG, "callApi: json Value  " + root);

        retrofit2.Call<JsonObject> calling = service.chnagePassword(token_header, email_header, root);

        calling.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                boolean success = response.isSuccessful();
                if (!success) {
                    Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JsonObject changePwdObj = new JsonObject().get(response.body().toString()).getAsJsonObject();
                        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), changePwdObj.get("message").toString(), TSnackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        snackbar.show();



                        //saveData.save("login_token",changePwdObj.get());
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }

            @Override
            public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please Check User InterNet Connection", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
