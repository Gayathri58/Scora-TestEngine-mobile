package com.brigita.dashboard.pika.test_history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 03-07-2018.
 */

public class Attempt {

    @SerializedName("schedule_id")
    @Expose
    public Integer scheduleId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("test_instance_id")
    @Expose
    public Integer testInstanceId;
    @SerializedName("current_attempt")
    @Expose
    public Integer currentAttempt;
    @SerializedName("End_Time")
    @Expose
    public String endTime;
    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("test_desc")
    @Expose
    public String testDesc;
    @SerializedName("Test_Date")
    @Expose
    public String testDate;
    @SerializedName("percent_score")
    @Expose
    public String percentScore;

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public void setTestInstanceId(Integer testInstanceId) {
        this.testInstanceId = testInstanceId;
    }

    public void setCurrentAttempt(Integer currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public void setPercentScore(String percentScore) {
        this.percentScore = percentScore;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getTestInstanceId() {
        return testInstanceId;
    }

    public Integer getCurrentAttempt() {
        return currentAttempt;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestDesc() {
        return testDesc;
    }

    public String getTestDate() {
        return testDate;
    }

    public String getPercentScore() {
        return percentScore;
    }

}
