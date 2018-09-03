package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DifficultyLevelResponse {

    @SerializedName("difficulty_level_count")
    @Expose
    public List<DifficultyLevelCountRadio> difficultyLevelCount = null;
    @SerializedName("difficulty_level_score")
    @Expose
    public List<DifficultyLevelScoreRadio> difficultyLevelScore = null;

    public List<DifficultyLevelCountRadio> getDifficultyLevelCount() {
        return difficultyLevelCount;
    }

    public void setDifficultyLevelCount(List<DifficultyLevelCountRadio> difficultyLevelCount) {
        this.difficultyLevelCount = difficultyLevelCount;
    }

    public List<DifficultyLevelScoreRadio> getDifficultyLevelScore() {
        return difficultyLevelScore;
    }

    public void setDifficultyLevelScore(List<DifficultyLevelScoreRadio> difficultyLevelScore) {
        this.difficultyLevelScore = difficultyLevelScore;
    }
}
