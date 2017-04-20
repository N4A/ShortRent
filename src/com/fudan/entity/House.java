package com.fudan.entity;

import com.fudan.util.DateUtil;

public class House extends Entity {
	
	final public static int NO_BILL = 0, HAS_BILL = 1;   // provide bill?
	final public static int RENT_HOUSE = 1, RENT_ROOM = 2, RENT_BED = 3;  // 整租 | 单间 | 床位
	final public static int BHOTEL = 1, OHOTEL = 2, INN = 3, RESIDENCE = 4;  // 酒店 | 旅店 | 客栈 | 民居
	final public static int DOUBLE_BED = 1, SINGLE_BED = 2, HIGH_LOW_BED = 3;  // 单人床 | 双人床 | 高低床
	final public static int EXAMINING = 0, POSTED = 1, REJECTED = 2;  // 审核中 | 已发布 | 已拒绝 
	
	private int id, userId;
	private String name, roomDesc, useRule, facility, address, payRule;
	private int picNum;   // 保存图片数目
	private int guestNum, bedNum, bedroomNum, roomNum, toiletNum, minDay, maxDay, refundDay;
	private double area, dayPrice;
	private int bill, rentType, kind, bedType, state, del;
	private DateUtil createTime;
	private String state_str, json_str;  // 用于临时存放state对应的字符串，与数据库无关
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public String getUseRule() {
		return useRule;
	}

	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayRule() {
		return payRule;
	}

	public void setPayRule(String payRule) {
		this.payRule = payRule;
	}

	public int getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}

	public int getBedNum() {
		return bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

	public int getBedroomNum() {
		return bedroomNum;
	}

	public void setBedroomNum(int bedroomNum) {
		this.bedroomNum = bedroomNum;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getToiletNum() {
		return toiletNum;
	}

	public void setToiletNum(int toiletNum) {
		this.toiletNum = toiletNum;
	}

	public int getMinDay() {
		return minDay;
	}

	public void setMinDay(int minDay) {
		this.minDay = minDay;
	}

	public int getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}

	public int getRefundDay() {
		return refundDay;
	}

	public void setRefundDay(int refundDay) {
		this.refundDay = refundDay;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(double dayPrice) {
		this.dayPrice = dayPrice;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public int getRentType() {
		return rentType;
	}

	public void setRentType(int rentType) {
		this.rentType = rentType;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getBedType() {
		return bedType;
	}

	public void setBedType(int bedType) {
		this.bedType = bedType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public DateUtil getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateUtil createTime) {
		this.createTime = createTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
	public int getPicNum() {
		return picNum;
	}

	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}

	public String getState_str() {
		return state_str;
	}

	public void setState_str(String state_str) {
		this.state_str = state_str;
	}

	/**
	 * 将房屋对象转换为保存了必要信息的json字符串。
	 * 用于前台地图弹出菜单的信息展示。
	 * 因为较为简单所以不使用gson等包处理了，手动拼就行。
	 * 2015/07/24
	 * 2015/08/04 修复字符串值没有引号的问题
	 */
	public String toJSONString() {
		return String.format("{name:'%s',address:'%s',dayPrice:'%s'}",name, address, dayPrice);
	}

	public String getJson_str() {
		return json_str;
	}

	public void setJson_str(String json_str) {
		this.json_str = json_str;
	}
	
}
