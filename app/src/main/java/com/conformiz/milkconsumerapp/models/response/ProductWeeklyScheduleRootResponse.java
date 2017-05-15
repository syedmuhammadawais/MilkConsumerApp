package com.conformiz.milkconsumerapp.models.response;

import java.util.ArrayList;

public class ProductWeeklyScheduleRootResponse implements java.io.Serializable {
    private int code;
    private ArrayList<ProductWeeklyScheduleRootResponseData> data;
    private boolean success;
    private String message;
    private String client_id;
    private String product_id;
    private String orderStartDate;



    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ProductWeeklyScheduleRootResponseData>  getData() {
        return this.data;
    }

    public void setData(ArrayList<ProductWeeklyScheduleRootResponseData>  data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientId() {
        return client_id;
    }

    public void setClientId(String clientId) {
        this.client_id = clientId;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String productId) {
        this.product_id = productId;
    }

    public String getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
    }
}
