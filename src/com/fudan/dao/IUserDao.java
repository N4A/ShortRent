package com.fudan.dao;

import java.util.List;

import com.fudan.entity.User;

public interface IUserDao {
	
	public boolean add(User u);   // 新增用户
	public boolean addAdmin(User u);  // 新增管理员
	public boolean delete(User u);  // 删除用户
	public boolean update(User u);  // 更新用户
	public boolean updatePassword(User u);  // 更改用户密码
	public User verify(String username, String passwordRaw); // 验证用户名和密码
	public User findUser(int id);  // 得到一个用户
	public User findUserByName(String name);  // 根据用户名得到用户
	public List<User> findUsers(int page);  // 分页显示所有用户
	public int maxPageOfUsers();  // 得到所有用户的最大页码
	
}
