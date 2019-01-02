package com.example.poplify.baby_guru_sample.forgot_pwd;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.OtpUserValid;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;

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
public class Forgot_otp extends Fragment {

    private Unbinder unbinder;
    Typeface regular, regularMon;
    TextView text_msg, txt_enterOtp;
    EditText enter_otp;
    Button submit_otp_btn;
    String _otpEmail = null;
    OtpUserValid otpUserValid;
    SaveData saveData;


    public Forgot_otp() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.forgot_otp_frag, container, false);


        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");


        text_msg = view.findViewById(R.id.msg_otp);
        text_msg.setTypeface(regularMon);
        txt_enterOtp = view.findViewById(R.id.otp_enter);
        txt_enterOtp.setTypeface(regularMon);

        unbinder = ButterKnife.bind(this, view);
        enter_otp = view.findViewById(R.id.txt_otp);
        enter_otp.setTypeface(regularMon);


        submit_otp_btn = view.findViewById(R.id.submit_otp);
        submit_otp_btn.setTypeface(regularMon);
        submit_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData = new SaveData(getContext());
                otpUserValid = new OtpUserValid();

                try {
                    OtpUserValid.User validuser = new OtpUserValid().new User();

                    _otpEmail = saveData.getString("otpEmail");

                    validuser.setEmail(_otpEmail);
                    validuser.setOtp(Integer.parseInt(enter_otp.getText().toString()));
                    otpUserValid.setUser(validuser);

                    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                    String token_header = saveData.get("token");
                    String email_header = saveData.get("email");


                    Call<ResponseBody> user = service.validateOtp(token_header, email_header, otpUserValid);
                    user.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            boolean success = response.isSuccessful();
                            if (!success) {
                                switch (response.code()) {
                                    case 404:
                                        Toast.makeText(getContext(), "not found", Toast.LENGTH_LONG).show();
                                        break;
                                    case 500:
                                        Toast.makeText(getContext(), "server broken", Toast.LENGTH_LONG).show();
                                        break;
                                    default:
                                        Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                        break;
                                }
                            } else {
                                Intent newpwd = new Intent(getContext(), Reset_password.class);
                                newpwd.putExtra("otp_email", _otpEmail);
                                startActivity(newpwd);
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            call.cancel();
                            Log.e("TAG", "onFailure: " + t.toString());
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

    @OnClick(R.id.forget_otp_layout)
    void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

}
