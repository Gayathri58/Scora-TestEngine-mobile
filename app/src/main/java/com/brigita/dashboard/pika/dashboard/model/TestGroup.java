package com.brigita.dashboard.pika.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 13-06-2018.
 */

public class TestGroup {

    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("grp_id")
    @Expose
    public Integer grpId;
    @SerializedName("grp_name")
    @Expose
    public String grpName;
    @SerializedName("No_of_Tests")
    @Expose
    public Integer noOfTests;

    public Integer getOrgId() {
        return orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public String getGrpName() {
        return grpName;
    }

    public Integer getNoOfTests() {
        return noOfTests;
    }
}
