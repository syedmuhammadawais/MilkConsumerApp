package com.conformiz.milkconsumerapp.models.Request;

public class SignUpUserRootRequest implements java.io.Serializable {
    private static final long serialVersionUID = -7153377540964118383L;

    private String fullName;
    private String address;
    private String email;
    private String userName;
    private String father_or_husband_name;
    private String zone_id;
    private String password;
    private String cell_no_1;
    private String date_of_birth;



    private String cell_no_2;
    private String area;
    private String city; // optional
    private String residence_phone_no;
    private String cnic;

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResidence_phone_no() {
        return this.residence_phone_no;
    }

    public void setResidence_phone_no(String residence_phone_no) {
        this.residence_phone_no = residence_phone_no;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFather_or_husband_name() {
        return this.father_or_husband_name;
    }

    public void setFather_or_husband_name(String father_or_husband_name) {
        this.father_or_husband_name = father_or_husband_name;
    }

    public String getZone_id() {
        return this.zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCell_no_1() {
        return this.cell_no_1;
    }

    public void setCell_no_1(String cell_no_1) {
        this.cell_no_1 = cell_no_1;
    }

    public String getCell_no_2() {
        return this.cell_no_2;
    }

    public void setCell_no_2(String cell_no_2) {
        this.cell_no_2 = cell_no_2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
