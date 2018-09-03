package com.brigita.dashboard.pika.scheduled_tests.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
 * Model class for scheduled tests shown in Dashboard
 */

public class ScheduledTest {

    @SerializedName("Test_Name")
    @Expose
    public String testName;
    @SerializedName("Test_Desc")
    @Expose
    public String testDesc;
    @SerializedName("Total_Count")
    @Expose
    public Integer totalCount;
    @SerializedName("Attempt_Count")
    @Expose
    public Integer attemptCount;
    @SerializedName("scheduled_date")
    @Expose
    public String scheduledDate;
    @SerializedName("scheduled_end_date")
    @Expose
    public String scheduledEndDate;
    @SerializedName("Test_ID")
    @Expose
    public Integer testID;
    @SerializedName("Test_Status")
    @Expose
    public Integer testStatus;
    @SerializedName("org_id")
    @Expose
    public String orgId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("active_index")
    @Expose
    public String activeIndex;
    @SerializedName("schedule_id")
    @Expose
    public Integer scheduleId;
    @SerializedName("schedule_instance_id")
    @Expose
    public Integer scheduleInstanceId;
    @SerializedName("grp_id")
    @Expose
    public Object grpId;


}
