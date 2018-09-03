package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectiveContent {

    @SerializedName("directive_id")
    @Expose
    public Integer directiveId;
    @SerializedName("content")
    @Expose
    public List<Content> content = null;
    @SerializedName("attributes")
    @Expose
    public Attributes attributes;

    public Integer getDirectiveId() {
        return directiveId;
    }

    public void setDirectiveId(Integer directiveId) {
        this.directiveId = directiveId;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
