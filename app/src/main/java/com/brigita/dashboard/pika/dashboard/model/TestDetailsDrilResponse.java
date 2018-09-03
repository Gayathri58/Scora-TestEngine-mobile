package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestDetailsDrilResponse {

    @SerializedName("test_status")
    @Expose
    public String testStatus;
    @SerializedName("Test_Name")
    @Expose
    public String testName;


    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
