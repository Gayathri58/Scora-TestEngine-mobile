package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * model class for Sign In request
 */

public class SignInRequest {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("client_id")
    @Expose
    public Integer clientId;
    @SerializedName("client_secret")
    @Expose
    public String clientSecret;
    @SerializedName("grant_type")
    @Expose
    public String grantType;

    public SignInRequest(String email, String password, Integer clientId, String clientSecret, String grantType) {
        this.email = email;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }




}
