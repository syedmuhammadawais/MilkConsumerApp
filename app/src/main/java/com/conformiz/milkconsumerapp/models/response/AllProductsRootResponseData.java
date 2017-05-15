package com.conformiz.milkconsumerapp.models.response;

public class AllProductsRootResponseData implements java.io.Serializable {
    private String unit;
    private String price;
    private String product_id;
    private String product_name;

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
}
