package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataFormatMatchValue {

    @SerializedName("match_data")
    @Expose
    public List<MatchDatum> matchData = null;
    @SerializedName("match_row_seq_id")
    @Expose
    public Integer matchRowSeqId;

    public List<MatchDatum> getMatchData() {
        return matchData;
    }

    public void setMatchData(List<MatchDatum> matchData) {
        this.matchData = matchData;
    }

    public Integer getMatchRowSeqId() {
        return matchRowSeqId;
    }

    public void setMatchRowSeqId(Integer matchRowSeqId) {
        this.matchRowSeqId = matchRowSeqId;
    }
}

