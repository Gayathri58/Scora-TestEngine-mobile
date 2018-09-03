package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestRecentActivityDrillResponse {@SerializedName("user_id")
@Expose
public Integer userId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("Test_Dtl")
    @Expose
    public String testDtl;
    @SerializedName("Percentage_Score")
    @Expose
    public String percentageScore;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestDtl() {
        return testDtl;
    }

    public void setTestDtl(String testDtl) {
        this.testDtl = testDtl;
    }

    public String getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(String percentageScore) {
        this.percentageScore = percentageScore;
    }
}
