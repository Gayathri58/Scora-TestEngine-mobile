package com.brigita.dashboard.pika.scheduled_tests.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduledTestResponse {

    @SerializedName("scheduled_tests")
    @Expose
    public List<ScheduledTest> scheduledTests = null;
    @SerializedName("timezone")
    @Expose
    public String timezone;

    public List<ScheduledTest> getScheduledTests() {
        return scheduledTests;
    }

    public String getTimezone() {
        return timezone;
    }
}
