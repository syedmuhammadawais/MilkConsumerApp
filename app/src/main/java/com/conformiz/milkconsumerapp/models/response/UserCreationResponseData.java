package com.conformiz.milkconsumerapp.models.response;

public class UserCreationResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 4007114513571747619L;
    private String client_id;

    public String getClient_id() {
        return this.client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
