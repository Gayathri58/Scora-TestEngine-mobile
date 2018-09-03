package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class TopicLevel {

    @SerializedName("topic_count")
    @Expose
    public List<TopicCount> topicCount = null;
    @SerializedName("topic_score")
    @Expose
    public List<TopicScore> topicScore = null;

    public List<TopicCount> getTopicCount() {
        return topicCount;
    }

    public List<TopicScore> getTopicScore() {
        return topicScore;
    }
}
