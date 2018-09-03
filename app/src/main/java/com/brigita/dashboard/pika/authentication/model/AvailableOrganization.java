package com.brigita.dashboard.pika.authentication.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/*
 * model class for user details response
 */

public class AvailableOrganization {

    @SerializedName("organization_img")
    @Expose
    public String organizationImg;
    @SerializedName("organization_name")
    @Expose
    public String organizationName;
    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("organization_roles")
    @Expose
    public List<String> organizationRoles = null;
    @SerializedName("applications")
    @Expose
    public List<Application> applications = null;
    @SerializedName("default")
    @Expose
    public boolean _default;
}
