package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class TaxonomyLevelScore {

    @SerializedName("Taxonomy_Nm")
    @Expose
    public String taxonomyNm;
    @SerializedName("Incorrect")
    @Expose
    public String incorrect;
    @SerializedName("Correct")
    @Expose
    public String correct;
    @SerializedName("Missed")
    @Expose
    public String missed;

    public String getTaxonomyNm() {
        return taxonomyNm;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public String getCorrect() {
        return correct;
    }

    public String getMissed() {
        return missed;
    }
}
