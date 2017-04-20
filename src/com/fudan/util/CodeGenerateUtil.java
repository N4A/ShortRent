package com.fudan.util;

import java.util.Calendar;

import com.fudan.entity.Order;

public class CodeGenerateUtil {

	/** 生成一个8位的salt （用于对密码的加密）
	 *  输出：String,8位16进制的随机数
	 *  2015/07/20
	 */
	public String generateSalt() {
		String salt = "";
		for (int i = 0; i < 8; i++) {
			salt += Integer.toHexString((int) (Math.random() * 16));
		}
		return salt;
	}
	
	/** 生成订单号， 订单号=日期毫秒+房屋id+用户id
	 *  输入：Order对象（此时这个对象并不包含日期）
	 *  输出：String,8位16进制的随机数
	 *  2015/07/21
	 *  2015/07/23:改进生成机制，重复概率更小
	 */
	public String generateOrderNum(Order o) {
		String orderNum = "";
		orderNum += Calendar.getInstance().getTimeInMillis();
		orderNum += o.getHouseId();
		orderNum += o.getUserId();
		// 如果两次订单号相同，那么必须是同一用户在1ms内对同一房屋下两次订单，这太难
		return orderNum;
	}
}
