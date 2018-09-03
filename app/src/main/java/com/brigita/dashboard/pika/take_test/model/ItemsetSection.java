package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsetSection {

    @SerializedName("section_id")
    @Expose
    public Integer sectionId;
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("timer")
    @Expose
    public Integer timer;
    @SerializedName("section_items")
    @Expose
    public List<SectionItem> sectionItems = null;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public List<SectionItem> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<SectionItem> sectionItems) {
        this.sectionItems = sectionItems;
    }
}