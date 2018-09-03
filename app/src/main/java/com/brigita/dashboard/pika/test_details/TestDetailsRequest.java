package com.brigita.dashboard.pika.test_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestDetailsRequest {

    @SerializedName("current_section")
    @Expose
    public CurrentSection currentSection;
    @SerializedName("device_type")
    @Expose
    public Integer deviceType;
    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("next_section_id")
    @Expose
    public String nextSectionId;
    @SerializedName("org_id")
    @Expose
    public String orgId;
    @SerializedName("paused_filter_key")
    @Expose
    public String pausedFilterKey;
    @SerializedName("schedule_id")
    @Expose
    public String scheduleId;
    @SerializedName("test_id")
    @Expose
    public String testId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("user_selected_option")
    @Expose
    public List<Object> userSelectedOption ;
    @SerializedName("user_storage_data")
    @Expose
    public List<Object> userStorageData ;

    public TestDetailsRequest(CurrentSection currentSection, Integer deviceType, String groupId, String nextSectionId, String orgId, String pausedFilterKey,
                              String scheduleId, String testId, String userId, List<Object> userSelectedOption,
                              List<Object> userStorageData) {
        this.currentSection = currentSection;
        this.deviceType = deviceType;
        this.groupId = groupId;
        this.nextSectionId = nextSectionId;
        this.orgId = orgId;
        this.pausedFilterKey = pausedFilterKey;
        this.scheduleId = scheduleId;
        this.testId = testId;
        this.userId = userId;
        this.userSelectedOption = userSelectedOption;
        this.userStorageData = userStorageData;
    }

    public CurrentSection getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(CurrentSection currentSection) {
        this.currentSection = currentSection;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Object getNextSectionId() {
        return nextSectionId;
    }

    public void setNextSectionId(String nextSectionId) {
        this.nextSectionId = nextSectionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Object getPausedFilterKey() {
        return pausedFilterKey;
    }

    public void setPausedFilterKey(String pausedFilterKey) {
        this.pausedFilterKey = pausedFilterKey;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Object> getUserSelectedOption() {
        return userSelectedOption;
    }

    public void setUserSelectedOption(List<Object> userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }

    public List<Object> getUserStorageData() {
        return userStorageData;
    }

    public void setUserStorageData(List<Object> userStorageData) {
        this.userStorageData = userStorageData;
    }
}
