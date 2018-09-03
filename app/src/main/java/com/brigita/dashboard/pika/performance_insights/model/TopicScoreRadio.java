package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicScoreRadio {

    @SerializedName("Topic_Nm")
    @Expose
    public String topicNm;
    @SerializedName("Incorrect")
    @Expose
    public String incorrect;
    @SerializedName("Correct")
    @Expose
    public String correct;
    @SerializedName("Missed")
    @Expose
    public String missed;

    public String getTopicNm() {
        return topicNm;
    }

    public void setTopicNm(String topicNm) {
        this.topicNm = topicNm;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getMissed() {
        return missed;
    }

    public void setMissed(String missed) {
        this.missed = missed;
    }
}
