package com.example.poplify.baby_guru_sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.poplify.baby_guru_sample.Bottom_navbar.Bottom_tabs;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.SignUpDetails;
import com.example.poplify.baby_guru_sample.pojo.response.SignUpDetailsRes;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class Sign_up extends AppCompatActivity implements View.OnClickListener {

    TextView term_nd_condi, title, validationTXT, newsletter;
    EditText sign_name_txt, sign_pwd_txt, sign_mail_txt;
    android.support.v7.widget.Toolbar toolbar;
    Button sign_up_btn;
    SaveData saveData;
    Typeface bold, regular, regularMon;
    private String sendToken;
    private String headerEmail;
    private SignUpDetailsRes serverResponse;
    private String mailTxt, nameTxt, pwdTxt;
    Animation slideUp;
    ImageView babyImage;
    ConstraintLayout layout;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String regexPassword = ".{8,}";
    String namePattern = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
    SignUpDetails.User newUser;
    SignUpDetails sh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        init();
        setupUI();


        //awesomeValidation.setContext(this);

        validationTXT = findViewById(R.id.validTextView);


        Spannable wordtoSpan = new SpannableString("I accept the Terms & Conditions");
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 13, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        term_nd_condi.setText(wordtoSpan);


        checkNetworkConnection();


        slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_top_to_bottom);
        //setting Action Bar


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void init() {
        //butterknife
        ButterKnife.bind(this);
        regular = Typeface.createFromAsset(getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.otf");
        saveData = new SaveData(getApplicationContext());

        toolbar = findViewById(R.id.toolbar_sign);
        term_nd_condi = findViewById(R.id.span_text);
        title = findViewById(R.id.toolbar_title);
        title.setTypeface(regular);
        title.setText(getResources().getString(R.string.signup));

        //back Button on toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2 = new Intent(getApplicationContext(), MainActivity.class);
                main2.putExtra("finish", true); // if you are checking for this in your other Activities
                main2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main2);
            }
        });

        layout = findViewById(R.id.sign_layout);

        //slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_bottom);
    }

    private void setupUI() {
        sign_name_txt = findViewById(R.id.sign_in_name);

        sign_mail_txt = findViewById(R.id.sign_in_mail);
      /*  sign_mail_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mailTxt = sign_mail_txt.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (mailTxt.matches(emailPattern) && s.length() > 0){
                    validationTXT.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            validationTXT.setVisibility(View.VISIBLE);
                            validationTXT.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in));
                            validationTXT.setText("Enter a Valid Email");
                        }
                    },4000);
                    boolean focus = sign_mail_txt.requestFocus();
                    if(focus) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
            }
        });*/

        sign_pwd_txt = findViewById(R.id.sign_in_pwd);

       /* sign_pwd_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if (pwdTxt.length()<8){
                        validationTXT.setText("Enter a Strong Password");
                        if(validationTXT.getVisibility()==View.GONE) {
                            slideToBottom(validationTXT);
                        }
                    }
                }
            }
        });

        sign_pwd_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pwdTxt = sign_mail_txt.getText().toString();
                if (pwdTxt.length()<8){
                    validationTXT.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            validationTXT.setVisibility(View.VISIBLE);
                            validationTXT.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in));
                            validationTXT.setText("Enter a Strong Password");
                        }
                    },4000);
                    boolean focus = sign_pwd_txt.requestFocus();
                    if(focus) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
            }
        });*/


        sign_up_btn = findViewById(R.id.sign_up);
        sign_up_btn.setOnClickListener(this);
        sign_up_btn.setTypeface(regular);


        term_nd_condi.setTypeface(regularMon);
        sign_pwd_txt.setTypeface(regularMon);
        sign_mail_txt.setTypeface(regularMon);

        sign_name_txt.setTypeface(regularMon);

        babyImage = findViewById(R.id.signUpbaby);
        // babyImage.bringToFront();

        newsletter = findViewById(R.id.span_text2);
        newsletter.setTypeface(regularMon);


    }

    public void hideSoftKeyboard(View v) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sign_up:

                nameTxt = sign_name_txt.getText().toString().trim();
                mailTxt = sign_mail_txt.getText().toString().trim();
                pwdTxt = sign_pwd_txt.getText().toString().trim();

                boolean emptyValid = emptyValid(mailTxt, nameTxt, pwdTxt);
                // boolean patternValid = patternValidation(mailTxt,nameTxt,pwdTxt);
                if (emptyValid) {
                    newUser = new SignUpDetails().new User();
                    newUser.setName(sign_name_txt.getText().toString().trim());
                    newUser.setEmail(sign_mail_txt.getText().toString().trim());
                    newUser.setPassword(sign_pwd_txt.getText().toString().trim());
                    newUser.setPassword_confirmation(sign_pwd_txt.getText().toString().trim());
                    newUser.setRole_id(2);
                    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                    sh = new SignUpDetails();
                    sh.setUser(newUser);

                    Call<SignUpDetailsRes> user = service.createUser(sh);
                    user.enqueue(new Callback<SignUpDetailsRes>() {
                        @Override
                        public void onResponse(Call<SignUpDetailsRes> call, Response<SignUpDetailsRes> response) {
                            serverResponse = response.body();
                            boolean success = response.isSuccessful();
                            if (!success) {
                                switch (response.code()) {
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
                                    default:
                                        //Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();

                                        break;
                                }

                            } else {
                                String token = response.body().getAuthenticationToken();
                                String getEmail = response.body().getEmail();
                                Log.e("tokenTAG", "Token : " + token);
                                if (token != null) {

                                    sendToken = token;
                                    headerEmail = getEmail;
                                }
                                saveData.save("signToken", sendToken);
                                saveData.save("email", headerEmail);
                                Intent pay = new Intent(getApplicationContext(), Bottom_tabs.class);
                                pay.putExtra("finish", true); // if you are checking for this in your other Activities
                                pay.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(pay);
                                finish();
                            }
                        }


                        @Override
                        public void onFailure(Call<SignUpDetailsRes> call, Throwable t) {
                            call.cancel();
                            Toast.makeText(getApplicationContext(), "Please Check User InterNet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(getApplicationContext(), "signUp", Toast.LENGTH_LONG).show();
                    //new SignUpData().execute();
                }

                break;
            default:
                Toast.makeText(getApplicationContext(), "Sign_up", Toast.LENGTH_LONG).show();
        }

    }

    private boolean patternValidation(String mailTxt, String nameTxt, String pwdTxt) {

        if (!nameTxt.matches(namePattern)) {

            sign_name_txt.setError(getResources().getString(R.string.invalid_name));
            sign_name_txt.requestFocus();
            return false;
        } else {
            if (mailTxt.matches(emailPattern)) {
                sign_mail_txt.setError(getResources().getString(R.string.invalid_email));
                sign_mail_txt.requestFocus();
                return false;
            } else {
                if (pwdTxt.matches(regexPassword)) {
                    sign_pwd_txt.setError(getResources().getString(R.string.invalid_password));
                    sign_pwd_txt.requestFocus();
                    return false;
                } else {
                    return true;
                }
            }
        }


    }

    private boolean emptyValid(String mailTxt, String nameTxt, String pwdTxt) {
        if (!nameTxt.matches(""))
        {
            if (!nameTxt.matches(namePattern)) {
                sign_name_txt.setBackgroundResource(R.drawable.button_with_border);
                sign_name_txt.setError(getResources().getString(R.string.invalid_name));
                sign_name_txt.setBackgroundResource(R.drawable.error_bg);
                sign_name_txt.requestFocus();
                return false;
            } else if (!mailTxt.matches("")) {

                if (!mailTxt.matches(emailPattern)) {
                    sign_mail_txt.setError(getResources().getString(R.string.invalid_email));
                    sign_mail_txt.setBackgroundResource(R.drawable.error_bg);
                    sign_mail_txt.requestFocus();
                    return false;
                } else if (!pwdTxt.matches("")) {

                    if (!pwdTxt.matches(regexPassword)) {
                        sign_pwd_txt.setError(getResources().getString(R.string.invalid_password));
                        sign_pwd_txt.setBackgroundResource(R.drawable.error_bg);
                        sign_pwd_txt.requestFocus();
                        return false;
                    } else {
                        sign_pwd_txt.setBackgroundResource(R.drawable.button_with_border);
                        return true;
                    }
                } else {
                    sign_mail_txt.setBackgroundResource(R.drawable.button_with_border);
                    sign_pwd_txt.setError(getResources().getString(R.string.invalid_password));
                    sign_pwd_txt.setBackgroundResource(R.drawable.error_bg);
                    sign_pwd_txt.requestFocus();
                    return false;
                }

            } else {
                sign_name_txt.setBackgroundResource(R.drawable.button_with_border);
                sign_mail_txt.setError(getResources().getString(R.string.invalid_email));
                sign_mail_txt.setBackgroundResource(R.drawable.error_bg);
                sign_mail_txt.requestFocus();
            }
            return false;
        } else {
            sign_name_txt.setError(getResources().getString(R.string.invalid_name));
            sign_name_txt.setBackgroundResource(R.drawable.error_bg);
            sign_name_txt.requestFocus();
            return false;
        }
    }


    // To animate view slide out from top to bottom
    public void slideToBottom(View view) {
        slideUp.setDuration(1500);
        slideUp.setFillAfter(true);
        view.startAnimation(slideUp);
        view.setVisibility(View.GONE);
    }


    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"

            Toast.makeText(getApplicationContext(), "Net is connected", Toast.LENGTH_LONG);

        } else {
            Toast.makeText(getApplicationContext(), "Net is not connected", Toast.LENGTH_LONG);
        }

        return isConnected;
    }

    private class SignUpData extends AsyncTask<String, Void, String> {

        SignUpDetails.User newUser;
        SignUpDetails sh;

        @Override
        protected void onPreExecute() {


            newUser = new SignUpDetails().new User();
            newUser.setName(sign_name_txt.getText().toString().trim());
            newUser.setEmail(sign_mail_txt.getText().toString().trim());
            newUser.setPassword(sign_pwd_txt.getText().toString().trim());
            newUser.setPassword_confirmation(sign_pwd_txt.getText().toString().trim());
            newUser.setRole_id(2);


        }

        @Override
        protected String doInBackground(String... strings) {

            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            sh = new SignUpDetails();
            sh.setUser(newUser);

            if (checkNetworkConnection()) {
                Call<SignUpDetailsRes> user = service.createUser(sh);
                user.enqueue(new Callback<SignUpDetailsRes>() {
                    @Override
                    public void onResponse(Call<SignUpDetailsRes> call, Response<SignUpDetailsRes> response) {
                        serverResponse = response.body();


                        boolean success = response.isSuccessful();
                        if (!success) {
                            switch (response.code()) {
                                case 400:
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        TSnackbar snackbar2 = TSnackbar.make(findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                        snackbar2.setActionTextColor(Color.WHITE);
                                        View snackbarView2 = snackbar2.getView();
                                        snackbarView2.setBackgroundColor(getResources().getColor(R.color.light_purple));
                                        TextView textView2 = snackbarView2.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                        textView2.setTextColor(Color.WHITE);
                                        textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                                        snackbar2.show();
                                        Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                default:
                                    Toast.makeText(getApplicationContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();

                                    break;
                            }

                        } else {
                            String token = response.body().getAuthenticationToken();
                            String getEmail = response.body().getEmail();
                            Log.e("tokenTAG", "Token : " + token);
                            if (token != null) {

                                sendToken = token;
                                headerEmail = getEmail;
                            }
                            saveData.save("signToken", sendToken);
                            saveData.save("email", headerEmail);
                            Intent pay = new Intent(getApplicationContext(), Bottom_tabs.class);
                            startActivity(pay);
                        }
                    }


                    @Override
                    public void onFailure(Call<SignUpDetailsRes> call, Throwable t) {
                        call.cancel();
                        Toast.makeText(getApplicationContext(), "Please Check User InterNet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {

                validationTXT.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validationTXT.setVisibility(View.VISIBLE);
                        validationTXT.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
                        validationTXT.setText(getResources().getString(R.string.internet_valid));
                    }
                }, 4000);
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
