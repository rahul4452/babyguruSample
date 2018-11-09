package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//********************************//

//========Generate Otp===========//

//*******************************/

public class OtpUser {


    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     *
     */
    public OtpUser() {
    }

    /**
     *
     * @param user
     */
    public OtpUser(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OtpUser withUser(User user) {
        this.user = user;
        return this;
    }

    public class User {

        @SerializedName("email")
        @Expose
        private String email;

        /**
         * No args constructor for use in serialization
         */
        public User() {
        }

        /**
         * @param email
         */
        public User(String email) {
            super();
            this.email = email;
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

    }
}