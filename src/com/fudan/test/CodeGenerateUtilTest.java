package com.fudan.test;

import org.junit.Test;

import com.fudan.util.CodeGenerateUtil;

public class CodeGenerateUtilTest {
	
	@Test
	public void testGenerateSalt() {
		System.out.println(new CodeGenerateUtil().generateSalt());
	}


}
