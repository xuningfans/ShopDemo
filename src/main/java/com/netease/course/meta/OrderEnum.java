package com.netease.course.meta;

public enum OrderEnum {
	BUY("已下单", 0), PAY("已付款", 1), DELIVER("已发货", 2);

	private final String state;
	private final int value;

	private OrderEnum(String state, int value) {
		this.state = state;
		this.value = value;
	}

	public String getState() {
		return state;
	}

	public int getValue() {
		return value;
	}
	
	/** 定义枚举类型自己的方法 **/
	public static OrderEnum valueOf(int i) {
		switch (i) {
		case 0:
			return OrderEnum.BUY;
		case 1:
			return OrderEnum.PAY;
		case 2:
			return OrderEnum.DELIVER;
		default:
			return null;
		}
	}


}
