package com.example.poplify.baby_guru_sample.pojo.response.userResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserDetails {

@SerializedName("user")
@Expose
private User user;
@SerializedName("relations")
@Expose
private List<Relation> relations = null;
@SerializedName("children")
@Expose
private List<Child> children = null;
@SerializedName("show_invite_button")
@Expose
private Boolean showInviteButton;
@SerializedName("invite_detail")
@Expose
private Object inviteDetail;
@SerializedName("invite_label")
@Expose
private String inviteLabel;
@SerializedName("user_labels")
@Expose
private UserLabels userLabels;

/**
* No args constructor for use in serialization
* 
*/
public GetUserDetails() {
}

/**
* 
* @param userLabels
* @param inviteLabel
* @param relations
* @param showInviteButton
* @param children
* @param user
* @param inviteDetail
*/
public GetUserDetails(User user, List<Relation> relations, List<Child> children, Boolean showInviteButton, Object inviteDetail, String inviteLabel, UserLabels userLabels) {
super();
this.user = user;
this.relations = relations;
this.children = children;
this.showInviteButton = showInviteButton;
this.inviteDetail = inviteDetail;
this.inviteLabel = inviteLabel;
this.userLabels = userLabels;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public GetUserDetails withUser(User user) {
this.user = user;
return this;
}

public List<Relation> getRelations() {
return relations;
}

public void setRelations(List<Relation> relations) {
this.relations = relations;
}

public GetUserDetails withRelations(List<Relation> relations) {
this.relations = relations;
return this;
}

public List<Child> getChildren() {
return children;
}

public void setChildren(List<Child> children) {
this.children = children;
}

public GetUserDetails withChildren(List<Child> children) {
this.children = children;
return this;
}

public Boolean getShowInviteButton() {
return showInviteButton;
}

public void setShowInviteButton(Boolean showInviteButton) {
this.showInviteButton = showInviteButton;
}

public GetUserDetails withShowInviteButton(Boolean showInviteButton) {
this.showInviteButton = showInviteButton;
return this;
}

public Object getInviteDetail() {
return inviteDetail;
}

public void setInviteDetail(Object inviteDetail) {
this.inviteDetail = inviteDetail;
}

public GetUserDetails withInviteDetail(Object inviteDetail) {
this.inviteDetail = inviteDetail;
return this;
}

public String getInviteLabel() {
return inviteLabel;
}

public void setInviteLabel(String inviteLabel) {
this.inviteLabel = inviteLabel;
}

public GetUserDetails withInviteLabel(String inviteLabel) {
this.inviteLabel = inviteLabel;
return this;
}

public UserLabels getUserLabels() {
return userLabels;
}

public void setUserLabels(UserLabels userLabels) {
this.userLabels = userLabels;
}

public GetUserDetails withUserLabels(UserLabels userLabels) {
this.userLabels = userLabels;
return this;
}

//-----------------------------------com.example..Buttons.java-----------------------------------

    public class Buttons {

        @SerializedName("edit")
        @Expose
        private String edit;
        @SerializedName("change_password")
        @Expose
        private String changePassword;
        @SerializedName("contact_us")
        @Expose
        private String contactUs;
        @SerializedName("legal_agreement")
        @Expose
        private String legalAgreement;
        @SerializedName("change_language")
        @Expose
        private String changeLanguage;
        @SerializedName("logout")
        @Expose
        private String logout;
        @SerializedName("send_invite")
        @Expose
        private String sendInvite;
        @SerializedName("manage_invite")
        @Expose
        private String manageInvite;
        @SerializedName("remove_invite")
        @Expose
        private String removeInvite;
        @SerializedName("cancel")
        @Expose
        private String cancel;
        @SerializedName("faq")
        @Expose
        private String faq;

        /**
         * No args constructor for use in serialization
         *
         */
        public Buttons() {
        }

        /**
         *
         * @param removeInvite
         * @param logout
         * @param legalAgreement
         * @param sendInvite
         * @param faq
         * @param edit
         * @param contactUs
         * @param cancel
         * @param changePassword
         * @param manageInvite
         * @param changeLanguage
         */
        public Buttons(String edit, String changePassword, String contactUs, String legalAgreement, String changeLanguage, String logout, String sendInvite, String manageInvite, String removeInvite, String cancel, String faq) {
            super();
            this.edit = edit;
            this.changePassword = changePassword;
            this.contactUs = contactUs;
            this.legalAgreement = legalAgreement;
            this.changeLanguage = changeLanguage;
            this.logout = logout;
            this.sendInvite = sendInvite;
            this.manageInvite = manageInvite;
            this.removeInvite = removeInvite;
            this.cancel = cancel;
            this.faq = faq;
        }

        public String getEdit() {
            return edit;
        }

        public void setEdit(String edit) {
            this.edit = edit;
        }

        public Buttons withEdit(String edit) {
            this.edit = edit;
            return this;
        }

        public String getChangePassword() {
            return changePassword;
        }

        public void setChangePassword(String changePassword) {
            this.changePassword = changePassword;
        }

        public Buttons withChangePassword(String changePassword) {
            this.changePassword = changePassword;
            return this;
        }

        public String getContactUs() {
            return contactUs;
        }

        public void setContactUs(String contactUs) {
            this.contactUs = contactUs;
        }

        public Buttons withContactUs(String contactUs) {
            this.contactUs = contactUs;
            return this;
        }

        public String getLegalAgreement() {
            return legalAgreement;
        }

        public void setLegalAgreement(String legalAgreement) {
            this.legalAgreement = legalAgreement;
        }

        public Buttons withLegalAgreement(String legalAgreement) {
            this.legalAgreement = legalAgreement;
            return this;
        }

        public String getChangeLanguage() {
            return changeLanguage;
        }

        public void setChangeLanguage(String changeLanguage) {
            this.changeLanguage = changeLanguage;
        }

        public Buttons withChangeLanguage(String changeLanguage) {
            this.changeLanguage = changeLanguage;
            return this;
        }

        public String getLogout() {
            return logout;
        }

        public void setLogout(String logout) {
            this.logout = logout;
        }

        public Buttons withLogout(String logout) {
            this.logout = logout;
            return this;
        }

        public String getSendInvite() {
            return sendInvite;
        }

        public void setSendInvite(String sendInvite) {
            this.sendInvite = sendInvite;
        }

        public Buttons withSendInvite(String sendInvite) {
            this.sendInvite = sendInvite;
            return this;
        }

        public String getManageInvite() {
            return manageInvite;
        }

        public void setManageInvite(String manageInvite) {
            this.manageInvite = manageInvite;
        }

        public Buttons withManageInvite(String manageInvite) {
            this.manageInvite = manageInvite;
            return this;
        }

        public String getRemoveInvite() {
            return removeInvite;
        }

        public void setRemoveInvite(String removeInvite) {
            this.removeInvite = removeInvite;
        }

        public Buttons withRemoveInvite(String removeInvite) {
            this.removeInvite = removeInvite;
            return this;
        }

        public String getCancel() {
            return cancel;
        }

        public void setCancel(String cancel) {
            this.cancel = cancel;
        }

        public Buttons withCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public String getFaq() {
            return faq;
        }

        public void setFaq(String faq) {
            this.faq = faq;
        }

        public Buttons withFaq(String faq) {
            this.faq = faq;
            return this;
        }

    }
    // -----------------------------------com.example..Child.java-----------------------------------
    public class Child {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender_id")
        @Expose
        private Integer genderId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("gender")
        @Expose
        private List<Gender> gender = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public Child() {
        }

        /**
         *
         * @param id
         * @param userId
         * @param dob
         * @param name
         * @param deletedAt
         * @param gender
         * @param genderId
         */
        public Child(Integer id, String name, String dob, Integer genderId, Integer userId, Object deletedAt, List<Gender> gender) {
            super();
            this.id = id;
            this.name = name;
            this.dob = dob;
            this.genderId = genderId;
            this.userId = userId;
            this.deletedAt = deletedAt;
            this.gender = gender;
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

        public Integer getGenderId() {
            return genderId;
        }

        public void setGenderId(Integer genderId) {
            this.genderId = genderId;
        }

        public Child withGenderId(Integer genderId) {
            this.genderId = genderId;
            return this;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Child withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public Child withDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
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

    }

  //-----------------------------------com.example..Gender.java-----------------------------------

    public class Gender {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("selected")
        @Expose
        private Boolean selected;
        @SerializedName("default_name")
        @Expose
        private String defaultName;

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
        public Gender(Integer id, String name, Boolean selected, String defaultName) {
            super();
            this.id = id;
            this.name = name;
            this.selected = selected;
            this.defaultName = defaultName;
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

    }
   // -----------------------------------com.example..Header.java-----------------------------------

    public class Header {

        @SerializedName("user_profile")
        @Expose
        private String userProfile;

        /**
         * No args constructor for use in serialization
         *
         */
        public Header() {
        }

        /**
         *
         * @param userProfile
         */
        public Header(String userProfile) {
            super();
            this.userProfile = userProfile;
        }

        public String getUserProfile() {
            return userProfile;
        }

        public void setUserProfile(String userProfile) {
            this.userProfile = userProfile;
        }

        public Header withUserProfile(String userProfile) {
            this.userProfile = userProfile;
            return this;
        }

    }

   // -----------------------------------com.example..Labels.java-----------------------------------

    public class Labels {

        @SerializedName("mother")
        @Expose
        private String mother;
        @SerializedName("father")
        @Expose
        private String father;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("my_children")
        @Expose
        private String myChildren;
        @SerializedName("email_address")
        @Expose
        private String emailAddress;
        @SerializedName("add_child")
        @Expose
        private String addChild;
        @SerializedName("newsletter")
        @Expose
        private String newsletter;
        @SerializedName("subscribed")
        @Expose
        private String subscribed;
        @SerializedName("unsubscribed")
        @Expose
        private String unsubscribed;

        /**
         * No args constructor for use in serialization
         *
         */
        public Labels() {
        }

        /**
         *
         * @param unsubscribed
         * @param details
         * @param mother
         * @param newsletter
         * @param addChild
         * @param emailAddress
         * @param subscribed
         * @param myChildren
         * @param father
         */
        public Labels(String mother, String father, String details, String myChildren, String emailAddress, String addChild, String newsletter, String subscribed, String unsubscribed) {
            super();
            this.mother = mother;
            this.father = father;
            this.details = details;
            this.myChildren = myChildren;
            this.emailAddress = emailAddress;
            this.addChild = addChild;
            this.newsletter = newsletter;
            this.subscribed = subscribed;
            this.unsubscribed = unsubscribed;
        }

        public String getMother() {
            return mother;
        }

        public void setMother(String mother) {
            this.mother = mother;
        }

        public Labels withMother(String mother) {
            this.mother = mother;
            return this;
        }

        public String getFather() {
            return father;
        }

        public void setFather(String father) {
            this.father = father;
        }

        public Labels withFather(String father) {
            this.father = father;
            return this;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Labels withDetails(String details) {
            this.details = details;
            return this;
        }

        public String getMyChildren() {
            return myChildren;
        }

        public void setMyChildren(String myChildren) {
            this.myChildren = myChildren;
        }

        public Labels withMyChildren(String myChildren) {
            this.myChildren = myChildren;
            return this;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public Labels withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public String getAddChild() {
            return addChild;
        }

        public void setAddChild(String addChild) {
            this.addChild = addChild;
        }

        public Labels withAddChild(String addChild) {
            this.addChild = addChild;
            return this;
        }

        public String getNewsletter() {
            return newsletter;
        }

        public void setNewsletter(String newsletter) {
            this.newsletter = newsletter;
        }

        public Labels withNewsletter(String newsletter) {
            this.newsletter = newsletter;
            return this;
        }

        public String getSubscribed() {
            return subscribed;
        }

        public void setSubscribed(String subscribed) {
            this.subscribed = subscribed;
        }

        public Labels withSubscribed(String subscribed) {
            this.subscribed = subscribed;
            return this;
        }

        public String getUnsubscribed() {
            return unsubscribed;
        }

        public void setUnsubscribed(String unsubscribed) {
            this.unsubscribed = unsubscribed;
        }

        public Labels withUnsubscribed(String unsubscribed) {
            this.unsubscribed = unsubscribed;
            return this;
        }

    }

    //-----------------------------------com.example..Relation.java-----------------------------------

    public class Relation {

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
        public Relation() {
        }

        /**
         *
         * @param defaultName
         * @param id
         * @param selected
         * @param name
         */
        public Relation(Integer id, String defaultName, String name, Boolean selected) {
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

        public Relation withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public void setDefaultName(String defaultName) {
            this.defaultName = defaultName;
        }

        public Relation withDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Relation withName(String name) {
            this.name = name;
            return this;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public Relation withSelected(Boolean selected) {
            this.selected = selected;
            return this;
        }

    }

    //-----------------------------------com.example..User.java-----------------------------------

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
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
        public User(Integer id, String email, String name, String imageUrl, Boolean newsletterSubscribed) {
            super();
            this.id = id;
            this.email = email;
            this.name = name;
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

   // -----------------------------------com.example..UserLabels.java-----------------------------------
   public class UserLabels {

       @SerializedName("header")
       @Expose
       private Header header;
       @SerializedName("labels")
       @Expose
       private Labels labels;
       @SerializedName("buttons")
       @Expose
       private Buttons buttons;

       /**
        * No args constructor for use in serialization
        *
        */
       public UserLabels() {
       }

       /**
        *
        * @param labels
        * @param buttons
        * @param header
        */
       public UserLabels(Header header, Labels labels, Buttons buttons) {
           super();
           this.header = header;
           this.labels = labels;
           this.buttons = buttons;
       }

       public Header getHeader() {
           return header;
       }

       public void setHeader(Header header) {
           this.header = header;
       }

       public UserLabels withHeader(Header header) {
           this.header = header;
           return this;
       }

       public Labels getLabels() {
           return labels;
       }

       public void setLabels(Labels labels) {
           this.labels = labels;
       }

       public UserLabels withLabels(Labels labels) {
           this.labels = labels;
           return this;
       }

       public Buttons getButtons() {
           return buttons;
       }

       public void setButtons(Buttons buttons) {
           this.buttons = buttons;
       }

       public UserLabels withButtons(Buttons buttons) {
           this.buttons = buttons;
           return this;
       }

   }
}