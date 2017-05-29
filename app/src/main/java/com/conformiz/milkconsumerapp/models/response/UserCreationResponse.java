package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class UserCreationResponse implements java.io.Serializable {
    private static final long serialVersionUID = 8001644914174909033L;
    private int code;
    private boolean success;
    private boolean alreadyExists;
    private String message;
    private ArrayList<SaveDataArrayResponseData> data;

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

    public boolean getAlreadyExists() {
        return this.alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
