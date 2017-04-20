package com.fudan.biz.impl;

import java.util.List;

import com.fudan.biz.IUserBiz;
import com.fudan.dao.IUserDao;
import com.fudan.dao.impl.UserDaoImpl;
import com.fudan.entity.User;

public class UserBizImpl implements IUserBiz {
	IUserDao dao = new UserDaoImpl();
	
	//添加用户，实现用户注册功能2015/07/20
	@Override
	public boolean add(User u) {
		// TODO Auto-generated method stub
		if (dao.findUserByName(u.getUsername())!=null)
			return false;//用户已存在2015/07/23
		return dao.add(u);//返回false说明用户已存在
	}

	//删除用户，实现管理员管理和用户注销2015/07/20
	@Override
	public boolean delete(User u) {
		// TODO Auto-generated method stub
		return dao.delete(u);
	}

	/**
	 * 更新用户
	 * 因为更新时只提供了部分要更新的信息，为了防止无法设置（事实上并没有id）所以要在原user对象上更新
	 * 输入：只含更新信息的user对象
	 * 输入：true：更新成功；false：失败
	 * 2015/07/31
	 * 2015/07/31：分离更改普通信息跟密码，用于未设置的信息不更新
	 */
	public boolean update(User u) {
		String username = u.getUsername();
		User oldU = dao.findUserByName(username);
		// 更新基本信息
		if (!u.getAddress().trim().equals("")) {  // 用户输入了地址
			oldU.setAddress(u.getAddress());
		}	
		if (!u.getEmail().trim().equals("")) {
			oldU.setEmail(u.getEmail());
		}
		if (!u.getMobile().trim().equals("")) {
			oldU.setMobile(u.getMobile());
		}
		oldU.setGender(u.getGender());
		if (!dao.update(oldU)) { // 更新信息
			return false;
		}
		// 更新密码
		if (!u.getPassword().equals("")) {  // 输入了密码
			oldU.setPassword(u.getPassword());
			return dao.updatePassword(oldU);  // 更新密码
		}
		return true;  // 普通信息更新成功且没有更新密码时
	}

	//登陆验证2015/07/20
	@Override
	public User verify(String username, String passwordRaw) {
		// TODO Auto-generated method stub
		return dao.verify(username,passwordRaw);
	}

	//查找某一用户2015/07/20
	@Override
	public User findUser(int id) {
		// TODO Auto-generated method stub
		return dao.findUser(id);
	}

	//查询用户列表2015/07/20
	@Override
	public List<User> findUsers(int page) {
		// TODO Auto-generated method stub
		return dao.findUsers(page);
	}
	
	//根据用户名查询用户2015/07/20
	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return dao.findUserByName(name);
	}
	
	/**
	 * 得到用户最大页码
	 * 返回int
	 * 2015/08/02
	 */
	public int maxPageOfUsers() {
		return dao.maxPageOfUsers();
	}

}
