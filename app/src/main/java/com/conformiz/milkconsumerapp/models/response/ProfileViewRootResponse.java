package com.conformiz.milkconsumerapp.models.response;

public class ProfileViewRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 8295625723912057076L;
    private int code;
    private ProfileViewRootResponseData data;
    private boolean success;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProfileViewRootResponseData getData() {
        return this.data;
    }

    public void setData(ProfileViewRootResponseData data) {
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
