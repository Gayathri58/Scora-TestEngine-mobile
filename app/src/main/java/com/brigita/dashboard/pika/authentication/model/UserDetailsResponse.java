package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class for user details response call
 */

public class UserDetailsResponse {

    @SerializedName("user_login_details")
    @Expose
    public UserLoginDetails userLoginDetails;

}
