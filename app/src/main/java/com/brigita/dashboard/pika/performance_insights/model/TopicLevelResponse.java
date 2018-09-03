package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicLevelResponse {

    @SerializedName("topic_count")
    @Expose
    public List<TopicCountRadio> topicCount = null;
    @SerializedName("topic_score")
    @Expose
    public List<TopicScoreRadio> topicScore = null;

    public List<TopicCountRadio> getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(List<TopicCountRadio> topicCount) {
        this.topicCount = topicCount;
    }

    public List<TopicScoreRadio> getTopicScore() {
        return topicScore;
    }

    public void setTopicScore(List<TopicScoreRadio> topicScore) {
        this.topicScore = topicScore;
    }
}
