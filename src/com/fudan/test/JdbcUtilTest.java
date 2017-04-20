package com.fudan.test;

import java.sql.Connection;

import org.junit.Test;

import com.fudan.util.JdbcUtil;

public class JdbcUtilTest {
	
	@Test
	public void testConnection() {
		Connection conn = new JdbcUtil().getConnection();
		System.out.println("this conn is: "+conn);
	}
	
}
