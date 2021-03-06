package com.netease.course.meta;

import java.io.Serializable;

/**
 * 订单Bean
 * 
 * @author 公猴脖子男
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = -6451396726989021732L;

	private Integer ordersId;

	private User user;

	private Double ordersPrice;

	private Product product;

	private OrderEnum state;

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getOrdersPrice() {
		return ordersPrice;
	}

	public void setOrdersPrice(Double ordersPrice) {
		this.ordersPrice = ordersPrice;
	}

	public OrderEnum getState() {
		return state;
	}

	public void setState(int i) {
		this.state = OrderEnum.valueOf(i);
	}

	/*
	 * public void setState(OrderEnum state) { this.state = state; }
	 */

	@Override
	public String toString() {
		return "Orders [ordersId=" + ordersId + ", userId=" + user + ", productId=" + product + ", ordersPrice="
				+ ordersPrice + ", state=" + state.getState() + "]";
	}

}