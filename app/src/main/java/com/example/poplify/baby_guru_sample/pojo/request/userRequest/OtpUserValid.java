package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpUserValid {

    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     */
    public OtpUserValid() {
    }

    /**
     * @param user
     */
    public OtpUserValid(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OtpUserValid withUser(User user) {
        this.user = user;
        return this;
    }


    public class User {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("otp")
        @Expose
        private Integer otp;

        /**
         * No args constructor for use in serialization
         */
        public User() {
        }

        /**
         * @param email
         * @param otp
         */
        public User(String email, Integer otp) {
            super();
            this.email = email;
            this.otp = otp;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public User withEmail(String email) {
            this.email = email;
            return this;
        }

        public Integer getOtp() {
            return otp;
        }

        public void setOtp(Integer otp) {
            this.otp = otp;
        }

        public User withOtp(Integer otp) {
            this.otp = otp;
            return this;
        }

    }
}
