package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class StrongArea {

    @SerializedName("topic_Nm")
    @Expose
    public String topicNm;
    @SerializedName("Correct")
    @Expose
    public String correct;

    public String getTopicNm() {
        return topicNm;
    }

    public String getCorrect() {
        return correct;
    }
}
