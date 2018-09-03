package com.brigita.dashboard.pika.test_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestDetailsResponse {

    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("org_name")
    @Expose
    public String orgName;
    @SerializedName("org_logo")
    @Expose
    public String orgLogo;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_logo")
    @Expose
    public String userLogo;
    @SerializedName("timer")
    @Expose
    public Integer timer;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }
}
