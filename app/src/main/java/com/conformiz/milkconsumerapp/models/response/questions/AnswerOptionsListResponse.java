package com.conformiz.milkconsumerapp.models.response.questions;

public class AnswerOptionsListResponse implements java.io.Serializable {
    private String option_description;
    private String answer_point;
    private String is_answered;
    private String answerOption_id;

    public String getOption_description() {
        return this.option_description;
    }

    public void setOption_description(String option_description) {
        this.option_description = option_description;
    }

    public String getAnswer_point() {
        return this.answer_point;
    }

    public void setAnswer_point(String answer_point) {
        this.answer_point = answer_point;
    }

    public String getIs_answered() {
        return is_answered;
    }

    public void setIs_answered(String is_answered) {
        this.is_answered = is_answered;
    }

    public String getAnswerOption_id() {
        return this.answerOption_id;
    }

    public void setAnswerOption_id(String answerOption_id) {
        this.answerOption_id = answerOption_id;
    }
}
