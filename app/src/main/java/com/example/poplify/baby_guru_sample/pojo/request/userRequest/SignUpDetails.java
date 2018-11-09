package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpDetails {

@SerializedName("user")
@Expose
private User user;

/**
* No args constructor for use in serialization
* 
*/
public SignUpDetails() {
}

/**
* 
* @param user
*/
public SignUpDetails(User user) {
super();
this.user = user;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public SignUpDetails withUser(User user) {
this.user = user;
return this;
}

    public class User {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role_id")
        @Expose
        private Integer role_id;
        @SerializedName("password_confirmation")
        @Expose
        private String password_confirmation;



        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }



        /**
         *
         * @param email
         * @param name
         * @param password
         * @param role_id
         *
         */
        public User(String name, String email, String password, Integer role_id,String password_confirmation) {
            super();
            this.name = name;
            this.email = email;
            this.password = password;
            this.role_id = role_id;
            this.password_confirmation = password_confirmation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User withName(String name) {
            this.name = name;
            return this;
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

        public String getPassword_confirmation() {
            return password_confirmation;
        }

        public void setPassword_confirmation(String password_confirmation) {
            this.password_confirmation = password_confirmation;
        }

        public Integer getRole_id() {
            return role_id;
        }

        public void setRole_id(Integer role_id) {
            this.role_id = role_id;
        }

        public User withRole_id(Integer role_id) {
            this.role_id = role_id;
            return this;
        }


    }

}