package com.fudan.test;

import org.junit.Test;

import com.fudan.util.DateUtil;

public class DateUtilTest {
	// 测试DateUtil类，完成于2015/07/21
	
	@Test  // 测试按照字符串产生DateUtil对象和输出、获得相差天数的功能
	public void testNewAndDeltaDay() {
		DateUtil date1 = new DateUtil("2015-06-2");
		DateUtil date2 = new DateUtil("2015-07-2");
		System.out.println("date1 is:" + date1);
		System.out.println("delta date is:" + date2.getDeltaDay(date1));
	}
	
	@Test  // 测试得到当前日期的DateUtil对象并输出为字符串的功能
	public void testNewDateAndToString() {
		DateUtil d = new DateUtil();
		System.out.println(d.toString());
		System.out.println(d.toSQLString());
		System.out.println(d.toOrderString());
	}
	
	@Test  // 测试容错能力和initialized值的正确设置
	public void testInitialize() {
		DateUtil d1 = new DateUtil();
		DateUtil d2 = new DateUtil("2015-7-21");
		DateUtil d3 = new DateUtil("");
		DateUtil d4 = new DateUtil("dafadsfdga");
		System.out.println("d1 init?" + d1.isInitialized());
		System.out.println("d2 init?" + d2.isInitialized());
		System.out.println("d3 init?" + d3.isInitialized());
		System.out.println("d4 init?" + d4.isInitialized());
	}

}
