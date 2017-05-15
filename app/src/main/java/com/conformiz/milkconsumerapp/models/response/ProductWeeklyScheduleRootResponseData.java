package com.conformiz.milkconsumerapp.models.response;

public class ProductWeeklyScheduleRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 4408809233985000785L;
    private String frequency_id;
    private String quantity;
    private String day_name;
    private String PreferredTime;
    private String isSelected;

    public String getFrequency_id() {
        return this.frequency_id;
    }

    public void setFrequency_id(String frequency_id) {
        this.frequency_id = frequency_id;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDay_name() {
        return this.day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getPreferredTime() {
        return this.PreferredTime;
    }

    public void setPreferredTime(String PreferredTime) {
        this.PreferredTime = PreferredTime;
    }

    public String getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
