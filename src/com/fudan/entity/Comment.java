package com.fudan.entity;

public class Comment extends Entity {
	
	private int id, userId, houseId, rank;
	private String content, username; // username不用于数据库相关操作，只用于取出时的临时存放
	private int del;
	private int orderId;  // 临时存放
	
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
	
	public int getHouseId() {
		return houseId;
	}
	
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getDel() {
		return del;
	}
	
	public void setDel(int del) {
		this.del = del;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
