package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class TestScoreBoardInstance {

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

    public String getTestName() {
        return testName;
    }

    public Integer getItemsetId() {
        return itemsetId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public String getAttempt() {
        return attempt;
    }

    public String getSectionNm() {
        return sectionNm;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public String getScore() {
        return score;
    }

    public String getPercentageScore() {
        return percentageScore;
    }

    public String getIncorrectItems() {
        return incorrectItems;
    }

    public String getCorrectItems() {
        return correctItems;
    }

    public String getMissedItems() {
        return missedItems;
    }
}
