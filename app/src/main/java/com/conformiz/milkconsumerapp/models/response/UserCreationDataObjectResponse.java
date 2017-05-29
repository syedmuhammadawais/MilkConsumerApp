package com.conformiz.milkconsumerapp.models.response;

public class UserCreationDataObjectResponse implements java.io.Serializable {
    private static final long serialVersionUID = -4379376331022094814L;
    private int code;
    private UserCreationDataObjectResponseData data;
    private boolean success;
    private boolean alreadyExists;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserCreationDataObjectResponseData getData() {
        return this.data;
    }

    public void setData(UserCreationDataObjectResponseData data) {
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
