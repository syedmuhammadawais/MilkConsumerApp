package com.conformiz.milkconsumerapp.models.response;

public class UserCreationResponse implements java.io.Serializable {
    private static final long serialVersionUID = 8001644914174909033L;
    private int code;
    private UserCreationResponseData data;
    private boolean success;
    private boolean alreadyExists;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserCreationResponseData getData() {
        return this.data;
    }

    public void setData(UserCreationResponseData data) {
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
