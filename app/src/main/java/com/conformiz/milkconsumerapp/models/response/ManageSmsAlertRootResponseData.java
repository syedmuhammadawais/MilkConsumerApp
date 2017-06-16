package com.conformiz.milkconsumerapp.models.response;

public class ManageSmsAlertRootResponseData implements java.io.Serializable {
    private static final long serialVersionUID = 8102971530800662676L;
    private String daily_delivery_sms;
    private String alert_new_product;

    public String getDaily_delivery_sms() {
        return this.daily_delivery_sms;
    }

    public void setDaily_delivery_sms(String daily_delivery_sms) {
        this.daily_delivery_sms = daily_delivery_sms;
    }

    public String getAlert_new_product() {
        return this.alert_new_product;
    }

    public void setAlert_new_product(String alert_new_product) {
        this.alert_new_product = alert_new_product;
    }
}
