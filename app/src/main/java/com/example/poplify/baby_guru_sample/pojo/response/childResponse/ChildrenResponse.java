package com.example.poplify.baby_guru_sample.pojo.response.childResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChildrenResponse {

@SerializedName("child")
@Expose
private List<Child> child = null;
@SerializedName("child_index_label")
@Expose
private ChildIndexLabel childIndexLabel;
@SerializedName("sub_parent_login_app")
@Expose
private Boolean subParentLoginApp;
@SerializedName("method_saved")
@Expose
private Boolean methodSaved;
@SerializedName("has_before_you")
@Expose
private Boolean hasBeforeYou;
@SerializedName("child_count")
@Expose
private Integer childCount;

/**
* No args constructor for use in serialization
* 
*/
public ChildrenResponse() {
}

/**
* 
* @param child
* @param childIndexLabel
* @param childCount
* @param hasBeforeYou
* @param subParentLoginApp
* @param methodSaved
*/
public ChildrenResponse(List<Child> child, ChildIndexLabel childIndexLabel, Boolean subParentLoginApp, Boolean methodSaved, Boolean hasBeforeYou, Integer childCount) {
super();
this.child = child;
this.childIndexLabel = childIndexLabel;
this.subParentLoginApp = subParentLoginApp;
this.methodSaved = methodSaved;
this.hasBeforeYou = hasBeforeYou;
this.childCount = childCount;
}

public List<Child> getChild() {
return child;
}

public void setChild(List<Child> child) {
this.child = child;
}

public ChildrenResponse withChild(List<Child> child) {
this.child = child;
return this;
}

public ChildIndexLabel getChildIndexLabel() {
return childIndexLabel;
}

public void setChildIndexLabel(ChildIndexLabel childIndexLabel) {
this.childIndexLabel = childIndexLabel;
}

public ChildrenResponse withChildIndexLabel(ChildIndexLabel childIndexLabel) {
this.childIndexLabel = childIndexLabel;
return this;
}

public Boolean getSubParentLoginApp() {
return subParentLoginApp;
}

public void setSubParentLoginApp(Boolean subParentLoginApp) {
this.subParentLoginApp = subParentLoginApp;
}

public ChildrenResponse withSubParentLoginApp(Boolean subParentLoginApp) {
this.subParentLoginApp = subParentLoginApp;
return this;
}

public Boolean getMethodSaved() {
return methodSaved;
}

public void setMethodSaved(Boolean methodSaved) {
this.methodSaved = methodSaved;
}

public ChildrenResponse withMethodSaved(Boolean methodSaved) {
this.methodSaved = methodSaved;
return this;
}

public Boolean getHasBeforeYou() {
return hasBeforeYou;
}

public void setHasBeforeYou(Boolean hasBeforeYou) {
this.hasBeforeYou = hasBeforeYou;
}

public ChildrenResponse withHasBeforeYou(Boolean hasBeforeYou) {
this.hasBeforeYou = hasBeforeYou;
return this;
}

public Integer getChildCount() {
return childCount;
}

public void setChildCount(Integer childCount) {
this.childCount = childCount;
}

public ChildrenResponse withChildCount(Integer childCount) {
this.childCount = childCount;
return this;
}

//-----------------------------------com.example..Child.java-----------------------------------


    public class Child {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("method_id")
        @Expose
        private Object methodId;
        @SerializedName("image_updated_at")
        @Expose
        private String imageUpdatedAt;
        @SerializedName("image_file_size")
        @Expose
        private Integer imageFileSize;
        @SerializedName("image_content_type")
        @Expose
        private String imageContentType;
        @SerializedName("image_file_name")
        @Expose
        private String imageFileName;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private List<Gender> gender = null;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("saved_method")
        @Expose
        private Boolean savedMethod;

        /**
         * No args constructor for use in serialization
         *
         */
        public Child() {
        }

        /**
         *
         * @param id
         * @param imageFileName
         * @param imageFileSize
         * @param imageUrl
         * @param dob
         * @param name
         * @param gender
         * @param imageUpdatedAt
         * @param savedMethod
         * @param imageContentType
         * @param methodId
         */
        public Child(Integer id, String name, Object methodId, String imageUpdatedAt, Integer imageFileSize, String imageContentType, String imageFileName, String dob, List<Gender> gender, String imageUrl, Boolean savedMethod) {
            super();
            this.id = id;
            this.name = name;
            this.methodId = methodId;
            this.imageUpdatedAt = imageUpdatedAt;
            this.imageFileSize = imageFileSize;
            this.imageContentType = imageContentType;
            this.imageFileName = imageFileName;
            this.dob = dob;
            this.gender = gender;
            this.imageUrl = imageUrl;
            this.savedMethod = savedMethod;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Child withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Child withName(String name) {
            this.name = name;
            return this;
        }

        public Object getMethodId() {
            return methodId;
        }

        public void setMethodId(Object methodId) {
            this.methodId = methodId;
        }

        public Child withMethodId(Object methodId) {
            this.methodId = methodId;
            return this;
        }

        public String getImageUpdatedAt() {
            return imageUpdatedAt;
        }

        public void setImageUpdatedAt(String imageUpdatedAt) {
            this.imageUpdatedAt = imageUpdatedAt;
        }

        public Child withImageUpdatedAt(String imageUpdatedAt) {
            this.imageUpdatedAt = imageUpdatedAt;
            return this;
        }

        public Integer getImageFileSize() {
            return imageFileSize;
        }

        public void setImageFileSize(Integer imageFileSize) {
            this.imageFileSize = imageFileSize;
        }

        public Child withImageFileSize(Integer imageFileSize) {
            this.imageFileSize = imageFileSize;
            return this;
        }

        public String getImageContentType() {
            return imageContentType;
        }

        public void setImageContentType(String imageContentType) {
            this.imageContentType = imageContentType;
        }

        public Child withImageContentType(String imageContentType) {
            this.imageContentType = imageContentType;
            return this;
        }

        public String getImageFileName() {
            return imageFileName;
        }

        public void setImageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
        }

        public Child withImageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
            return this;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Child withDob(String dob) {
            this.dob = dob;
            return this;
        }

        public List<Gender> getGender() {
            return gender;
        }

        public void setGender(List<Gender> gender) {
            this.gender = gender;
        }

        public Child withGender(List<Gender> gender) {
            this.gender = gender;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Child withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Boolean getSavedMethod() {
            return savedMethod;
        }

        public void setSavedMethod(Boolean savedMethod) {
            this.savedMethod = savedMethod;
        }

        public Child withSavedMethod(Boolean savedMethod) {
            this.savedMethod = savedMethod;
            return this;
        }

    }

//-----------------------------------com.example..ChildIndexLabel.java-----------------------------------



    public class ChildIndexLabel {

        @SerializedName("sleep_coaching")
        @Expose
        private String sleepCoaching;
        @SerializedName("child_index")
        @Expose
        private String childIndex;
        @SerializedName("add_child")
        @Expose
        private String addChild;
        @SerializedName("select_child")
        @Expose
        private String selectChild;

        /**
         * No args constructor for use in serialization
         *
         */
        public ChildIndexLabel() {
        }

        /**
         *
         * @param addChild
         * @param sleepCoaching
         * @param selectChild
         * @param childIndex
         */
        public ChildIndexLabel(String sleepCoaching, String childIndex, String addChild, String selectChild) {
            super();
            this.sleepCoaching = sleepCoaching;
            this.childIndex = childIndex;
            this.addChild = addChild;
            this.selectChild = selectChild;
        }

        public String getSleepCoaching() {
            return sleepCoaching;
        }

        public void setSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
        }

        public ChildIndexLabel withSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
            return this;
        }

        public String getChildIndex() {
            return childIndex;
        }

        public void setChildIndex(String childIndex) {
            this.childIndex = childIndex;
        }

        public ChildIndexLabel withChildIndex(String childIndex) {
            this.childIndex = childIndex;
            return this;
        }

        public String getAddChild() {
            return addChild;
        }

        public void setAddChild(String addChild) {
            this.addChild = addChild;
        }

        public ChildIndexLabel withAddChild(String addChild) {
            this.addChild = addChild;
            return this;
        }

        public String getSelectChild() {
            return selectChild;
        }

        public void setSelectChild(String selectChild) {
            this.selectChild = selectChild;
        }

        public ChildIndexLabel withSelectChild(String selectChild) {
            this.selectChild = selectChild;
            return this;
        }

    }

//-----------------------------------com.example..Gender.java-----------------------------------


    public class Gender {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("default_name")
        @Expose
        private String defaultName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("selected")
        @Expose
        private Boolean selected;

        /**
         * No args constructor for use in serialization
         *
         */
        public Gender() {
        }

        /**
         *
         * @param defaultName
         * @param id
         * @param selected
         * @param name
         */
        public Gender(Integer id, String defaultName, String name, Boolean selected) {
            super();
            this.id = id;
            this.defaultName = defaultName;
            this.name = name;
            this.selected = selected;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Gender withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public void setDefaultName(String defaultName) {
            this.defaultName = defaultName;
        }

        public Gender withDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Gender withName(String name) {
            this.name = name;
            return this;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public Gender withSelected(Boolean selected) {
            this.selected = selected;
            return this;
        }

    }
}