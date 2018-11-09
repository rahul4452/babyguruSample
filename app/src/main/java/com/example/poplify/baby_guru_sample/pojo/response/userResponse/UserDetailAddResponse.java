package com.example.poplify.baby_guru_sample.pojo.response.userResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailAddResponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("user")
@Expose
private User user;

/**
* No args constructor for use in serialization
* 
*/
public UserDetailAddResponse() {
}

/**
* 
* @param message
* @param user
*/
public UserDetailAddResponse(String message, User user) {
super();
this.message = message;
this.user = user;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public UserDetailAddResponse withMessage(String message) {
this.message = message;
return this;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public UserDetailAddResponse withUser(User user) {
this.user = user;
return this;
}


    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("newsletter_subscribed")
        @Expose
        private Boolean newsletterSubscribed;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         *
         * @param id
         * @param imageUrl
         * @param email
         * @param name
         * @param newsletterSubscribed
         */
        public User(Integer id, String name, String email, String imageUrl, Boolean newsletterSubscribed) {
            super();
            this.id = id;
            this.name = name;
            this.email = email;
            this.imageUrl = imageUrl;
            this.newsletterSubscribed = newsletterSubscribed;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public User withId(Integer id) {
            this.id = id;
            return this;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public User withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Boolean getNewsletterSubscribed() {
            return newsletterSubscribed;
        }

        public void setNewsletterSubscribed(Boolean newsletterSubscribed) {
            this.newsletterSubscribed = newsletterSubscribed;
        }

        public User withNewsletterSubscribed(Boolean newsletterSubscribed) {
            this.newsletterSubscribed = newsletterSubscribed;
            return this;
        }

    }
}