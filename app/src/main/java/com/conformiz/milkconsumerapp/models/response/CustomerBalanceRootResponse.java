package com.conformiz.milkconsumerapp.models.response;

public class CustomerBalanceRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 5937162776741776366L;
    private int code;
    private int data;
    private boolean success;
    private boolean accees_Limit;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getAccees_Limit() {
        return this.accees_Limit;
    }

    public void setAccees_Limit(boolean accees_Limit) {
        this.accees_Limit = accees_Limit;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
