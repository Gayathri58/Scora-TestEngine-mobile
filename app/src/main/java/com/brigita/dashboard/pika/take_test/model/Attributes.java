package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.security.auth.Subject;

public class Attributes {

    @SerializedName("subject")
    @Expose
    public List<Subject> subject = null;
    @SerializedName("topic")
    @Expose
    public List<Topic> topic = null;
    @SerializedName("subtopic")
    @Expose
    public List<Subtopic> subtopic = null;
    @SerializedName("content_type")
    @Expose
    public List<ContentType> contentType = null;
    @SerializedName("difficulty_level")
    @Expose
    public String difficultyLevel;
    @SerializedName("search_keywords")
    @Expose
    public String searchKeywords;
    @SerializedName("ref_link")
    @Expose
    public String refLink;


    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    public List<Subtopic> getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(List<Subtopic> subtopic) {
        this.subtopic = subtopic;
    }

    public List<ContentType> getContentType() {
        return contentType;
    }

    public void setContentType(List<ContentType> contentType) {
        this.contentType = contentType;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public String getRefLink() {
        return refLink;
    }

    public void setRefLink(String refLink) {
        this.refLink = refLink;
    }
}
