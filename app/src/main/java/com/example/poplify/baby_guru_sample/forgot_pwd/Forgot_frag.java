package com.example.poplify.baby_guru_sample.forgot_pwd;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.OtpUser;

import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forgot_frag extends Fragment {


    private Unbinder unbinder;
    Typeface regular, regularMon;
    TextView frgt_text, frgt_mail;
    Button reset_pwd_btn;
    SaveData saveData;
    OtpUser otpUser;
    String getText = null;
    Bundle bundle;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private static final String TAG = "Forgot_frag";

    public Forgot_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.forgot_fragment, container, false);
        bundle = new Bundle();
        saveData = new SaveData(getContext());
        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        frgt_text = view.findViewById(R.id.forget_text);
        frgt_text.setTypeface(regular);


        frgt_mail = view.findViewById(R.id.forget_mail);
        frgt_mail.setTypeface(regular);


        unbinder = ButterKnife.bind(this, view);

        reset_pwd_btn = view.findViewById(R.id.reset_btn);
        reset_pwd_btn.setTypeface(regular);


        checkNetworkConnection();


        reset_pwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    getText = frgt_mail.getText().toString();
                    boolean validEmail = emailValidation(getText);

                    if (validEmail) {
                        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                        String token_header = saveData.get("token");
                        String email_header = saveData.get("email");

                        otpUser = new OtpUser();


                        OtpUser.User user = new OtpUser().new User();
                        user.setEmail(getText);
                        otpUser.setUser(user);

                        Call<ResponseBody> call = service.getOtp(token_header, email_header, otpUser);

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call call, Response response) {

                                boolean success = response.isSuccessful();
                                if (!success) {
                                    switch (response.code()) {
                                        case 400:
                                            try {
                                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                TSnackbar snackbar = TSnackbar.make(view.findViewById(R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
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
                                            Toast.makeText(getContext(), "server broken", Toast.LENGTH_LONG).show();
                                            break;
                                        default:
                                            Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                            break;
                                    }

                                } else {


                                    Log.e("TAG", "response 33: " + response.body().toString());
                                    saveData.save("otpEmail", getText);
                                    Forgot_pwd.fragmentManager.beginTransaction().replace(R.id.frag_container, new Forgot_otp()).addToBackStack("Forgot_frag").commit();
                                }

                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                                call.cancel();
                                Log.e("TAG", "onFailure: " + t.toString());
                                // Log error here since request failed
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        return view;
    }
                    /*//new SignUpDetailsRes().getAuthenticationToken();
                    //new SignUpDetailsRes().getEmail();

                    if (token_header == null && email_header == null) {
                        token_header = new LoginResponse().getAuthenticationToken();
                        email_header = new LoginResponse().getEmail();

                        */

    private boolean emailValidation(String getText) {
        if (!getText.matches("")) {
            if (!getText.matches(emailPattern)) {
                frgt_mail.setError(getResources().getString(R.string.invalid_email));
                frgt_mail.setBackgroundResource(R.drawable.error_bg);
                frgt_mail.requestFocus();
                return false;
            } else {
                frgt_mail.setBackgroundResource(R.drawable.button_with_border);
                return true;
            }
        } else {
            frgt_mail.setBackgroundResource(R.drawable.button_with_border);
            frgt_mail.setError(getResources().getString(R.string.invalid_email));
            frgt_mail.setBackgroundResource(R.drawable.error_bg);
            frgt_mail.requestFocus();
        }
        return false;
    }


    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"

            Toast.makeText(getContext(), "Net is connected", Toast.LENGTH_LONG);

        } else {
            Toast.makeText(getContext(), "Net is not connected", Toast.LENGTH_LONG);
        }

        return isConnected;
    }

    @OnClick(R.id.forget_layout)
    void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}