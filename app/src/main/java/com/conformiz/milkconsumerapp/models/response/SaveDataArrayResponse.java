package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class SaveDataArrayResponse implements java.io.Serializable {
    private static final long serialVersionUID = -6804738135203193836L;
    private int code;
    private ArrayList<SaveDataArrayResponseData> data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<SaveDataArrayResponseData> getData() {
        return this.data;
    }

    public void setData(ArrayList<SaveDataArrayResponseData> data) {
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
