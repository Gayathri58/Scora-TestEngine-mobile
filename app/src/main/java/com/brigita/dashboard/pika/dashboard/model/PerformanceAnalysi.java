package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 13-06-2018.
 */

public class PerformanceAnalysi {

    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("user_score")
    @Expose
    public String userScore;

    public Integer getOrgId() {
        return orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }
}



