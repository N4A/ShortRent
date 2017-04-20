package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan.dao.IMessageDao;
import com.fudan.entity.Entity;
import com.fudan.entity.Message;
import com.fudan.util.JdbcUtil;

public class MessageDaoImpl implements IMessageDao {
	
	private JdbcUtil util;
	
	public MessageDaoImpl() {
		util = new JdbcUtil();
	}

	/** 新增站内信
	 *  输入：Message对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean add(Message m) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO message(user_id,target_id,content) VALUES (?,?,?)";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, m.getUserId());
			pst.setInt(2, m.getTargetId());
			pst.setString(3, m.getContent());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 删除站内信
	 *  输入：Message对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean delete(Message m) {
		Connection conn = util.getConnection();
		String sql = "UPDATE message SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, m.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 根据id得到站内信
	 *  输入：id
	 *  输出：Message；null：没有站内信或已被删除
	 *  2015/07/20
	 */
	public Message findMessage(int id) {
		Message m = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM message WHERE id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {  // 存在用户
				m = new Message();
				m.setId(id);
				m.setUserId(rs.getInt("user_id"));
				m.setTargetId(rs.getInt("target_id"));
				m.setContent(rs.getString("content"));
				m.setState(rs.getInt("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return m;
	}

	/** 根据发送者id得到该用户发送的所有站内信
	 *  输入：sender id
	 *  输出：List<Message>；null：没有站内信
	 *  2015/07/20
	 *  2015/08/03 只得到一条的bug
	 */
	public List<Message> findMessageBySender(int senderId) {
		List<Message> ms = new ArrayList<Message>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM message WHERE user_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, senderId);
			rs = pst.executeQuery();
			while (rs.next()) {  // 存在用户
				Message m = new Message();
				m.setId(rs.getInt("id"));
				m.setUserId(senderId);
				m.setTargetId(rs.getInt("target_id"));
				m.setContent(rs.getString("content"));
				m.setState(rs.getInt("state"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (ms.size() == 0) ? null : ms;
	}

	/** 根据接收者id得到该用户收到的所有站内信
	 *  输入：receiver id
	 *  输出：List<Message>；null：没有站内信
	 *  2015/07/20
	 *  2015/08/03 只得到一条的bug
	 */
	public List<Message> findMessageByReceiver(int receiverId) {
		List<Message> ms = new ArrayList<Message>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM message WHERE target_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, receiverId);
			rs = pst.executeQuery();
			while (rs.next()) {  // 存在用户
				Message m = new Message();
				m.setId(rs.getInt("id"));
				m.setUserId(rs.getInt("user_id"));
				m.setTargetId(receiverId);
				m.setContent(rs.getString("content"));
				m.setState(rs.getInt("state"));
				ms.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (ms.size() == 0) ? null : ms;
	}

	/** 更新某条站内信的阅读状态：未读=>已读
	 *  输入：Message对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean update(Message m) {
		Connection conn = util.getConnection();
		String sql = "UPDATE message SET state=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Message.READ);
			pst.setInt(2, m.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

}
