package com.fudan.action;

import java.util.List;

import com.fudan.biz.IOrderBiz;
import com.fudan.biz.impl.OrderBizImpl;
import com.fudan.entity.Comment;
import com.fudan.entity.Order;
import com.fudan.entity.User;
import com.fudan.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction  extends ActionSupport{
 
	private static final long serialVersionUID = 1L;
	private Order or=new Order();//不知为何对象传参失灵了。
	//一下为or对象的变量
	private int userId;
	private int houseId;
	private String checkinDate;
	private String checkoutDate;
	private double dayPrice;
	
	private String result;
	private String verification;
	IOrderBiz orBiz = new OrderBizImpl();
	
	private List<Order> orList;
	private int id;  // order id
	private Comment comment = new Comment();
	
	//增加订单2015/07/20 31号修改
	public String add(){
		//初始化or对象
		or.setUserId(userId);
		or.setHouseId(houseId);
		DateUtil chekinDateUtil = new DateUtil(checkinDate);
		DateUtil checkoutDateUtil = new DateUtil(checkoutDate);
		or.setCheckinDate(chekinDateUtil);
		or.setCheckoutDate(checkoutDateUtil);
		or.setDayPrice(dayPrice);
		String num = (String)ActionContext.getContext().getSession().get("num");//验证码
		if (!num.equals(verification)) {
			result = "验证码错误";
			return "error";//验证码错误，
		}
		if (!chekinDateUtil.isInitialized() || !checkoutDateUtil.isInitialized()) {//输入时间不正确
			result = "输入时间不正确";
			return "error";
		}
		if(orBiz.findConflictOrder(or)) {//订单冲突
			result = "改时间段已有订单";
			return "error";
		}
		if(orBiz.checkMinDay(or)) {//时间小于房屋最小时间 
			result = "您的入住时间小于最小天数";
			return "error";
		}
		if(orBiz.checkMaxDay(or)) {//时间大于房屋最大入住时间 
			result = "您的入住时间大于最大天数";
			return "error";
		}
		
		if(orBiz.add(getOr())) {
			result = "下单成功";
			return "success";
		} else {
			result = "对不起，由于商家原因，订单失败";
			return "error";
		}
	}
	
	/**
	 * 去我是房东的订单页面
	 * 2015/08/02
	 */
	public String toOrderOwner() {
		// 从session中读取user的id
		User u = (User) ActionContext.getContext().getSession().get("user");
		int userId = u.getId();
		// 设置主要参数
		orList = orBiz.findOrderByOwner(userId);
		return "success";
	}
	
	/**
	 * 去我是房客的订单页面
	 * 2015/08/02
	 */
	public String toOrderUser() {
		// 从session中读取user的id
		User u = (User) ActionContext.getContext().getSession().get("user");
		int userId = u.getId();
		// 设置主要参数
		orList = orBiz.findOrderByUser(userId);
		return "success";
	}
	
	/**
	 * 拒绝订单
	 * 2015/08/03
	 */
	public String refuseOrder() {
		Order o = orBiz.findOrder(id);
		return (orBiz.update(o, Order.REJECTED)) ? "success" : "error";		
	}
	
	/**
	 * 接受订单
	 * 2015/08/03
	 */
	public String acceptOrder() {
		Order o = orBiz.findOrder(id);
		return (orBiz.update(o, Order.ACCEPTED)) ? "success" : "error";		
	}
	
	/**
	 * 取消订单
	 * 2015/08/03
	 */
	public String cancelOrder() {
		Order o = orBiz.findOrder(id);
		return (orBiz.update(o, Order.CANCELED)) ? "success" : "error";		
	}
	
	/**
	 * 完成订单
	 * 2015/08/03
	 */
	public String finishOrder() {
		Order o = orBiz.findOrder(id);
		return (orBiz.update(o, Order.FINISHED)) ? "success" : "error";		
	}
	
	//getter and setter methods
	public Order getOr() {
		return or;
	}
	public void setOr(Order or) {
		this.or = or;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}
	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public double getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(double dayPrice) {
		this.dayPrice = dayPrice;
	}

	public List<Order> getOrList() {
		return orList;
	}

	public void setOrList(List<Order> orList) {
		this.orList = orList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
