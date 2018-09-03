package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class DifficultyLevel {

    @SerializedName("difficulty_level_count")
    @Expose
    public List<DifficultyLevelCount> difficultyLevelCount = null;
    @SerializedName("difficulty_level_score")
    @Expose
    public List<DifficultyLevelScore> difficultyLevelScore = null;

    public List<DifficultyLevelCount> getDifficultyLevelCount() {
        return difficultyLevelCount;
    }

    public List<DifficultyLevelScore> getDifficultyLevelScore() {
        return difficultyLevelScore;
    }

    public void setDifficultyLevelCount(List<DifficultyLevelCount> difficultyLevelCount) {
        this.difficultyLevelCount = difficultyLevelCount;
    }

    public void setDifficultyLevelScore(List<DifficultyLevelScore> difficultyLevelScore) {
        this.difficultyLevelScore = difficultyLevelScore;
    }
}
