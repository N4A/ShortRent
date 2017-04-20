package com.fudan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fudan.dao.IHouseDao;
import com.fudan.entity.Entity;
import com.fudan.entity.House;
import com.fudan.util.DateUtil;
import com.fudan.util.JdbcUtil;
import com.fudan.util.SearchUtil;

public class HouseDaoImpl implements IHouseDao {
	
	final private static int NUM_PER_PAGE = 15;  // 每页显示的房屋数(管理页面)
	//final private static int LATEST_HOUSE_NUM = 6;   // 首页显示最新房子的个数
	private JdbcUtil util;
	
	public HouseDaoImpl() {
		util = new JdbcUtil();
	}

	/** 新增房源
	 *  输入：House对象
	 *  输出：int：自增id，0：失败
	 *  2015/07/20
	 *  2015/08/05：改为返回自增id
	 */
	public int add(House h) {
		Connection conn = util.getConnection();
		String sql = "INSERT INTO house(user_id,name,bill,rent_type,kind,area,guest_num,bed_num,"
				+ "bedroom_num,room_num,bed_type,toilet_num,room_desc,use_rule,facility,address,"
				+ "min_day,max_day,refund_day,pay_rule,day_price,create_time) VALUES (?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
		PreparedStatement pst = null;
		ResultSet rs = null;
		//boolean flag = false;
		int autoIncrementKey = 0;
		try {
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, h.getUserId());
			pst.setString(2, h.getName());
			pst.setInt(3, h.getBill());
			pst.setInt(4, h.getRentType());
			pst.setInt(5, h.getKind());
			pst.setDouble(6, h.getArea());
			pst.setInt(7, h.getGuestNum());
			pst.setInt(8, h.getBedNum());
			pst.setInt(9, h.getBedroomNum());
			pst.setInt(10, h.getRoomNum());
			pst.setInt(11, h.getBedType());
			pst.setInt(12, h.getToiletNum());
			pst.setString(13, h.getRoomDesc());
			pst.setString(14, h.getUseRule());
			pst.setString(15, h.getFacility());
			pst.setString(16, h.getAddress());
			pst.setInt(17, h.getMinDay());
			pst.setInt(18, h.getMaxDay());
			pst.setInt(19, h.getRefundDay());
			pst.setString(20, h.getPayRule());
			pst.setDouble(21, h.getDayPrice());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			//flag = true;
			if (rs.next()) {  // 得到自增id
				autoIncrementKey = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}		
		return autoIncrementKey;
	}

	//private int getAutoIncrementId() {
		
	//}
	
