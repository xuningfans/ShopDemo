package com.netease.course.meta;

import java.math.BigDecimal;

public class Product {
    private Integer productId;

    private String productTitle;

    private String productImage;

    private String productDetail;

    private BigDecimal productPrice;

    private String productSummary;

    private Boolean productSell;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
    }

    public Boolean getProductSell() {
        return productSell;
    }

    public void setProductSell(Boolean productSell) {
        this.productSell = productSell;
    }
}