package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestAttemptResponse {

    @SerializedName("test_id")
    @Expose
    public String testId;
    @SerializedName("attempts")
    @Expose
    public List<TestAttempt> attempts = null;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public List<TestAttempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<TestAttempt> attempts) {
        this.attempts = attempts;
    }
}
