package com.conformiz.milkconsumerapp.models.response;

public class DeliveriesRecordRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 6635868652706857673L;
    private String date;
    private String unit;
    private String amount;
    private String quantity;
    private String product_name;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
