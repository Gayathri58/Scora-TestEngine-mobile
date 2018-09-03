package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("data_id")
    @Expose
    public Integer dataId;
    @SerializedName("data_format_id")
    @Expose
    public Integer dataFormatId;
    @SerializedName("data_format_value")
    @Expose
    public String dataFormatValue;
    @SerializedName("item_df_sequence")
    @Expose
    public Integer itemDfSequence;
    @SerializedName("data_identifier")
    @Expose
    public Object dataIdentifier;
    @SerializedName("match_type")
    @Expose
    public Integer matchType;
    @SerializedName("data_format_match_value")
    @Expose
    public List<DataFormatMatchValue> dataFormatMatchValue = null;

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public List<DataFormatMatchValue> getDataFormatMatchValue() {
        return dataFormatMatchValue;
    }

    public void setDataFormatMatchValue(List<DataFormatMatchValue> dataFormatMatchValue) {
        this.dataFormatMatchValue = dataFormatMatchValue;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataFormatId() {
        return dataFormatId;
    }

    public void setDataFormatId(Integer dataFormatId) {
        this.dataFormatId = dataFormatId;
    }

    public String getDataFormatValue() {
        return dataFormatValue;
    }

    public void setDataFormatValue(String dataFormatValue) {
        this.dataFormatValue = dataFormatValue;
    }

    public Integer getItemDfSequence() {
        return itemDfSequence;
    }

    public void setItemDfSequence(Integer itemDfSequence) {
        this.itemDfSequence = itemDfSequence;
    }

    public Object getDataIdentifier() {
        return dataIdentifier;
    }

    public void setDataIdentifier(Object dataIdentifier) {
        this.dataIdentifier = dataIdentifier;
    }
}
