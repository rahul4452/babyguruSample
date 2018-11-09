package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassword {

@SerializedName("user")
@Expose
private User user;

/**
* No args constructor for use in serialization
* 
*/
public ResetPassword() {
}

/**
* 
* @param user
*/
public ResetPassword(User user) {
super();
this.user = user;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public ResetPassword withUser(User user) {
this.user = user;
return this;
}

    public class User {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("confirm_password")
        @Expose
        private String confirmPassword;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         *
         * @param confirmPassword
         * @param email
         * @param password
         */
        public User(String email, String password, String confirmPassword) {
            super();
            this.email = email;
            this.password = password;
            this.confirmPassword = confirmPassword;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public User withPassword(String password) {
            this.password = password;
            return this;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public User withConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

    }
}