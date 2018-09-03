package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TakeTestResponse {

    @SerializedName("org_id")
    @Expose
    public Integer orgId;
    @SerializedName("test_start_resume")
    @Expose
    public Integer testStartResume;
    @SerializedName("org_name")
    @Expose
    public String orgName;
    @SerializedName("org_logo")
    @Expose
    public String orgLogo;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("test_id")
    @Expose
    public Integer testId;
    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("schedule_id")
    @Expose
    public Integer scheduleId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_logo")
    @Expose
    public String userLogo;
    @SerializedName("section_flag")
    @Expose
    public Integer sectionFlag;
    @SerializedName("paused_filter_key")
    @Expose
    public Object pausedFilterKey;
    @SerializedName("display_runing_score")
    @Expose
    public Integer displayRuningScore;
    @SerializedName("test_alert_time")
    @Expose
    public Integer testAlertTime;
    @SerializedName("test_alert_time_show")
    @Expose
    public String testAlertTimeShow;
    @SerializedName("enable_item_shuffle")
    @Expose
    public Integer enableItemShuffle;
    @SerializedName("enable_ans_choice_shuffle")
    @Expose
    public Integer enableAnsChoiceShuffle;
    @SerializedName("display_hints")
    @Expose
    public Integer displayHints;
    @SerializedName("display_item_solution")
    @Expose
    public Integer displayItemSolution;
    @SerializedName("dynamic_section_selection")
    @Expose
    public Integer dynamicSectionSelection;
    @SerializedName("previous_button")
    @Expose
    public Integer previousButton;
    @SerializedName("mark_for_review_button")
    @Expose
    public Integer markForReviewButton;
    @SerializedName("clear_response_button")
    @Expose
    public Integer clearResponseButton;
    @SerializedName("question_palette_button")
    @Expose
    public Integer questionPaletteButton;
    @SerializedName("filter_display")
    @Expose
    public Integer filterDisplay;
    @SerializedName("previous_button_value")
    @Expose
    public String previousButtonValue;
    @SerializedName("mark_for_review_button_value")
    @Expose
    public String markForReviewButtonValue;
    @SerializedName("clear_response_button_value")
    @Expose
    public String clearResponseButtonValue;
    @SerializedName("save_and_next_button_value")
    @Expose
    public String saveAndNextButtonValue;
    @SerializedName("submit_button_value")
    @Expose
    public String submitButtonValue;
    @SerializedName("pause_section_id")
    @Expose
    public Object pauseSectionId;
    @SerializedName("pause_itemset_item_key")
    @Expose
    public Object pauseItemsetItemKey;
    @SerializedName("pause_resume")
    @Expose
    public Integer pauseResume;
    @SerializedName("itemset_id")
    @Expose
    public Integer itemsetId;
    @SerializedName("test_timer_type")
    @Expose
    public String testTimerType;
    @SerializedName("timer")
    @Expose
    public Integer timer;
    @SerializedName("test_instance_id")
    @Expose
    public Integer testInstanceId;
    @SerializedName("section_attributes")
    @Expose
    public List<SectionAttribute> sectionAttributes = null;
    @SerializedName("itemset_sections")
    @Expose
    public List<ItemsetSection> itemsetSections = null;

    @SerializedName("total_section_timer")
    @Expose
    public String totalSectionTimer;

    public String getTotalSectionTimer() {
        return totalSectionTimer;
    }

    public void setTotalSectionTimer(String totalSectionTimer) {
        this.totalSectionTimer = totalSectionTimer;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTestStartResume() {
        return testStartResume;
    }

    public void setTestStartResume(Integer testStartResume) {
        this.testStartResume = testStartResume;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public Integer getSectionFlag() {
        return sectionFlag;
    }

    public void setSectionFlag(Integer sectionFlag) {
        this.sectionFlag = sectionFlag;
    }

    public Object getPausedFilterKey() {
        return pausedFilterKey;
    }

    public void setPausedFilterKey(Object pausedFilterKey) {
        this.pausedFilterKey = pausedFilterKey;
    }

    public Integer getDisplayRuningScore() {
        return displayRuningScore;
    }

    public void setDisplayRuningScore(Integer displayRuningScore) {
        this.displayRuningScore = displayRuningScore;
    }

    public Integer getTestAlertTime() {
        return testAlertTime;
    }

    public void setTestAlertTime(Integer testAlertTime) {
        this.testAlertTime = testAlertTime;
    }

    public String getTestAlertTimeShow() {
        return testAlertTimeShow;
    }

    public void setTestAlertTimeShow(String testAlertTimeShow) {
        this.testAlertTimeShow = testAlertTimeShow;
    }

    public Integer getEnableItemShuffle() {
        return enableItemShuffle;
    }

    public void setEnableItemShuffle(Integer enableItemShuffle) {
        this.enableItemShuffle = enableItemShuffle;
    }

    public Integer getEnableAnsChoiceShuffle() {
        return enableAnsChoiceShuffle;
    }

    public void setEnableAnsChoiceShuffle(Integer enableAnsChoiceShuffle) {
        this.enableAnsChoiceShuffle = enableAnsChoiceShuffle;
    }

    public Integer getDisplayHints() {
        return displayHints;
    }

    public void setDisplayHints(Integer displayHints) {
        this.displayHints = displayHints;
    }

    public Integer getDisplayItemSolution() {
        return displayItemSolution;
    }

    public void setDisplayItemSolution(Integer displayItemSolution) {
        this.displayItemSolution = displayItemSolution;
    }

    public Integer getDynamicSectionSelection() {
        return dynamicSectionSelection;
    }

    public void setDynamicSectionSelection(Integer dynamicSectionSelection) {
        this.dynamicSectionSelection = dynamicSectionSelection;
    }

    public Integer getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Integer previousButton) {
        this.previousButton = previousButton;
    }

    public Integer getMarkForReviewButton() {
        return markForReviewButton;
    }

    public void setMarkForReviewButton(Integer markForReviewButton) {
        this.markForReviewButton = markForReviewButton;
    }

    public Integer getClearResponseButton() {
        return clearResponseButton;
    }

    public void setClearResponseButton(Integer clearResponseButton) {
        this.clearResponseButton = clearResponseButton;
    }

    public Integer getQuestionPaletteButton() {
        return questionPaletteButton;
    }

    public void setQuestionPaletteButton(Integer questionPaletteButton) {
        this.questionPaletteButton = questionPaletteButton;
    }

    public Integer getFilterDisplay() {
        return filterDisplay;
    }

    public void setFilterDisplay(Integer filterDisplay) {
        this.filterDisplay = filterDisplay;
    }

    public String getPreviousButtonValue() {
        return previousButtonValue;
    }

    public void setPreviousButtonValue(String previousButtonValue) {
        this.previousButtonValue = previousButtonValue;
    }

    public String getMarkForReviewButtonValue() {
        return markForReviewButtonValue;
    }

    public void setMarkForReviewButtonValue(String markForReviewButtonValue) {
        this.markForReviewButtonValue = markForReviewButtonValue;
    }

    public String getClearResponseButtonValue() {
        return clearResponseButtonValue;
    }

    public void setClearResponseButtonValue(String clearResponseButtonValue) {
        this.clearResponseButtonValue = clearResponseButtonValue;
    }

    public String getSaveAndNextButtonValue() {
        return saveAndNextButtonValue;
    }

    public void setSaveAndNextButtonValue(String saveAndNextButtonValue) {
        this.saveAndNextButtonValue = saveAndNextButtonValue;
    }

    public String getSubmitButtonValue() {
        return submitButtonValue;
    }

    public void setSubmitButtonValue(String submitButtonValue) {
        this.submitButtonValue = submitButtonValue;
    }

    public Object getPauseSectionId() {
        return pauseSectionId;
    }

    public void setPauseSectionId(Object pauseSectionId) {
        this.pauseSectionId = pauseSectionId;
    }

    public Object getPauseItemsetItemKey() {
        return pauseItemsetItemKey;
    }

    public void setPauseItemsetItemKey(Object pauseItemsetItemKey) {
        this.pauseItemsetItemKey = pauseItemsetItemKey;
    }

    public Integer getPauseResume() {
        return pauseResume;
    }

    public void setPauseResume(Integer pauseResume) {
        this.pauseResume = pauseResume;
    }

    public Integer getItemsetId() {
        return itemsetId;
    }

    public void setItemsetId(Integer itemsetId) {
        this.itemsetId = itemsetId;
    }

    public String getTestTimerType() {
        return testTimerType;
    }

    public void setTestTimerType(String testTimerType) {
        this.testTimerType = testTimerType;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public Integer getTestInstanceId() {
        return testInstanceId;
    }

    public void setTestInstanceId(Integer testInstanceId) {
        this.testInstanceId = testInstanceId;
    }

    public List<SectionAttribute> getSectionAttributes() {
        return sectionAttributes;
    }

    public void setSectionAttributes(List<SectionAttribute> sectionAttributes) {
        this.sectionAttributes = sectionAttributes;
    }

    public List<ItemsetSection> getItemsetSections() {
        return itemsetSections;
    }

    public void setItemsetSections(List<ItemsetSection> itemsetSections) {
        this.itemsetSections = itemsetSections;
    }
}
