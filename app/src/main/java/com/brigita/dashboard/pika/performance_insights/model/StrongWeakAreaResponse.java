package com.brigita.dashboard.pika.performance_insights.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StrongWeakAreaResponse {

    @SerializedName("strong_area")
    @Expose
    public List<StrongAreaRadio> strongArea = null;
    @SerializedName("weak_area")
    @Expose
    public List<WeakAreaRadio> weakArea = null;

    public List<StrongAreaRadio> getStrongArea() {
        return strongArea;
    }

    public void setStrongArea(List<StrongAreaRadio> strongArea) {
        this.strongArea = strongArea;
    }

    public List<WeakAreaRadio> getWeakArea() {
        return weakArea;
    }

    public void setWeakArea(List<WeakAreaRadio> weakArea) {
        this.weakArea = weakArea;
    }
}
