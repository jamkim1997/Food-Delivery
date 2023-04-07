package model;

import java.util.Date;

public class Coupon {
    private Integer couponId;

    private String couponName;

    private Integer couponScope;

    private Double couponMinMoney;

    private Date createdDate;

    private Double couponValue;

    private String couponDescription;

    private String couponImage;

    public Coupon(){}

    public Coupon(String couponName, Integer couponScope, Double couponMinMoney, Double couponValue, String couponDescription, String couponImage) {
        this.couponName = couponName;
        this.couponScope = couponScope;
        this.couponMinMoney = couponMinMoney;
        this.couponValue = couponValue;
        this.couponDescription = couponDescription;
        this.couponImage = couponImage;
    }

    public Coupon(Integer couponId, String couponName, Integer couponScope, Double couponMinMoney, Double couponValue, String couponDescription, String couponImage) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.couponScope = couponScope;
        this.couponMinMoney = couponMinMoney;
        this.couponValue = couponValue;
        this.couponDescription = couponDescription;
        this.couponImage = couponImage;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getCouponScope() {
        return couponScope;
    }

    public void setCouponScope(Integer couponScope) {
        this.couponScope = couponScope;
    }

    public Double getCouponMinMoney() {
        return couponMinMoney;
    }

    public void setCouponMinMoney(Double couponMinMoney) {
        this.couponMinMoney = couponMinMoney;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription == null ? null : couponDescription.trim();
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage == null ? null : couponImage.trim();
    }
}