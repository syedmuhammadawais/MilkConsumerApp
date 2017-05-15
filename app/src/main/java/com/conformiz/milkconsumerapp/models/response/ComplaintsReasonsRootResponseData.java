package com.conformiz.milkconsumerapp.models.response;

public class ComplaintsReasonsRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 9086485558986499258L;
    private String company_branch_id;
    private String name;
    private String complain_type_id;

    public String getCompany_branch_id() {
        return this.company_branch_id;
    }

    public void setCompany_branch_id(String company_branch_id) {
        this.company_branch_id = company_branch_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplain_type_id() {
        return this.complain_type_id;
    }

    public void setComplain_type_id(String complain_type_id) {
        this.complain_type_id = complain_type_id;
    }

    @Override
    public String toString() {
        return this.name;            // What to display in the Spinner list.
    }


}
