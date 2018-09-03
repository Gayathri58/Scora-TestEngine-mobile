package com.brigita.dashboard.pika.test_instruction.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstructionRequest {

    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("org_id")
    @Expose
    private Integer orgId;
    @SerializedName("user_id")
    @Expose
    private Object userId;

    public InstructionRequest(Integer testId, Integer orgId, Object userId) {
        this.testId = testId;
        this.orgId = orgId;
        this.userId = userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

}
