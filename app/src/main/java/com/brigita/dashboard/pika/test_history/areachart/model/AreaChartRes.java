package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaChartRes {


    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("current_attempt")
    @Expose
    public Integer currentAttempt;
    @SerializedName("schedule_id")
    @Expose
    public Integer scheduleId;
    @SerializedName("End_Time")
    @Expose
    public String endTime;
    @SerializedName("percent_score")
    @Expose
    public String percentScore;


    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(Integer currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPercentScore() {
        return percentScore;
    }

    public void setPercentScore(String percentScore) {
        this.percentScore = percentScore;
    }

    public AreaChartRes(String testName, Integer orgId, Integer testId, Integer currentAttempt, Integer scheduleId, String endTime, String percentScore) {
        this.testName = testName;
        this.orgId = orgId;
        this.testId = testId;
        this.currentAttempt = currentAttempt;
        this.scheduleId = scheduleId;
        this.endTime = endTime;
        this.percentScore = percentScore;
    }
}
