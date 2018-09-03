package com.brigita.dashboard.pika.test_history.areachart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRIGITA on 07-07-2018.
 */

public class StrongWeakArea {

    @SerializedName("strong_area")
    @Expose
    public List<StrongArea> strongArea = null;
    @SerializedName("weak_area")
    @Expose
    public List<WeakArea> weakArea = null;

    public List<StrongArea> getStrongArea() {
        return strongArea;
    }

    public List<WeakArea> getWeakArea() {
        return weakArea;
    }
}
