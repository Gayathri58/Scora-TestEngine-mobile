package com.brigita.dashboard.pika.authentication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * model class for user details response
 */

public class Application {

    @SerializedName("application_name")
    @Expose
    public String applicationName;
    @SerializedName("application_image")
    @Expose
    public String applicationImage;
    @SerializedName("application_hover_image")
    @Expose
    public String applicationHoverImage;
    @SerializedName("available_modules")
    @Expose
    public List<AvailableModule> availableModules = null;
    @SerializedName("default")
    @Expose
    public Boolean _default;
}
