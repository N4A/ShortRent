package com.fudan.test;

import java.util.List;

import org.junit.Test;

import com.fudan.dao.IUserDao;
import com.fudan.dao.impl.UserDaoImpl;
import com.fudan.entity.User;

public class UserDaoTest {
	// 测试UserDaoImpl类，完成于2015/07/28
	
	@Test  // 测试新增
	public void testAdd() {
		User u = new User();
		u.setUsername("test");
		u.setPassword("test");
		u.setMobile("123456");
		IUserDao ud = new UserDaoImpl();
		ud.add(u);
	}
	
	@Test  // 测试查找 
	public void testFindUser() {
		IUserDao ud = new UserDaoImpl();
		User u = ud.findUser(2);
		System.out.println(u.getUsername());
		System.out.println(u.getAddress()==null);
	}
	
	@Test  // 测试更新
	public void testUpdate() {
		IUserDao ud = new UserDaoImpl();
		User u = ud.findUser(1);
		u.setEmail("test@test.com");
		u.setGender(User.MALE);
		u.setAddress("一个中文地址");
		ud.update(u);
	}
	
	@Test  // 测试验证用户名密码
	public void testVerify() {
		IUserDao ud = new UserDaoImpl();
		System.out.println("user is:" + ud.verify("test", "test"));
		System.out.println("user is:" + ud.verify("test", "falsePassword"));
	}
	
	@Test  // 测试修改密码
	public void testUpdatePassword() {
		IUserDao ud = new UserDaoImpl();
		User u = ud.findUser(1);
		u.setPassword("newPassword");
		ud.updatePassword(u);
		System.out.println("user password is test:" + ud.verify("test", "test"));
		System.out.println("user password is newPassword:" + ud.verify("test", "newPassword"));
		u = ud.findUser(1);
		u.setPassword("test");
		ud.updatePassword(u);
		System.out.println("user password is test:" + ud.verify("test", "test"));
		System.out.println("user password is newPassword:" + ud.verify("test", "newPassword"));
	}
	
	@Test  // 测试增加管理员
	public void testAddAdmin() {
		User u = new User();
		u.setUsername("admin");
		u.setPassword("admin");
		u.setMobile("123456");
		IUserDao ud = new UserDaoImpl();
		ud.addAdmin(u);
	}
	
	@Test  // 测试根据用户名获得用户
	public void testFindUserByName() {
		IUserDao ud = new UserDaoImpl();
		User u = ud.findUserByName("test");
		System.out.println(u.getUsername());
		System.out.println(u.getAddress());
		User u2 = ud.findUserByName("NoSuchUser");
		System.out.println(u2);
	}
	
	@Test  // 测试得到所有用户
	public void testFindUsers() {
		IUserDao ud = new UserDaoImpl();
		List<User> us = ud.findUsers(1);
		for (int i = 0; i < us.size(); i++) {
			System.out.println(us.get(i).getUsername());
		}
	}
	
	@Test  // 测试删除
	public void testDelete() {
		IUserDao ud = new UserDaoImpl();
		ud.delete(ud.findUser(1));
	}
	
	@Test  // 测试得到页数
	public void testMaxPageOfUsers() {
		IUserDao ud = new UserDaoImpl();
		System.out.println(ud.maxPageOfUsers());
	}

}
