package com.netease.course.meta;

import java.io.Serializable;

/**
 * 商品Bean
 * @author 公猴脖子男
 */
public class Product implements Serializable {

	private static final long serialVersionUID = -5082637775244308663L;

	private Integer productId;

    private String productTitle;

    private String productImage;
    
    private String productDetail;

    private Double productPrice;

    private String productSummary;

    private Boolean isSell;

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

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean IsSell) {
        this.isSell = IsSell;
    }

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productImage=" + productImage
				+ ", productDetail=" + productDetail + ", productPrice=" + productPrice + ", productSummary="
				+ productSummary + ", productSell=" + isSell + "]";
	}
    
    
}