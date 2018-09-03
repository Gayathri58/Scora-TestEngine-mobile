package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 13-06-2018.
 */

public class TestSummary {

    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("test_status")
    @Expose
    public String testStatus;
    @SerializedName("No_of_Tests")
    @Expose
    public Integer noOfTests;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public Integer getNoOfTests() {
        return noOfTests;
    }

    public void setNoOfTests(Integer noOfTests) {
        this.noOfTests = noOfTests;
    }
}
