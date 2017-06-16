package com.conformiz.milkconsumerapp.models.response;

public class ManageSmsAlertRootResponse implements java.io.Serializable {
    private static final long serialVersionUID = 4363465620164565665L;
    private int code;
    private ManageSmsAlertRootResponseData data;
    private boolean success;
    private String message;
    private String client_id;


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ManageSmsAlertRootResponseData getData() {
        return this.data;
    }

    public void setData(ManageSmsAlertRootResponseData data) {
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
