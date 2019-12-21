package com.shopping.mall.constant;

/**
 * 
 * @Title: OrderStatusEnum.java
 * @Package com.sihai.common.enums
 * @Description:  支付方法
 * @date 2017年4月24日 下午4:31:20
 * @version V1.0
 */
public enum PaidMethodEnum {

	ALIPAY(1, "支付宝"),			// 支付宝
	WX(2, "微信");			// 微信

	public final int key;
	public final String value;

	PaidMethodEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static String getName(int key) {
		for (PaidMethodEnum status : PaidMethodEnum.values()) {
			if (status.getKey() == key) {
				return status.value;
			}
		}
		return null;
	}
	 
	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
