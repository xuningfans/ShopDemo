package com.netease.course.meta;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable {
	private static final long serialVersionUID = 8461355401694402895L;

	private Integer userId;

	@NotEmpty(message = "请登录！")
	private String userName;

	@NotEmpty(message = "请登录！")
	private String userPassword;

	private Integer userType;

	private List<Orders> orderList;

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + "]";
	}

}