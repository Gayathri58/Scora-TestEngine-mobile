package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/*
 * model class for user details response
 */

public class UserLoginDetails {

    @SerializedName("profile_name")
    @Expose
    public String profileName;
    @SerializedName("profile_picture")
    @Expose
    public String profilePicture;
    @SerializedName("profile_email")
    @Expose
    public String profileEmail;
    @SerializedName("profile_organization_text")
    @Expose
    public String profileOrganizationText;
    @SerializedName("available_organization")
    @Expose
    public List<AvailableOrganization> availableOrganization = null;
}
