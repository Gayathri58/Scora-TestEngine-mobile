package com.brigita.dashboard.pika.test_history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 03-07-2018.
 */

public class TestHistoryResponse {
    @SerializedName("test_dtl")
    @Expose
    public List<TestDtl> testDtl = null;
    @SerializedName("timezone")
    @Expose
    public String timezone;


    public List<TestDtl> getTestDtl() {
        return testDtl;
    }

    public String getTimezone() {
        return timezone;
    }
}
