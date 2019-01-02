package com.example.poplify.baby_guru_sample.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMessageResponse {
        @SerializedName("message")
        @Expose
        private String message;

    public MyMessageResponse(String message) {
        super();
        this.message = message;
    }

    public MyMessageResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
