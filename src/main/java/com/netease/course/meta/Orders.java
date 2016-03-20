package com.netease.course.meta;

import java.math.BigDecimal;

public class Orders {
    private Integer ordersId;

    private Integer userId;

    private Integer productId;

    private BigDecimal ordersPrice;

    private Boolean isbuy;

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getOrdersPrice() {
        return ordersPrice;
    }

    public void setOrdersPrice(BigDecimal ordersPrice) {
        this.ordersPrice = ordersPrice;
    }

    public Boolean getIsbuy() {
        return isbuy;
    }

    public void setIsbuy(Boolean isbuy) {
        this.isbuy = isbuy;
    }
}