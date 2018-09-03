package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StrongAreaRadio {
    @SerializedName("topic_Nm")
    @Expose
    public String topicNm;
    @SerializedName("Correct")
    @Expose
    public String correct;

    public String getTopicNm() {
        return topicNm;
    }

    public void setTopicNm(String topicNm) {
        this.topicNm = topicNm;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

}
