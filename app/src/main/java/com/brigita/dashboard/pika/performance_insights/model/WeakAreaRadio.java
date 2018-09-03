package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeakAreaRadio {

    @SerializedName("topic_Nm")
    @Expose
    public String topicNm;
    @SerializedName("Incorrect")
    @Expose
    public String incorrect;

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
}
