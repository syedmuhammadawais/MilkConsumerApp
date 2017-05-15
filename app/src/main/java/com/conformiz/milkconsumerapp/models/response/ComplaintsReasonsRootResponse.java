package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class ComplaintsReasonsRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 5661654987357305055L;
    private int code;
    private ArrayList<ComplaintsReasonsRootResponseData> data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public  ArrayList<ComplaintsReasonsRootResponseData> getData() {
        return this.data;
    }

    public void setData( ArrayList<ComplaintsReasonsRootResponseData> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
