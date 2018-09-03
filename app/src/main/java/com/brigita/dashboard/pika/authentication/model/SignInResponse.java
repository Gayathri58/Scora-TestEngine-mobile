package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * model class for sign in response
 */

public class SignInResponse {

    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public Integer expiresIn;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("default_application")
    @Expose
    public String defaultApplication;
    @SerializedName("application_access")
    @Expose
    public List<String> applicationAccess = null;

    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("message")
    @Expose
    public String message;

}
