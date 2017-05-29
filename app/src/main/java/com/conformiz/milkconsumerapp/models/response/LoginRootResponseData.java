package com.conformiz.milkconsumerapp.models.response;

public class LoginRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = -2054274960828626585L;
    private String client_id;
    private String fullname;
    private String email;
    private String cell_no_1;

    public String getClient_id() {
        return this.client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell_no_1() {
        return cell_no_1;
    }

    public void setCell_no_1(String cell_no_1) {
        this.cell_no_1 = cell_no_1;
    }
}
