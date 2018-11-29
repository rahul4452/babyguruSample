package com.example.poplify.baby_guru_sample.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class ApiClient {
    private static Retrofit retrofit = null;

    enum Urls {
        DEVELOPMENT, STAGEING, PRODUCTION;

        String getBaseUrl() {
            switch (this) {
                case DEVELOPMENT:
                    return null;
                case STAGEING:
                    return "https://babyguru-staging.poplify.com";
                case PRODUCTION:
                    return null;
            }

            return null;
        }
    }

    public static Urls BASE_URL = Urls.STAGEING;

   static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL.getBaseUrl())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
