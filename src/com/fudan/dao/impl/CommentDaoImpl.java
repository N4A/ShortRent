package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan.dao.ICommentDao;
import com.fudan.entity.Comment;
import com.fudan.entity.Entity;
import com.fudan.util.JdbcUtil;

public class CommentDaoImpl implements ICommentDao {

	private JdbcUtil util;
	
	public CommentDaoImpl() {
		util = new JdbcUtil();
	}
	
	/** 新增评论
	 *  输入：Comment对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean add(Comment c) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO comment(user_id,house_id,content) VALUES (?,?,?)";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getUserId());
			pst.setInt(2, c.getHouseId());
			pst.setString(3, c.getContent());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 删除评论
	 *  输入：Comment对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean delete(Comment c) {
		Connection conn = util.getConnection();
		String sql = "UPDATE comment SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, c.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 根据房屋id得到（未被删除的）评论 （用于房源页面对评论的显示）
	 *  输入：houseId
	 *  输出：List<Comment>：操作成功；null：没有评论
	 *  2015/07/20
	 */
	public List<Comment> findCommentByHouse(int houseId) {
		List<Comment> cs = new ArrayList<Comment>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM comment WHERE house_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, houseId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Comment c = new Comment();
				c.setId(rs.getInt("id"));
				c.setUserId(rs.getInt("user_id"));
				c.setHouseId(houseId);
				c.setContent(rs.getString("content"));
				cs.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (cs.size() == 0) ? null : cs;
	}

}
