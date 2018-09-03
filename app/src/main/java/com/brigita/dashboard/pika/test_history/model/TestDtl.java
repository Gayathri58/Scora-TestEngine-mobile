package com.brigita.dashboard.pika.test_history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestDtl {

    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("attempts")
    @Expose
    public List<Attempt> attempts = null;

    public Integer getTestId() {
        return testId;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }
}
