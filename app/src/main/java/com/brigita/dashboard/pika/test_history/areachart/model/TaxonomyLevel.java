package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class TaxonomyLevel {

    @SerializedName("taxonomy_level_count")
    @Expose
    public List<TaxonomyLevelCount> taxonomyLevelCount = null;
    @SerializedName("taxonomy_level_score")
    @Expose
    public List<TaxonomyLevelScore> taxonomyLevelScore = null;

    public List<TaxonomyLevelCount> getTaxonomyLevelCount() {
        return taxonomyLevelCount;
    }

    public List<TaxonomyLevelScore> getTaxonomyLevelScore() {
        return taxonomyLevelScore;
    }
}
