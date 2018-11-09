package com.example.poplify.baby_guru_sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.forgot_pwd.Forgot_pwd;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.LoginDetails;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.LoginResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText login_mail, login_pwd;
    SaveData save;
    TextView frgt, toolbar_title;
    android.support.v7.widget.Toolbar toolbar;
    Button login_btn;
    String loginToken, loginEmail;
    LoginDetails.User userLogin;
    LoginDetails logged_in;
    Typeface bold, regular, regularMon;
    private String loginMailTxt;
    private String loginPwdTxt;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String regexPassword = ".{8,}";


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initLoginUI();
        setupLoginUI();
        checkNetworkConnection();


    }

    private void initLoginUI() {

        //Setting fonts
        regular = Typeface.createFromAsset(getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.otf");


        save = new SaveData(getApplicationContext());

        //setting Toolbar title
        toolbar = findViewById(R.id.toolbar_log);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(regular);
        toolbar_title.setText(getResources().getText(R.string.login));

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main1);

            }
        });

    }

    private void setupLoginUI() {
        login_mail = findViewById(R.id.log_mail);
        login_mail.setTypeface(regularMon);

        login_pwd = findViewById(R.id.log_pwd);


        frgt = findViewById(R.id.forget);
        frgt.setOnClickListener(this);
        frgt.setTypeface(regularMon);
        Spannable s = new SpannableString("Forgot Password ?");
        s.setSpan(new ForegroundColorSpan(Color.rgb(214, 153, 255)), 0, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        frgt.setText(s);


        login_btn = findViewById(R.id.log_btn);
        login_btn.setTypeface(regular);
        login_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget:
                Intent frgt = new Intent(getApplicationContext(), Forgot_pwd.class);
                startActivity(frgt);
                break;


            case R.id.log_btn:
                loginMailTxt = login_mail.getText().toString().trim();
                loginPwdTxt = login_pwd.getText().toString().trim();
                boolean emptyValid = emptyValid(loginMailTxt, loginPwdTxt);
                if (emptyValid) {

                    userLogin = new LoginDetails().new User();
                    userLogin.setEmail(login_mail.getText().toString().trim());
                    userLogin.setPassword(login_pwd.getText().toString().trim());

                    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                    String token_header = save.get("token");
                    String email_header = save.get("email");

                    logged_in = new LoginDetails();
                    logged_in.setUser(userLogin);

                    Call<LoginResponse> user = service.userLogin(token_header, email_header, logged_in);
                    user.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            boolean success = response.isSuccessful();
                            if (!success) {
                                switch (response.code()) {
                                    case 401:
                                        try {
                                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                                            TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                            snackbar.setActionTextColor(Color.WHITE);
                                            View snackbarView = snackbar.getView();
                                            snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                            TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                            textView.setTextColor(Color.WHITE);
                                            textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                            snackbar.show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        break;
                                    case 400:
                                        try {
                                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                                            TSnackbar snackbar2 = TSnackbar.make(findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                            snackbar2.setActionTextColor(Color.WHITE);
                                            View snackbarView2 = snackbar2.getView();
                                            snackbarView2.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                            TextView textView2 = snackbarView2.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                            textView2.setTextColor(Color.WHITE);
                                            textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                                            snackbar2.show();
                                           // Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    default:
                                       // Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                        break;
                                }
                            } else {
                                loginToken = response.body().getAuthenticationToken();
                                loginEmail = response.body().getEmail();
                                save.save("login_token", loginToken);
                                save.save("login_email", loginEmail);
                                Intent login = new Intent(getApplicationContext(), Bottom_tabs.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            call.cancel();
                            Toast.makeText(getApplicationContext(), "not login", Toast.LENGTH_LONG).show();
                        }
                    });
                   // Toast.makeText(getApplicationContext(), "all true", Toast.LENGTH_LONG).show();
                }

                //Intent pay= new Intent(getApplicationContext(),Bottom_tabs.class);
                //startActivity(pay);
                break;
            default:
              //  Toast.makeText(getApplicationContext(), "Selct button", Toast.LENGTH_LONG);

        }
    }

    private boolean emptyValid(String loginMailTxt, String loginPwdTxt) {
        if (!loginMailTxt.matches("")) {
            if (!loginMailTxt.matches(emailPattern)) {
                login_mail.setError(getResources().getString(R.string.invalid_email));
                login_mail.setBackgroundResource(R.drawable.error_bg);
                login_mail.requestFocus();
                return false;
            } else if (!loginPwdTxt.matches("")) {

                if (!loginPwdTxt.matches(regexPassword)) {
                    login_pwd.setError(getResources().getString(R.string.invalid_password));
                    login_pwd.setBackgroundResource(R.drawable.error_bg);
                    login_pwd.requestFocus();
                    return false;
                } else {
                    login_pwd.setBackgroundResource(R.drawable.button_with_border);
                    return true;
                }
            } else {
                login_mail.setBackgroundResource(R.drawable.button_with_border);
                login_pwd.setError(getResources().getString(R.string.invalid_password));
                login_pwd.setBackgroundResource(R.drawable.error_bg);
                login_pwd.requestFocus();
                return false;
            }

        } else {
            //login_mail.setBackgroundResource(R.drawable.button_with_border);
            login_mail.setError(getResources().getString(R.string.invalid_email));
            login_mail.setBackgroundResource(R.drawable.error_bg);
            login_mail.requestFocus();
        }
        return false;
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"
        } else {
            isConnected = false;
            Toast.makeText(getApplicationContext(), "Net is not connected", Toast.LENGTH_LONG);
        }

        return isConnected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    /* @Override
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
    }*/


}
