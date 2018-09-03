package com.brigita.dashboard.pika.take_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SectionItem {

    @SerializedName("item_id")
    @Expose
    public Integer itemId;
    @SerializedName("item_type")
    @Expose
    public Integer itemType;
    @SerializedName("is_directive")
    @Expose
    public Integer isDirective;
    @SerializedName("directive_content")
    @Expose
    public DirectiveContent directiveContent;
    @SerializedName("hints")
    @Expose
    public Object hints;
    @SerializedName("item")
    @Expose
    public List<Item> item = null;
    @SerializedName("answer_choices")
    @Expose
    public List<AnswerChoice> answerChoices = null;
    @SerializedName("Answer_Choice_Type")
    @Expose
    public String answerChoiceType;
    @SerializedName("score_type")
    @Expose
    public String scoreType;
    @SerializedName("ItemSet_Item_Key")
    @Expose
    public Integer itemSetItemKey;
    @SerializedName("yet_to_visit")
    @Expose
    public Integer yetToVisit;
    @SerializedName("filterCategory")
    @Expose
    public String filterCategory;
    @SerializedName("mark_for_review")
    @Expose
    public Integer markForReview;
    @SerializedName("not_answered")
    @Expose
    public Integer notAnswered;
    @SerializedName("answered")
    @Expose
    public Integer answered;
    @SerializedName("answered_marked_for_review")
    @Expose
    public Integer answeredMarkedForReview;
    @SerializedName("seq_no")
    @Expose
    public Integer seqNo;
    @SerializedName("hints_shown")
    @Expose
    public String hintsShown;
    @SerializedName("user_selected_option")
    @Expose
    public List<Object> userSelectedOption = null;


    @SerializedName("match_type")
    @Expose
    public Integer matchType;
    @SerializedName("is_header")
    @Expose
    public Integer isHeader;

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(Integer isHeader) {
        this.isHeader = isHeader;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getIsDirective() {
        return isDirective;
    }

    public void setIsDirective(Integer isDirective) {
        this.isDirective = isDirective;
    }

    public DirectiveContent getDirectiveContent() {
        return directiveContent;
    }

    public void setDirectiveContent(DirectiveContent directiveContent) {
        this.directiveContent = directiveContent;
    }

    public Object getHints() {
        return hints;
    }

    public void setHints(Object hints) {
        this.hints = hints;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public String getAnswerChoiceType() {
        return answerChoiceType;
    }

    public void setAnswerChoiceType(String answerChoiceType) {
        this.answerChoiceType = answerChoiceType;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public Integer getItemSetItemKey() {
        return itemSetItemKey;
    }

    public void setItemSetItemKey(Integer itemSetItemKey) {
        this.itemSetItemKey = itemSetItemKey;
    }

    public Integer getYetToVisit() {
        return yetToVisit;
    }

    public void setYetToVisit(Integer yetToVisit) {
        this.yetToVisit = yetToVisit;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public Integer getMarkForReview() {
        return markForReview;
    }

    public void setMarkForReview(Integer markForReview) {
        this.markForReview = markForReview;
    }

    public Integer getNotAnswered() {
        return notAnswered;
    }

    public void setNotAnswered(Integer notAnswered) {
        this.notAnswered = notAnswered;
    }

    public Integer getAnswered() {
        return answered;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public Integer getAnsweredMarkedForReview() {
        return answeredMarkedForReview;
    }

    public void setAnsweredMarkedForReview(Integer answeredMarkedForReview) {
        this.answeredMarkedForReview = answeredMarkedForReview;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getHintsShown() {
        return hintsShown;
    }

    public void setHintsShown(String hintsShown) {
        this.hintsShown = hintsShown;
    }

    public List<Object> getUserSelectedOption() {
        return userSelectedOption;
    }

    public void setUserSelectedOption(List<Object> userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }
}
