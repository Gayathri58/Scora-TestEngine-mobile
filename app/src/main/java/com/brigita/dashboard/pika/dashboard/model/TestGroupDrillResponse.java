package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestGroupDrillResponse {


    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("grp_id")
    @Expose
    public Integer grpId;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("user_score")
    @Expose
    public String userScore;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }
}
