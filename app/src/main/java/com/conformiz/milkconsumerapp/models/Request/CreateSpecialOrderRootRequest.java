package com.conformiz.milkconsumerapp.models.Request;

public class CreateSpecialOrderRootRequest implements java.io.Serializable {
    private static final long serialVersionUID = 3551604669068210975L;
    private String preferred_time_id;
    private String quantity;
    private String product_id;
    private String start_date;
    private String end_date;
    private String client_id;

    public String getPreferred_time_id() {
        return this.preferred_time_id;
    }

    public void setPreferred_time_id(String preferred_time_id) {
        this.preferred_time_id = preferred_time_id;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getClient_id() {
        return this.client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
