package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchDatum {

    @SerializedName("header")
    @Expose
    public Integer header;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("match_value")
    @Expose
    public String matchValue;
    @SerializedName("match_data_format_id")
    @Expose
    public Integer matchDataFormatId;
    @SerializedName("row_seq_id")
    @Expose
    public Integer rowSeqId;


    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(String matchValue) {
        this.matchValue = matchValue;
    }

    public Integer getMatchDataFormatId() {
        return matchDataFormatId;
    }

    public void setMatchDataFormatId(Integer matchDataFormatId) {
        this.matchDataFormatId = matchDataFormatId;
    }

    public Integer getRowSeqId() {
        return rowSeqId;
    }

    public void setRowSeqId(Integer rowSeqId) {
        this.rowSeqId = rowSeqId;
    }
}
