package com.example.poplify.baby_guru_sample.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpDetailsRes {

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
private Integer role_id;

/**
* No args constructor for use in serialization
* 
*/
public SignUpDetailsRes() {
}

/**
* 
* @param id
* @param email
* @param name
* @param authenticationToken
* @param role_id
*/
public SignUpDetailsRes(Integer id, String authenticationToken, String name, String email, Integer role_id) {
super();
this.id = id;
this.authenticationToken = authenticationToken;
this.name = name;
this.email = email;
this.role_id = role_id;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public SignUpDetailsRes withId(Integer id) {
this.id = id;
return this;
}

public String getAuthenticationToken() {
return authenticationToken;
}

public void setAuthenticationToken(String authenticationToken) {
this.authenticationToken = authenticationToken;
}

public SignUpDetailsRes withAuthenticationToken(String authenticationToken) {
this.authenticationToken = authenticationToken;
return this;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public SignUpDetailsRes withName(String name) {
this.name = name;
return this;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public SignUpDetailsRes withEmail(String email) {
this.email = email;
return this;
}

public Integer getRole_id() {
        return role_id;
    }

public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public SignUpDetailsRes withRoleId(Integer roleId) {
this.role_id = roleId;
return this;
}

}