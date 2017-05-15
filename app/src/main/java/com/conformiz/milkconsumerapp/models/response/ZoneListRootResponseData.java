package com.conformiz.milkconsumerapp.models.response;

public class ZoneListRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 3524102373702019480L;
    private String company_branch_id;
    private String zone_id;
    private String is_active;
    private String is_deleted;
    private String name;

    public String getCompany_branch_id() {
        return this.company_branch_id;
    }

    public void setCompany_branch_id(String company_branch_id) {
        this.company_branch_id = company_branch_id;
    }

    public String getZone_id() {
        return this.zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getIs_active() {
        return this.is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_deleted() {
        return this.is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name+"";
    }
}
