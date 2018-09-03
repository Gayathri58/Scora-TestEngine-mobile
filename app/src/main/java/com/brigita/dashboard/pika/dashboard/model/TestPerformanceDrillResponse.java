package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPerformanceDrillResponse {

    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("Attempt")
    @Expose
    public String attempt;
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

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public String getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(String percentageScore) {
        this.percentageScore = percentageScore;
    }
}
