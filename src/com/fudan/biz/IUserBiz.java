package com.fudan.biz;

import java.util.List;

import com.fudan.entity.User;

public interface IUserBiz {
	public boolean add(User u);   // 新增用户2015/07/17
	public boolean delete(User u);  // 删除用户2015/07/17
	public boolean update(User u);  // 更新用户2015/07/17
	public User verify(String username, String passwordRaw); // 验证用户名和密码2015/07/17
	public User findUser(int id);  // 得到一个用户2015/07/17
	public List<User> findUsers(int page);  // 分页显示所有用户2015/07/17
	public User findUserByName(String name);//如果没有找到，返回null2015/07/17
	public int maxPageOfUsers();
}
