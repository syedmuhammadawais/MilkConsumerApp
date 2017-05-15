package com.conformiz.milkconsumerapp.models.response.teachersdata;

public class TeachersDataListResponse implements java.io.Serializable {
    private String teacher_name;
    private String father_name;
    private String teacher_id;
    private String school_name;
    private String class_name;
    private String teacher_code;

    public String getTeacher_name() {
        return this.teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getFather_name() {
        return this.father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getTeacher_id() {
        return this.teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSchool_name() {
        return this.school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getClass_name() {
        return this.class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_code() {
        return this.teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }
}
