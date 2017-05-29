package com.conformiz.milkconsumerapp.models;

public class SaveDataObjectResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1832000567054590804L;
    private int code;
    private SaveDataObjectResponseData data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SaveDataObjectResponseData getData() {
        return this.data;
    }

    public void setData(SaveDataObjectResponseData data) {
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