	/** 删除房源
	 *  输入：House对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 */
	public boolean delete(House h) {
		Connection conn = util.getConnection();
		String sql = "UPDATE house SET del=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Entity.DEL);
			pst.setInt(2, h.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 更新房源
	 *  输入：House对象
	 *  输出：true：操作成功；false：操作失败
	 *  2015/07/20
	 *  2015/07/22：改正了sql参数和pst设置参数不相等的bug
	 *  2015/07/31: 增加picNum属性
	 *  2015/08/03: 增加state属性
	 */
	public boolean update(House h) {
		Connection conn = util.getConnection();
		String sql = "UPDATE house SET user_id=?,name=?,bill=?,rent_type=?,kind=?,area=?,guest_num=?,"
				+ "bed_num=?,bedroom_num=?,room_num=?,bed_type=?,toilet_num=?,room_desc=?,use_rule=?,"
				+ "facility=?,address=?,min_day=?,max_day=?,refund_day=?,pay_rule=?,day_price=?,pic_num=?,state=? WHERE id=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, h.getUserId());
			pst.setString(2, h.getName());
			pst.setInt(3, h.getBill());
			pst.setInt(4, h.getRentType());
			pst.setInt(5, h.getKind());
			pst.setDouble(6, h.getArea());
			pst.setInt(7, h.getGuestNum());
			pst.setInt(8, h.getBedNum());
			pst.setInt(9, h.getBedroomNum());
			pst.setInt(10, h.getRoomNum());
			pst.setInt(11, h.getBedType());
			pst.setInt(12, h.getToiletNum());
			pst.setString(13, h.getRoomDesc());
			pst.setString(14, h.getUseRule());
			pst.setString(15, h.getFacility());
			pst.setString(16, h.getAddress());
			pst.setInt(17, h.getMinDay());
			pst.setInt(18, h.getMaxDay());
			pst.setInt(19, h.getRefundDay());
			pst.setString(20, h.getPayRule());
			pst.setDouble(21, h.getDayPrice());
			pst.setInt(22, h.getPicNum());
			pst.setInt(23, h.getState());
			pst.setInt(24, h.getId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(null, pst, conn);
		}		
		return flag;
	}

	/** 根据id查找房源
	 *  输入：id
	 *  输出：House对象；null：房源不存在或已被删除
	 *  2015/07/20
	 *  2015/07/22  改正一处拼写错误
	 *  2015/07/31: 增加picNum属性
	 */
	public House findHouse(int id) {
		House h = null;
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM house WHERE id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {  
				h = new House();
				h.setId(id);
				h.setUserId(rs.getInt("user_id"));
				h.setName(rs.getString("name"));
				h.setBill(rs.getInt("bill"));
				h.setRentType(rs.getInt("rent_type"));
				h.setKind(rs.getInt("kind"));
				h.setArea(rs.getDouble("area"));
				h.setGuestNum(rs.getInt("guest_num"));
				h.setBedNum(rs.getInt("bed_num"));
				h.setBedroomNum(rs.getInt("bedroom_num"));
				h.setRoomNum(rs.getInt("room_num"));
				h.setBedType(rs.getInt("bed_type"));
				h.setToiletNum(rs.getInt("toilet_num"));
				h.setRoomDesc(rs.getString("room_desc"));
				h.setUseRule(rs.getString("use_rule"));
				h.setFacility(rs.getString("facility"));
				h.setAddress(rs.getString("address"));
				h.setMinDay(rs.getInt("min_day"));
				h.setMaxDay(rs.getInt("max_day"));
				h.setRefundDay(rs.getInt("refund_day"));
				h.setPayRule(rs.getString("pay_rule"));
				h.setDayPrice(rs.getDouble("day_price"));
				h.setCreateTime(new DateUtil(rs.getString("create_time")));
				h.setState(rs.getInt("state"));
				h.setPicNum(rs.getInt("pic_num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return h;		
	}

	/** 根据搜索条件查找房源
	 *  输入：搜索条件，页码
	 *  输出：List<House>；null：不存在符合条件的房源
	 *  2015/07/20
	 *  2015/07/22  改正一处拼写错误
	 *  2015/07/27 去除后台查询的分页功能，改为biz层实现
	 *  2015/07/31: 增加picNum属性
	 */
	public List<House> searchHouses(SearchUtil scondition) {
		List<House> hs = new ArrayList<House>();
		Connection conn = util.getConnection();
		String sql = scondition.getQuerySQL();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {  
				House h = new House();
				h.setId(rs.getInt("id"));
				h.setUserId(rs.getInt("user_id"));
				h.setName(rs.getString("name"));
				h.setBill(rs.getInt("bill"));
				h.setRentType(rs.getInt("rent_type"));
				h.setKind(rs.getInt("kind"));
				h.setArea(rs.getDouble("area"));
				h.setGuestNum(rs.getInt("guest_num"));
				h.setBedNum(rs.getInt("bed_num"));
				h.setBedroomNum(rs.getInt("bedroom_num"));
				h.setRoomNum(rs.getInt("room_num"));
				h.setBedType(rs.getInt("bed_type"));
				h.setToiletNum(rs.getInt("toilet_num"));
				h.setRoomDesc(rs.getString("room_desc"));
				h.setUseRule(rs.getString("use_rule"));
				h.setFacility(rs.getString("facility"));
				h.setAddress(rs.getString("address"));
				h.setMinDay(rs.getInt("min_day"));
				h.setMaxDay(rs.getInt("max_day"));
				h.setRefundDay(rs.getInt("refund_day"));
				h.setPayRule(rs.getString("pay_rule"));
				h.setDayPrice(rs.getDouble("day_price"));
				h.setCreateTime(new DateUtil(rs.getString("create_time")));
				h.setState(rs.getInt("state"));
				h.setPicNum(rs.getInt("pic_num"));
				hs.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (hs.size() == 0) ? null : hs;
	}

	/** 得到最新的x张图片，用于首页展示
	 *  输入：图片张数
	 *  输出：List<String>的图片路径；null：不存在符合条件的房源
	 *  注意：图片的命名规则：houseId+'pic1'
	 *  2015/07/22
	 *  2015/08/03:只得到审核通过的房屋
	 *  2015/08/04:修改图片文件名
	 */
	public List<String> getPictures(int num) {
		List<String> picPath = new ArrayList<String>();
		Connection conn = util.getConnection();
		String sql = "SELECT id FROM house WHERE del=0 AND state=1 ORDER BY id DESC LIMIT 0,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			rs = pst.executeQuery();
			while (rs.next()) {  
				picPath.add("h0_" + rs.getInt("id") + ".png");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (picPath.size() == 0) ? null : picPath;
	}

	/** 得到最新的房源，用于在首页展示
	 *  输入：希望获得的个数
	 *  输出：List<House>；null：没有房源
	 *  2015/07/20
	 *  2015/07/22  改正一处拼写错误
	 *  2015/07/28 调用时可指定获得的数量
	 *  2015/07/31: 增加picNum属性
	 *  2015/08/03:只得到审核通过的房屋
	 */
	public List<House> findLatestHouses(int num) {
		List<House> hs = new ArrayList<House>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM house WHERE del=0 AND state=1 ORDER BY id DESC LIMIT 0,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			rs = pst.executeQuery();
			while (rs.next()) {  
				House h = new House();
				h.setId(rs.getInt("id"));
				h.setUserId(rs.getInt("user_id"));
				h.setName(rs.getString("name"));
				h.setBill(rs.getInt("bill"));
				h.setRentType(rs.getInt("rent_type"));
				h.setKind(rs.getInt("kind"));
				h.setArea(rs.getDouble("area"));
				h.setGuestNum(rs.getInt("guest_num"));
				h.setBedNum(rs.getInt("bed_num"));
				h.setBedroomNum(rs.getInt("bedroom_num"));
				h.setRoomNum(rs.getInt("room_num"));
				h.setBedType(rs.getInt("bed_type"));
				h.setToiletNum(rs.getInt("toilet_num"));
				h.setRoomDesc(rs.getString("room_desc"));
				h.setUseRule(rs.getString("use_rule"));
				h.setFacility(rs.getString("facility"));
				h.setAddress(rs.getString("address"));
				h.setMinDay(rs.getInt("min_day"));
				h.setMaxDay(rs.getInt("max_day"));
				h.setRefundDay(rs.getInt("refund_day"));
				h.setPayRule(rs.getString("pay_rule"));
				h.setDayPrice(rs.getDouble("day_price"));
				h.setCreateTime(new DateUtil(rs.getString("create_time")));
				h.setState(rs.getInt("state"));
				h.setPicNum(rs.getInt("pic_num"));
				hs.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (hs.size() == 0) ? null : hs;
	}

	/** 得到所有的房屋并分页显示
	 *  输入：page
	 *  输出：List<House>；null：没有房源
	 *  2015/07/20
	 *  2015/07/22  改正一处拼写错误
	 *  2015/07/29 改变排序方式：按状态升序，这样未审核通过的房屋显示在前面
	 *  2015/07/31: 增加picNum属性
	 */
	public List<House> findHouses(int page) {
		List<House> hs = new ArrayList<House>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM house WHERE del=0 ORDER BY state ASC LIMIT ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, NUM_PER_PAGE * (page - 1));
			pst.setInt(2, NUM_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {  
				House h = new House();
				h.setId(rs.getInt("id"));
				h.setUserId(rs.getInt("user_id"));
				h.setName(rs.getString("name"));
				h.setBill(rs.getInt("bill"));
				h.setRentType(rs.getInt("rent_type"));
				h.setKind(rs.getInt("kind"));
				h.setArea(rs.getDouble("area"));
				h.setGuestNum(rs.getInt("guest_num"));
				h.setBedNum(rs.getInt("bed_num"));
				h.setBedroomNum(rs.getInt("bedroom_num"));
				h.setRoomNum(rs.getInt("room_num"));
				h.setBedType(rs.getInt("bed_type"));
				h.setToiletNum(rs.getInt("toilet_num"));
				h.setRoomDesc(rs.getString("room_desc"));
				h.setUseRule(rs.getString("use_rule"));
				h.setFacility(rs.getString("facility"));
				h.setAddress(rs.getString("address"));
				h.setMinDay(rs.getInt("min_day"));
				h.setMaxDay(rs.getInt("max_day"));
				h.setRefundDay(rs.getInt("refund_day"));
				h.setPayRule(rs.getString("pay_rule"));
				h.setDayPrice(rs.getDouble("day_price"));
				h.setCreateTime(new DateUtil(rs.getString("create_time")));
				h.setState(rs.getInt("state"));
				h.setPicNum(rs.getInt("pic_num"));
				hs.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (hs.size() == 0) ? null : hs;
	}

	/** 得到指定用户的所有的房屋并分页显示
	 *  输入：userId，page
	 *  输出：List<House>；null：没有房源
	 *  2015/07/20
	 *  2015/07/22  改正一处拼写错误，改正一处sql语法错误
	 *  2015/07/31: 增加picNum属性
	 */
	public List<House> findHousesByUserId(int userId, int page) {
		List<House> hs = new ArrayList<House>();
		Connection conn = util.getConnection();
		String sql = "SELECT * FROM house WHERE user_id=? AND del=0 ORDER BY id DESC LIMIT ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, NUM_PER_PAGE * (page - 1));
			pst.setInt(3, NUM_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {  
				House h = new House();
				h.setId(rs.getInt("id"));
				h.setUserId(rs.getInt("user_id"));
				h.setName(rs.getString("name"));
				h.setBill(rs.getInt("bill"));
				h.setRentType(rs.getInt("rent_type"));
				h.setKind(rs.getInt("kind"));
				h.setArea(rs.getDouble("area"));
				h.setGuestNum(rs.getInt("guest_num"));
				h.setBedNum(rs.getInt("bed_num"));
				h.setBedroomNum(rs.getInt("bedroom_num"));
				h.setRoomNum(rs.getInt("room_num"));
				h.setBedType(rs.getInt("bed_type"));
				h.setToiletNum(rs.getInt("toilet_num"));
				h.setRoomDesc(rs.getString("room_desc"));
				h.setUseRule(rs.getString("use_rule"));
				h.setFacility(rs.getString("facility"));
				h.setAddress(rs.getString("address"));
				h.setMinDay(rs.getInt("min_day"));
				h.setMaxDay(rs.getInt("max_day"));
				h.setRefundDay(rs.getInt("refund_day"));
				h.setPayRule(rs.getString("pay_rule"));
				h.setDayPrice(rs.getDouble("day_price"));
				h.setCreateTime(new DateUtil(rs.getString("create_time")));
				h.setState(rs.getInt("state"));
				h.setPicNum(rs.getInt("pic_num"));
				hs.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pst, conn);
		}
		return (hs.size() == 0) ? null : hs;
	}

	/** 得到查询所有房屋时的最大页数，用于分页
	 *  输出：int
	 *  2015/07/27
	 */
	public int maxPageOfHouses() {
		int num = 0;
		int page = 0;
		Connection conn = util.getConnection();
		String sql = "SELECT count(*) FROM house WHERE del=0";
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

	/** 得到根据用户id查询所有房屋时的最大页数，用于分页
	 *  输入：userId
	 *  输出：int
	 *  2015/07/27
	 */
	public int maxPageOfHousesByUserId(int userId) {
		int num = 0;
		int page = 0;
		Connection conn = util.getConnection();
		String sql = "SELECT count(*) FROM house WHERE user_id=? AND del=0";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
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
