package com.conformiz.milkconsumerapp.models.response;

/**
 * Created by Fahad.Munir on 16-May-17.
 */

public class PaymentProcessResponse {
    private String status_code;
    private String status_message;
    private String trans_ref_no;
    private String Token;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getTrans_ref_no() {
        return trans_ref_no;
    }

    public void setTrans_ref_no(String trans_ref_no) {
        this.trans_ref_no = trans_ref_no;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
