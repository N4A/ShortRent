package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fudan.dao.IOrderDao;
import com.fudan.entity.Entity;
import com.fudan.entity.Order;
import com.fudan.util.CodeGenerateUtil;
import com.fudan.util.DateUtil;
import com.fudan.util.JdbcUtil;

public class OrderDaoImpl implements IOrderDao {

	private JdbcUtil util;
	
	public OrderDaoImpl() {
		util = new JdbcUtil();
	}
	
	/** 新增订单
	 *  输入：Order对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/22 改正表名与sql关键词冲突的问题
	 */
	public boolean add(Order o) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO `order`(user_id,house_id,order_num,checkin_date,checkout_date,day_price,"
				+ "order_date) VALUES (?,?,?,?,?,?,NOW())";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, o.getUserId());
			pst.setInt(2, o.getHouseId());
			pst.setString(3, new CodeGenerateUtil().generateOrderNum(o));  // 根据order对象生成订单号
			pst.setDate(4, o.getCheckinDate().toSQLDate());  // 需要以sql.Date的类型储存数据
			pst.setDate(5, o.getCheckoutDate().toSQLDate());
			pst.setDouble(6, o.getDayPrice());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 删除订单
	 *  输入：Order对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/22 改正表名与sql关键词冲突的问题
	 */
	public boolean delete(Order o) {
		Connection conn = util.getConnection();
		String sql = "UPDATE `order` SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, o.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 更新订单 （实际只有状态，确认日期和评论状态会被更新）
	 *  输入：Order对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/22 改正表名与sql关键词冲突的问题
	 */
	public boolean update(Order o) {
		Connection conn = util.getConnection();
		String sql = "UPDATE `order` SET state=?,check_date=?,comment_state=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, o.getState());
			pst.setDate(2, o.getCheckDate().toSQLDate());
			pst.setInt(3, o.getCommentState());
			pst.setInt(4, o.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 根据用户id得到（未被删除的）订单 
	 *  输入：userId
	 *  输出：List<Order>：操作成功；null：没有订单
	 *  2015/07/22 改正表名与sql关键词冲突的问题
	 */
	public List<Order> findOrderByUser(int userId) {
		List<Order> os = new ArrayList<Order>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM `order` WHERE user_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(userId);
				o.setHouseId(rs.getInt("house_id"));
				o.setOrderNum(rs.getString("order_num"));
				o.setCheckinDate(new DateUtil(rs.getString("checkin_date")));
				o.setCheckoutDate(new DateUtil(rs.getString("checkout_date")));
				o.setDayPrice(rs.getDouble("day_price"));
				o.setState(rs.getInt("state"));
				o.setCheckDate(new DateUtil(rs.getString("check_date")));
				o.setOrderDate(new DateUtil(rs.getString("order_date")));
				o.setCommentState(rs.getInt("comment_state"));
				os.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (os.size() == 0) ? null : os;
	}

	/** 根据房东id得到（未被删除的）订单 （需要在order表和house表联合查询） 
	 *  输入：ownerId
	 *  输出：List<Order>：操作成功；null：没有订单
	 *  2015/07/22 改正表名与sql关键词冲突的问题
	 */
	public List<Order> findOrderByOwner(int ownerId) {
		List<Order> os = new ArrayList<Order>();
		Connection conn = util.getConnection();
		String sql = "SELECT o.id,o.user_id,o.house_id,o.order_num,o.checkin_date,o.checkout_date,"
				+ "o.day_price,o.state,o.check_date,o.order_date,o.comment_state FROM `order` o,house h "
				+ "WHERE h.user_id=? AND o.house_id=h.id AND o.del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ownerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setHouseId(rs.getInt("house_id"));
				o.setOrderNum(rs.getString("order_num"));
				o.setCheckinDate(new DateUtil(rs.getString("checkin_date")));
				o.setCheckoutDate(new DateUtil(rs.getString("checkout_date")));
				o.setDayPrice(rs.getDouble("day_price"));
				o.setState(rs.getInt("state"));
				o.setCheckDate(new DateUtil(rs.getString("check_date")));
				o.setOrderDate(new DateUtil(rs.getString("order_date")));
				o.setCommentState(rs.getInt("comment_state"));
				os.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (os.size() == 0) ? null : os;
	}

	/** 查找是否有与当前订单时间冲突的订单
	 *  输入：order对象
	 *  输出：true：存在冲突的订单；false：不存在
	 *  2015/07/22
	 *  2015/07/23:修正查询的逻辑错误 || 不知道为什么使用pstm无法得到正确的结果
	 */
	public boolean findConflictOrder(Order o) {
		Connection conn = util.getConnection();
		// 搜索存在冲突的订单：同一个房屋id，对方已被受理，入住时间或退出时间不符合
		// 即 not (this.checkoutDate<=other.checkinDate or this.checkinDate>=other.checkoutDate))
		String sql = "SELECT id FROM `order` WHERE house_id=" + o.getHouseId() + " AND state=1 AND del=0 "
				+ "AND NOT(checkin_date >= " + o.getCheckoutDate().toSQLString() + " or checkout_date <= "
				+ o.getCheckinDate().toSQLString() + ")";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			/*pst.setInt(1, o.getHouseId());
			pst.setString(2, o.getCheckoutDate().toSQLString());
			pst.setString(3, o.getCheckinDate().toSQLString());
			System.out.println(o.getCheckoutDate().toSQLString());
			System.out.println(o.getCheckinDate().toSQLString());*/
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return false;
	}

	/** 得到与当前订单时间冲突的订单
	 *  输入：order对象
	 *  输出：List
	 *  2015/08/04
	 */
	public List<Order> getConflictOrder(Order oc) {
		List<Order> os = new ArrayList<Order>();
		Connection conn = util.getConnection();
		// 搜索存在冲突的订单：同一个房屋id，对方未被受理，入住时间或退出时间不符合
		// 即 not (this.checkoutDate<=other.checkinDate or this.checkinDate>=other.checkoutDate))
		String sql = "SELECT * FROM `order` WHERE house_id=" + oc.getHouseId() + " AND state=0 AND del=0 "
				+ "AND NOT(checkin_date >= " + oc.getCheckoutDate().toSQLString() + " or checkout_date <= "
				+ oc.getCheckinDate().toSQLString() + ")";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setHouseId(rs.getInt("house_id"));
				o.setOrderNum(rs.getString("order_num"));
				o.setCheckinDate(new DateUtil(rs.getString("checkin_date")));
				o.setCheckoutDate(new DateUtil(rs.getString("checkout_date")));
				o.setDayPrice(rs.getDouble("day_price"));
				o.setState(rs.getInt("state"));
				o.setCheckDate(new DateUtil(rs.getString("check_date")));
				o.setOrderDate(new DateUtil(rs.getString("order_date")));
				o.setCommentState(rs.getInt("comment_state"));
				os.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (os.size() == 0) ? null : os;
	}
	
	/**
	 * 根据id得到订单
	 * 输入 id
	 * 输出 order
	 * 2015/08/03
	 */
	public Order findOrder(int id) {
		Order o = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM `order` WHERE id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {  // 存在用户
				o = new Order();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setHouseId(rs.getInt("house_id"));
				o.setOrderNum(rs.getString("order_num"));
				o.setCheckinDate(new DateUtil(rs.getString("checkin_date")));
				o.setCheckoutDate(new DateUtil(rs.getString("checkout_date")));
				o.setDayPrice(rs.getDouble("day_price"));
				o.setState(rs.getInt("state"));
				o.setCheckDate(new DateUtil(rs.getString("check_date")));
				o.setOrderDate(new DateUtil(rs.getString("order_date")));
				o.setCommentState(rs.getInt("comment_state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return o;
	}

}
