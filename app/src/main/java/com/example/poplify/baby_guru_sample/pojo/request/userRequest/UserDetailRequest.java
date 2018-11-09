package com.example.poplify.baby_guru_sample.pojo.request.userRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

public class UserDetailRequest {

    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     */
    public UserDetailRequest() {
    }

    /**
     * @param user
     */
    public UserDetailRequest(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetailRequest withUser(User user) {
        this.user = user;
        return this;
    }
    public class User {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private MultipartBody.Part image;
        @SerializedName("language_id")
        @Expose
        private Integer languageId;
        @SerializedName("relation_id")
        @Expose
        private Integer relationId;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
        }

        /**
         *
         * @param name
         * @param image
         * @param languageId
         * @param relationId
         */
        public User(String name, MultipartBody.Part image, Integer languageId, Integer relationId) {
            super();
            this.name = name;
            this.image = image;
            this.languageId = languageId;
            this.relationId = relationId;
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

        public MultipartBody.Part getImage() {
            return image;
        }

        public void setImage(MultipartBody.Part image) {
            this.image = image;
        }

        public User withImage(MultipartBody.Part image) {
            this.image = image;
            return this;
        }

        public Integer getLanguageId() {
            return languageId;
        }

        public void setLanguageId(Integer languageId) {
            this.languageId = languageId;
        }

        public User withLanguageId(Integer languageId) {
            this.languageId = languageId;
            return this;
        }

        public Integer getRelationId() {
            return relationId;
        }

        public void setRelationId(Integer relationId) {
            this.relationId = relationId;
        }

        public User withRelationId(Integer relationId) {
            this.relationId = relationId;
            return this;
        }

    }
}