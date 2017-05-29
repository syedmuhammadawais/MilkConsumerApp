package com.conformiz.milkconsumerapp.models.response;

public class ViewCustomerComplaintsRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = -6473358084895928922L;
    private String reason;
    private String complain_id;
    private String created_on;
    private String response;
    private String status_name;
    private String query_text;

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComplain_id() {
        return this.complain_id;
    }

    public void setComplain_id(String complain_id) {
        this.complain_id = complain_id;
    }

    public String getCreated_on() {
        return this.created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus_name() {
        return this.status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getQuery_text() {
        return this.query_text;
    }

    public void setQuery_text(String query_text) {
        this.query_text = query_text;
    }
}
