package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentType {


    @SerializedName("UDF_Tag_ID")
    @Expose
    public Integer uDFTagID;
    @SerializedName("UDF_Tag")
    @Expose
    public String uDFTag;

    public Integer getuDFTagID() {
        return uDFTagID;
    }

    public void setuDFTagID(Integer uDFTagID) {
        this.uDFTagID = uDFTagID;
    }

    public String getuDFTag() {
        return uDFTag;
    }

    public void setuDFTag(String uDFTag) {
        this.uDFTag = uDFTag;
    }
}
