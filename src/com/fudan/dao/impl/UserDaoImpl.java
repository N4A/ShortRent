package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan.dao.IUserDao;
import com.fudan.entity.Entity;
import com.fudan.entity.User;
import com.fudan.util.CodeGenerateUtil;
import com.fudan.util.JdbcUtil;

public class UserDaoImpl implements IUserDao {

	final private static int NUM_PER_PAGE = 15; 
	private JdbcUtil util;
	
	public UserDaoImpl() {
		util = new JdbcUtil();
	}
	
	/** 新增用户
	 *  输入：User对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean add(User u) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO user(username,password,salt,mobile,email) VALUES (?,MD5(?),?,?,?)";
		PreparedStatement pst = null;
		String salt = (new CodeGenerateUtil()).generateSalt();
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword() + salt);
			pst.setString(3, salt);
			pst.setString(4, u.getMobile());
			pst.setString(5, u.getEmail());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 删除用户
	 *  输入：User对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean delete(User u) {
		Connection conn = util.getConnection();
		String sql = "UPDATE user SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, u.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 更新用户资料 （用于用户更改个人信息时）
	 *  输入：User对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/21
	 */
	public boolean update(User u) {
		Connection conn = util.getConnection();
		String sql = "UPDATE user SET gender=?,mobile=?,email=?,address=?,avatar=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, u.getGender());
			pst.setString(2, u.getMobile());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getAddress());
			pst.setString(5, u.getAvatar());
			pst.setInt(6, u.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 更新用户密码 （此时u.password中放的是新密码的原码）
	 *  输入：User对象，新密码
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/21
	 */
	public boolean updatePassword(User u) {
		Connection conn = util.getConnection();
		String sql = "UPDATE user SET password=MD5(?) WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getPassword() + u.getSalt());
			pst.setInt(2, u.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}
	
	/** 验证用户名密码是否正确 （用于登录验证）
	 *  输入：用户名，密码明码
	 *  输出：User对象：验证通过；null：验证不通过
	 *  2015/07/20
	 */
	public User verify(String username, String passwordRaw) {
		User temp_u = findUserByName(username);
		User u = null;  // 要返回的user对象
		if (temp_u != null) {  // 用户名正确，验证用户名和密码的一致性
			Connection conn = util.getConnection();
			String sql = "SELECT id FROM user WHERE username=? AND password=MD5(?)";
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, passwordRaw + temp_u.getSalt());
				rs = pst.executeQuery();
				if (rs.next()) {  // 存在用户
					u = temp_u;  // 一定是同一个用户
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.close(rs, pst, conn);
			}
		}
		return u;
	}

	/** 根据id得到用户
	 *  输入：id
	 *  输出：User对象：操作成功；null：不存在的id或用户已被删除
	 *  2015/07/20
	 */
	public User findUser(int id) {
		User u = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM user WHERE id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {  // 存在用户
				u = new User();
				u.setId(id);
				u.setSalt(rs.getString("salt"));
				u.setUsername(rs.getString("username"));
				u.setAddress(rs.getString("address"));
				u.setAvatar(rs.getString("avatar"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getInt("gender"));
				u.setMobile(rs.getString("mobile"));
				u.setState(rs.getInt("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return u;
	}

	/** 分页得到所有（未被删除的）用户 （用于管理员对用户的后台管理）
	 *  输入：page 页码
	 *  输出：List<User>； null：没有用户
	 *  2015/07/20
	 */
	public List<User> findUsers(int page) {
		List<User> us = new ArrayList<User>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM user WHERE del=0 LIMIT ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, NUM_PER_PAGE * (page - 1));
			pst.setInt(2, NUM_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {  // 存在用户
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setSalt(rs.getString("salt"));
				u.setUsername(rs.getString("username"));
				u.setAddress(rs.getString("address"));
				u.setAvatar(rs.getString("avatar"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getInt("gender"));
				u.setMobile(rs.getString("mobile"));
				u.setState(rs.getInt("state"));
				us.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (us.size() == 0) ? null : us;  // 如果长度为0即没有结果则返回null
	}

	/** 通过用户名得到用户
	 *  输入：name用户名
	 *  输出：User对象：操作成功；null：操作失败
	 *  2015/07/20
	 */
	public User findUserByName(String name) {
		User u = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM user WHERE username=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {  // 存在用户
				u = new User();
				u.setId(rs.getInt("id"));
				u.setSalt(rs.getString("salt"));
				u.setUsername(name);
				u.setAddress(rs.getString("address"));
				u.setAvatar(rs.getString("avatar"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getInt("gender"));
				u.setMobile(rs.getString("mobile"));
				u.setState(rs.getInt("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return u;
	}

	/** 新增管理员 （只能被现有的管理员调用）
	 *  输入：User对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean addAdmin(User u) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO user(username,password,salt,mobile,email,state) VALUES (?,MD5(?),?,?,?,1)";
		PreparedStatement pst = null;
		String salt = (new CodeGenerateUtil()).generateSalt();
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword() + salt);
			pst.setString(3, salt);
			pst.setString(4, u.getMobile());
			pst.setString(5, u.getEmail());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 得到查询所有用户时的最大页数，用于分页
	 *  输出：int
	 *  2015/07/28
	 */
	public int maxPageOfUsers() {
		int num = 0;
		int page = 0;
		Connection conn = util.getConnection();
		String sql = "SELECT count(*) FROM user WHERE del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {  
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		page = num / NUM_PER_PAGE + ((num % NUM_PER_PAGE == 0) ? 0 : 1);
		return page;	
	}

}
