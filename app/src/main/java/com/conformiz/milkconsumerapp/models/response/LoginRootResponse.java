package com.conformiz.milkconsumerapp.models.response;

public class LoginRootResponse implements java.io.Serializable {
    private int code;
    private LoginRootResponseData data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LoginRootResponseData getData() {
        return this.data;
    }

    public void setData(LoginRootResponseData data) {
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
