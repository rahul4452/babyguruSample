package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetails {

@SerializedName("user")
@Expose
private User user;

/**
* No args constructor for use in serialization
* 
*/
public LoginDetails() {
}

/**
* 
* @param user
*/
public LoginDetails(User user) {
super();
this.user = user;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public LoginDetails withUser(User user) {
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

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         * @param email
         * @param password
         */
        public User(String email, String password) {
            super();
            this.email = email;
            this.password = password;
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

    }
}