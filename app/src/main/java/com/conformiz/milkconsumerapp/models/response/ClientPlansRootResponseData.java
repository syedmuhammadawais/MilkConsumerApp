package com.conformiz.milkconsumerapp.models.response;

public class ClientPlansRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 3463179830035345727L;
    private String unit;
    private String orderStartDate;
    private String price;
    private String product_id;
    private String product_name;
    private String order_type;


    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderStartDate() {
        return this.orderStartDate;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }
}
