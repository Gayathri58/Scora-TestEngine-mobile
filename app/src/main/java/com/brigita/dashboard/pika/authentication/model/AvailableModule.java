package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/*
 * model class for user details response
 */

public class AvailableModule {
    @SerializedName("module_name")
    @Expose
    public String moduleName;
    @SerializedName("module_id")
    @Expose
    public Integer moduleId;
    @SerializedName("access")
    @Expose
    public Boolean access;
}
