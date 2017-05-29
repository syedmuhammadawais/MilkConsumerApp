package com.conformiz.milkconsumerapp.models.response;

public class ProfileViewRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = -8935113500850672391L;
    private String zone_id;
    private String address;
    private String cell_no_1;
    private String zone_name;
    private String fullname;
    private String cnic;
    private String email;
    private String client_id;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getZone_id() {
        return this.zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell_no_1() {
        return this.cell_no_1;
    }

    public void setCell_no_1(String cell_no_1) {
        this.cell_no_1 = cell_no_1;
    }

    public String getZone_name() {
        return this.zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
