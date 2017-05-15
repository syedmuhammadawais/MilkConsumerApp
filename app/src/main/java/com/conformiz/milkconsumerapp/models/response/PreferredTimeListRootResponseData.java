package com.conformiz.milkconsumerapp.models.response;

public class PreferredTimeListRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 5882464077317288832L;
    private String preferred_time_id;
    private String preferred_time_name;

    public String getPreferred_time_id() {
        return this.preferred_time_id;
    }

    public void setPreferred_time_id(String preferred_time_id) {
        this.preferred_time_id = preferred_time_id;
    }

    public String getPreferred_time_name() {
        return this.preferred_time_name;
    }

    public void setPreferred_time_name(String preferred_time_name) {
        this.preferred_time_name = preferred_time_name;
    }
}
