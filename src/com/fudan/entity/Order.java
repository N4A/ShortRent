package com.fudan.entity;

import com.fudan.util.DateUtil;

public class Order extends Entity {
	
	final public static int WAITING = 0, ACCEPTED = 1, CANCELED = 2, REJECTED = 3, FINISHED = 4;
	final public static int UNCOMMMENT = 0, COMMENTED = 1;
	
	private int id, houseId, userId;
	private String orderNum; 
	private DateUtil checkinDate, checkoutDate, orderDate, checkDate;
	private double dayPrice;
	private int state, commentState, del;
	private String userName, ownerName, stateStr, houseName;  // 临时存放用
	
	public int getId() {
		return id;
	}
	
	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public DateUtil getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(DateUtil checkinDate) {
		this.checkinDate = checkinDate;
	}

	public DateUtil getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(DateUtil checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public DateUtil getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(DateUtil orderDate) {
		this.orderDate = orderDate;
	}

	public DateUtil getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(DateUtil checkDate) {
		this.checkDate = checkDate;
	}

	public double getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(double dayPrice) {
		this.dayPrice = dayPrice;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCommentState() {
		return commentState;
	}

	public void setCommentState(int commentState) {
		this.commentState = commentState;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

}
