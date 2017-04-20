package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fudan.dao.IReplyDao;
import com.fudan.entity.Entity;
import com.fudan.entity.Reply;
import com.fudan.util.JdbcUtil;

public class ReplyDaoImpl implements IReplyDao {
	
	private JdbcUtil util;
	
	public ReplyDaoImpl() {
		util = new JdbcUtil();
	}

	/** 新增回复
	 *  输入：Reply对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean add(Reply r) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO reply(user_id,comment_id,content) VALUES (?,?,?)";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getUserId());
			pst.setInt(2, r.getCommentId());
			pst.setString(3, r.getContent());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 删除回复
	 *  输入：Reply对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean delete(Reply r) {
		Connection conn = util.getConnection();
		String sql = "UPDATE reply SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, r.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 根据评论id得到回复
	 *  输入：comment id
	 *  输出：Reply对象；null：没有回复或已被删除
	 *  2015/07/20
	 */
	public Reply findReplyByComment(int commentId) {
		Reply r = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM reply WHERE comment_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, commentId);
			rs = pst.executeQuery();
			if (rs.next()) {  // 存在用户
				r = new Reply();
				r.setId(rs.getInt("id"));
				r.setUserId(rs.getInt("user_id"));
				r.setCommentId(commentId);
				r.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return r;
	}

}
