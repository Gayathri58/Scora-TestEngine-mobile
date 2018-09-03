package com.brigita.dashboard.pika.test_instruction.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instruction {

    @SerializedName("Org_id")
    @Expose
    private Integer orgId;
    @SerializedName("Test_ID")
    @Expose
    private Integer testID;
    @SerializedName("Test_Rule_ID")
    @Expose
    private Integer testRuleID;
    @SerializedName("Test_Rule")
    @Expose
    private String testRule;
    @SerializedName("Active_Ind")
    @Expose
    private String activeInd;
    @SerializedName("Created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("Created_DT")
    @Expose
    private String createdDT;
    @SerializedName("Last_Updated_by")
    @Expose
    private Object lastUpdatedBy;
    @SerializedName("Last_Updated_DT")
    @Expose
    private Object lastUpdatedDT;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTestID() {
        return testID;
    }

    public void setTestID(Integer testID) {
        this.testID = testID;
    }

    public Integer getTestRuleID() {
        return testRuleID;
    }

    public void setTestRuleID(Integer testRuleID) {
        this.testRuleID = testRuleID;
    }

    public String getTestRule() {
        return testRule;
    }

    public void setTestRule(String testRule) {
        this.testRule = testRule;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDT() {
        return createdDT;
    }

    public void setCreatedDT(String createdDT) {
        this.createdDT = createdDT;
    }

    public Object getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Object lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Object getLastUpdatedDT() {
        return lastUpdatedDT;
    }

    public void setLastUpdatedDT(Object lastUpdatedDT) {
        this.lastUpdatedDT = lastUpdatedDT;
    }

}
