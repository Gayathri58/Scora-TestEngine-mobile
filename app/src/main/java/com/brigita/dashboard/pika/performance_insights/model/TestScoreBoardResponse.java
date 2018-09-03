package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestScoreBoardResponse {

    @SerializedName("test_instance_id")
    @Expose
    public Integer testInstanceId;
    @SerializedName("test_name")
    @Expose
    public String testName;
    @SerializedName("itemset_id")
    @Expose
    public Integer itemsetId;
    @SerializedName("section_id")
    @Expose
    public Integer sectionId;
    @SerializedName("Attempt")
    @Expose
    public String attempt;
    @SerializedName("section_nm")
    @Expose
    public String sectionNm;
    @SerializedName("No_Of_Items")
    @Expose
    public Integer noOfItems;
    @SerializedName("Max_Score")
    @Expose
    public Integer maxScore;
    @SerializedName("Score")
    @Expose
    public String score;
    @SerializedName("Percentage_Score")
    @Expose
    public String percentageScore;
    @SerializedName("Incorrect_Items")
    @Expose
    public String incorrectItems;
    @SerializedName("Correct_Items")
    @Expose
    public String correctItems;
    @SerializedName("Missed_Items")
    @Expose
    public String missedItems;

    public Integer getTestInstanceId() {
        return testInstanceId;
    }

    public void setTestInstanceId(Integer testInstanceId) {
        this.testInstanceId = testInstanceId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getItemsetId() {
        return itemsetId;
    }

    public void setItemsetId(Integer itemsetId) {
        this.itemsetId = itemsetId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public String getSectionNm() {
        return sectionNm;
    }

    public void setSectionNm(String sectionNm) {
        this.sectionNm = sectionNm;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(String percentageScore) {
        this.percentageScore = percentageScore;
    }

    public String getIncorrectItems() {
        return incorrectItems;
    }

    public void setIncorrectItems(String incorrectItems) {
        this.incorrectItems = incorrectItems;
    }

    public String getCorrectItems() {
        return correctItems;
    }

    public void setCorrectItems(String correctItems) {
        this.correctItems = correctItems;
    }

    public String getMissedItems() {
        return missedItems;
    }

    public void setMissedItems(String missedItems) {
        this.missedItems = missedItems;
    }
}
