package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {


    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("itemName")
    @Expose
    public String itemName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
