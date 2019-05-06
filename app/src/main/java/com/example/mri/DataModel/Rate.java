package com.example.mri.DataModel;

public class Rate {
    private String detailId;
    private String restaurantId;
    private String rateValue;
    private String comment;

    public Rate() {
    }

    public Rate(String detailId, String restaurantId, String rateValue, String comment) {
        this.detailId = detailId;
        this.restaurantId = restaurantId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
