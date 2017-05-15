package com.conformiz.milkconsumerapp.models.response.questions;

import java.util.ArrayList;

public class QuestionsDataResponseDataQuestionList implements java.io.Serializable {
    private String description;
    private String question_id;
    private ArrayList<AnswerOptionsListResponse> answerOption;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion_id() {
        return this.question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public ArrayList<AnswerOptionsListResponse> getAnswerOption() {
        return this.answerOption;
    }

    public void setAnswerOption(ArrayList<AnswerOptionsListResponse> answerOption) {
        this.answerOption = answerOption;
    }
}
