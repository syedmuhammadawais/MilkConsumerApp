package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class ClientPlansRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 4074313600885739944L;
    private int code;
    private ArrayList<ClientPlansRootResponseData> data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ClientPlansRootResponseData> getData() {
        return this.data;
    }

    public void setData(ArrayList<ClientPlansRootResponseData> data) {
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
