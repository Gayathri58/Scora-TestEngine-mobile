package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class AreaChartResponse {

    @SerializedName("test_score_board_instance")
    @Expose
    public List<TestScoreBoardInstance> testScoreBoardInstance = null;
    @SerializedName("difficulty_level")
    @Expose
    public DifficultyLevel difficultyLevel;
    @SerializedName("taxonomy_level")
    @Expose
    public TaxonomyLevel taxonomyLevel;
    @SerializedName("topic_level")
    @Expose
    public TopicLevel topicLevel;
    @SerializedName("strong_weak_area")
    @Expose
    public StrongWeakArea strongWeakArea;

    public List<TestScoreBoardInstance> getTestScoreBoardInstance() {
        return testScoreBoardInstance;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public TaxonomyLevel getTaxonomyLevel() {
        return taxonomyLevel;
    }

    public TopicLevel getTopicLevel() {
        return topicLevel;
    }

    public StrongWeakArea getStrongWeakArea() {
        return strongWeakArea;
    }
}
