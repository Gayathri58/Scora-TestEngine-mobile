package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestAttempt {

    @SerializedName("Current_Attempt")
    @Expose
    public Integer currentAttempt;

    public Integer getCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(Integer currentAttempt) {
        this.currentAttempt = currentAttempt;
    }
}
