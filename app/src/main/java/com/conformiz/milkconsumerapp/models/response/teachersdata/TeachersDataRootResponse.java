package com.conformiz.milkconsumerapp.models.response.teachersdata;

import java.util.ArrayList;

public class TeachersDataRootResponse implements java.io.Serializable {
    private int code;
    private ArrayList<TeachersDataListResponse> data;
    private boolean success;
    private String title;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<TeachersDataListResponse> getData() {
        return this.data;
    }

    public void setData(ArrayList<TeachersDataListResponse> data) {
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
}
