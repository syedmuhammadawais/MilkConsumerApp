package com.conformiz.milkconsumerapp.models.response.questions;

import java.util.ArrayList;

public class QuestionsDataResponseData implements java.io.Serializable {
    private String section_id;
    private String section_name;
    private ArrayList<QuestionsDataResponseDataQuestionList> questionList;

    public String getSection_id() {
        return this.section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return this.section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public ArrayList<QuestionsDataResponseDataQuestionList> getQuestionList() {
        return this.questionList;
    }

    public void setQuestionList(ArrayList<QuestionsDataResponseDataQuestionList> questionList) {
        this.questionList = questionList;
    }
}
