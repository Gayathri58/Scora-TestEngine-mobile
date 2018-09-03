package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaxonomyLevelResponse {

    @SerializedName("taxonomy_level_count")
    @Expose
    public List<TaxonomyLevelCountRadio> taxonomyLevelCount = null;
    @SerializedName("taxonomy_level_score")
    @Expose
    public List<TaxonomyLevelScoreRadio> taxonomyLevelScore = null;

    public List<TaxonomyLevelCountRadio> getTaxonomyLevelCount() {
        return taxonomyLevelCount;
    }

    public void setTaxonomyLevelCount(List<TaxonomyLevelCountRadio> taxonomyLevelCount) {
        this.taxonomyLevelCount = taxonomyLevelCount;
    }

    public List<TaxonomyLevelScoreRadio> getTaxonomyLevelScore() {
        return taxonomyLevelScore;
    }

    public void setTaxonomyLevelScore(List<TaxonomyLevelScoreRadio> taxonomyLevelScore) {
        this.taxonomyLevelScore = taxonomyLevelScore;
    }
}
