package com.fudan.test;

import org.junit.Test;

import com.fudan.util.SensitiveWordUtil;

public class SensitiveWordUtilTest {
	
	@Test  // 测试敏感词过滤功能
	public void testHasSensitiveWord() {
		System.out.println(new SensitiveWordUtil().hasSensitiveWord("dagadg江泽民sdaf"));
	}

}
