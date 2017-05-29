package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class ViewCustomerComplaintsRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1657607155094061636L;
    private int code;
    private ArrayList<ViewCustomerComplaintsRootResponseData> data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ViewCustomerComplaintsRootResponseData>  getData() {
        return this.data;
    }

    public void setData(ArrayList<ViewCustomerComplaintsRootResponseData> data) {
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
