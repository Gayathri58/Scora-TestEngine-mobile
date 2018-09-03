package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerChoice {

    @SerializedName("correct_answer")
    @Expose
    public Boolean correctAnswer;
    @SerializedName("answer_choice_id")
    @Expose
    public Integer answerChoiceId;
    @SerializedName("choice_elements")
    @Expose
    public List<ChoiceElement> choiceElements = null;
    @SerializedName("choosed_answer")
    @Expose
    public Boolean choosedAnswer;
    @SerializedName("seq_id")
    @Expose
    public Integer seqId;

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
    @SerializedName("score")
    @Expose
    public Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getAnswerChoiceId() {
        return answerChoiceId;
    }

    public void setAnswerChoiceId(Integer answerChoiceId) {
        this.answerChoiceId = answerChoiceId;
    }

    public List<ChoiceElement> getChoiceElements() {
        return choiceElements;
    }

    public void setChoiceElements(List<ChoiceElement> choiceElements) {
        this.choiceElements = choiceElements;
    }

    public Boolean getChoosedAnswer() {
        return choosedAnswer;
    }

    public void setChoosedAnswer(Boolean choosedAnswer) {
        this.choosedAnswer = choosedAnswer;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }
}


