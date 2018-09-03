package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 13-06-2018.
 */

public class RecentActivityLog {

    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("test_instance_id")
    @Expose
    public Integer testInstanceId;
    @SerializedName("Test_Date")
    @Expose
    public String testDate;
    @SerializedName("percent_score")
    @Expose
    public String percentScore;

    public Integer getOrgId() {
        return orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getTestInstanceId() {
        return testInstanceId;
    }

    public String getTestDate() {
        return testDate;
    }

    public String getPercentScore() {
        return percentScore;
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

    public void setTestInstanceId(Integer testInstanceId) {
        this.testInstanceId = testInstanceId;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public void setPercentScore(String percentScore) {
        this.percentScore = percentScore;
    }
}
