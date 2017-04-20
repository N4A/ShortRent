package com.fudan.util;

public class SearchUtil {
	
	final public static int GUEST_NUM_ORDER = 1, PRICE_ORDER = 0, AREA_ORDER = 2;  // 排序字段
	final public static int ASC = 0, DESC = 1;   // 升序 || 降序
	
	private DateUtil checkinDate, checkoutDate;  // 从订单表筛选掉这段时间已经有别的订单的房源
	private int minGuestNum;   // 房源能容纳的房客数应大于等于这个值
	private int rentType;      // 严格相等
	private int maxPrice;      // 不大于这个价格的房源
	private double minArea;    // 不小于这个面积的房源
	private int bedNum, roomNum, bedroomNum, toiletNum;  // 不小于这个值的参数
	private String address;    // 地址 模糊匹配，默认空则不匹配
	private int orderBy, sortBy;  // 指定排序的规则，默认按价格从低到高排序
	
	/**
	 * 在构造函数中设置默认值
	 * 2015/07/31
	 */
	public SearchUtil() {
		this.maxPrice = 9999;
		this.checkinDate = new DateUtil();
		this.checkoutDate = new DateUtil().add(7);
		this.rentType = 1;
		this.minGuestNum = 1;
		this.bedNum = 1;
		this.roomNum = 1;
		this.bedroomNum = 1;
		this.toiletNum = 1;
	}
	
	/** 得到用于查询的SQL代码
	 *  在调用此方法前先要对各种属性赋予合法的值
	 *  输出：String
	 *  2015/07/20
	 *  2015/07/22： 重大改进，移除了查询时间冲突的功能，将其移交给OrderDao处理
	 */
	public String getQuerySQL() {
		int deltaDay = checkoutDate.getDeltaDay(checkinDate);  // 用于房源限制的minDay和maxDay
		String orderByStr = (orderBy == 0) ? "day_price" : ((orderBy == 1) ? "guest_num" : "area");
		String sortByStr = (sortBy == 0) ? "ASC" : "DESC";
		// <del>由于涉及多表查询，不用*,要手动拼查询字段</del> 已退化为单表查询
		//String fields = "h.id,h.user_id,h.name,h.bill,h.rent_type,h.kind,h.area,h.guest_num,h.bed_num,"
		//		+ "h.bedroom_num,h.room_num,h.bed_type,h.toilet_num,h.room_desc,h.use_rule,h.facility,"
		//		+ "h.address,h.min_day,h.max_day,h.refund_day,h.pay_rule,h.day_price,h.create_time,h.state";
		// 主要的sql语句
		String sqlraw = "SELECT * FROM house WHERE state=1 AND del=0 AND guest_num>=%d AND rent_type=%d "
				+ "AND day_price<=%d AND area>=%f AND bed_num>=%d AND room_num>=%d AND bedroom_num>=%d "
				+ "AND toilet_num>=%d AND address LIKE %s AND min_day<=%d AND max_day>=%d ORDER BY %s %s ";
		String sql = String.format(sqlraw,  minGuestNum, rentType, maxPrice, minArea, bedNum, roomNum, 
				bedroomNum, toiletNum, "'%"+address+"%'", deltaDay, deltaDay, orderByStr, sortByStr);
		System.out.println(sql);
		return sql;
	}
	
	/*
	 * 不在此处查询时间冲突的原因：
	 * 1.技术上看，不能使用多表查询，这样无法查询当前房屋之前没有订单的情况，没有订单的情况下查找出来却不符合条件，完全不符合常理
	 * 2.既然如此，需要做两次查询，再将结果集相减。而这样从分工上看，查询时间冲突属于订单类的查询，而其他条件属于房屋的查询，不应该在
	 *   查询房屋的dao中访问查询订单的方法。
	 * 因此：关于查询结果的筛选请见HouseBiz中
	 */
	
	/**
	 * 得到一个clone
	 * 2015/08/04
	 */
	public SearchUtil clone() {
		SearchUtil n = new SearchUtil();
		n.address = this.address;
		n.bedNum = this.bedNum;
		n.bedroomNum = this.bedroomNum;
		n.checkinDate = this.checkinDate;
		n.checkoutDate = this.checkoutDate;
		n.maxPrice = this.maxPrice;
		n.minArea = this.minArea;
		n.minGuestNum = this.minGuestNum;
		n.orderBy = this.orderBy;
		n.rentType = this.rentType;
		n.roomNum = this.roomNum;
		n.sortBy = this.sortBy;
		n.toiletNum = this.toiletNum;
		return n;
	}
	
	public DateUtil getCheckinDate() {
		return checkinDate;
	}

	public DateUtil getCheckoutDate() {
		return checkoutDate;
	}

	public int getMinGuestNum() {
		return minGuestNum;
	}

	public int getRentType() {
		return rentType;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public double getMinArea() {
		return minArea;
	}

	public int getBedNum() {
		return bedNum;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public int getBedroomNum() {
		return bedroomNum;
	}

	public int getToiletNum() {
		return toiletNum;
	}

	public String getAddress() {
		return address;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setCheckinDate(DateUtil checkinDate) {
		this.checkinDate = checkinDate;
	}

	public void setCheckoutDate(DateUtil checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public void setMinGuestNum(int minGuestNum) {
		this.minGuestNum = minGuestNum;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinArea(double minArea) {
		this.minArea = minArea;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public void setBedroomNum(int bedroomNum) {
		this.bedroomNum = bedroomNum;
	}

	public void setToiletNum(int toiletNum) {
		this.toiletNum = toiletNum;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

}
