package com.conformiz.milkconsumerapp.models.response.questions;

import java.util.ArrayList;

public class QuestionsRootResponse implements java.io.Serializable {
    private int code;
    private boolean success;
    private String title;
    private String message;
    private String teacherId;

    private ArrayList<QuestionsDataResponseData> data;


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<QuestionsDataResponseData> getData() {
        return this.data;
    }

    public void setData(ArrayList<QuestionsDataResponseData> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
