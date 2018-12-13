package com.example.poplify.baby_guru_sample.rest;

import com.example.poplify.baby_guru_sample.pojo.request.userRequest.LoginDetails;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.OtpUser;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.OtpUserValid;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.ResetPassword;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.SignUpDetails;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.BeforeYouStartResponse;
import com.example.poplify.baby_guru_sample.pojo.response.childResponse.ChildrenResponse;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.GetUserDetails;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.LoginResponse;
import com.example.poplify.baby_guru_sample.pojo.response.SignUpDetailsRes;
import com.example.poplify.baby_guru_sample.pojo.response.userResponse.UserDetailAddResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface ApiInterface {


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1"})
    @POST("/sign_up_api")
    Call<SignUpDetailsRes> createUser(@Body SignUpDetails sign);


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/sign_in_api")
    Call<LoginResponse> userLogin(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Body LoginDetails userLogin);


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/generate_otp")
    Call<ResponseBody> getOtp(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Body OtpUser user);


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/validate_otp")
    Call<ResponseBody> validateOtp(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Body OtpUserValid otpUserValid);


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/password_reset")
    Call<ResponseBody> passwordReset(@Body ResetPassword resetPassword);


    @Multipart
    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/update_user_details")
    Call<UserDetailAddResponse> uploadMulFile(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email,
                                              @Part("user[name]") RequestBody name,
                                              @Part("user[relation_id]") RequestBody relation_id,
                                              @Part("user[language_id]") RequestBody language_id,
                                              @Part MultipartBody.Part image);


    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @GET()
    Call<GetUserDetails> getUserDetail(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Url String url);


    @Headers({"Content-Type: application/json", "Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/update_password_details")
    Call<JsonObject> chnagePassword(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Body JsonObject chngepwd);


    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @GET()
    Call<BeforeYouStartResponse> getBeforeDetail(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Url String url);


    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @GET()
    Call<CryingScaleResponse> getCryingScale(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email, @Url String url);


    @Multipart
    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @POST("/children")
    Call<ChildProfileResponse.Child> addChildFile(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email,
                                             @Part("child[name]") RequestBody name,
                                             @Part("child[dob]") RequestBody relation_id,
                                             @Part("child[gender_id]") RequestBody language_id,
                                             @Part MultipartBody.Part image);


    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @GET("/children/{id}")
    Call<ChildProfileResponse> showChildFile(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email,
                                             @Path("id") int id);


    @Multipart
    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @PUT("/children/{id}")
    Call<ChildProfileResponse.Child> updateChild(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email,
                                            @Path("id") int id,
                                            @Part("child[name]") RequestBody name,
                                            @Part("child[dob]") RequestBody dateBirth,
                                            @Part("child[gender_id]") RequestBody language_id,
                                            @Part MultipartBody.Part image);


    @Headers({"Accept: application/json", "X-OS:android", "X-Api-Version:1", "X-FIREBASE-TOKEN:token"})
    @GET("/children")
    Call<ChildrenResponse> getSelectChild(@Header("X-User-Token") String token_header, @Header("X-User-Email") String email);

}
