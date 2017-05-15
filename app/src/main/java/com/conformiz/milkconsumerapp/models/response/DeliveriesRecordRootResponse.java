package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class DeliveriesRecordRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = -5450970904603785922L;
    private int code;
    private ArrayList<DeliveriesRecordRootResponseData> data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<DeliveriesRecordRootResponseData> getData() {
        return this.data;
    }

    public void setData(ArrayList<DeliveriesRecordRootResponseData> data) {
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
