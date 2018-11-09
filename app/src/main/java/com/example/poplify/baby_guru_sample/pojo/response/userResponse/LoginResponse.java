package com.example.poplify.baby_guru_sample.pojo.response.userResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("authentication_token")
@Expose
private String authenticationToken;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("role_id")
@Expose
private Integer roleId;
@SerializedName("show_reset_password")
@Expose
private Boolean showResetPassword;



/**
* No args constructor for use in serialization
*
*/
public LoginResponse() {
}

/**
*
* @param id
* @param showResetPassword
* @param email
* @param name
* @param authenticationToken
* @param roleId
*/
public LoginResponse(Integer id, String authenticationToken, String name, String email, Integer roleId, Boolean showResetPassword) {
super();
this.id = id;
this.authenticationToken = authenticationToken;
this.name = name;
this.email = email;
this.roleId = roleId;
this.showResetPassword = showResetPassword;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public LoginResponse withId(Integer id) {
this.id = id;
return this;
}

public String getAuthenticationToken() {
return authenticationToken;
}

public void setAuthenticationToken(String authenticationToken) {
this.authenticationToken = authenticationToken;
}

public LoginResponse withAuthenticationToken(String authenticationToken) {
this.authenticationToken = authenticationToken;
return this;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public LoginResponse withName(String name) {
this.name = name;
return this;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public LoginResponse withEmail(String email) {
this.email = email;
return this;
}

public Integer getRoleId() {
return roleId;
}

public void setRoleId(Integer roleId) {
this.roleId = roleId;
}

public LoginResponse withRoleId(Integer roleId) {
this.roleId = roleId;
return this;
}

public Boolean getShowResetPassword() {
return showResetPassword;
}

public void setShowResetPassword(Boolean showResetPassword) {
this.showResetPassword = showResetPassword;
}

public LoginResponse withShowResetPassword(Boolean showResetPassword) {
this.showResetPassword = showResetPassword;
return this;
}

}