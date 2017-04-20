package com.fudan.action;

import java.util.List;
import java.util.regex.Pattern;

import com.fudan.biz.IHouseBiz;
import com.fudan.biz.IMessageBiz;
import com.fudan.biz.IOrderBiz;
import com.fudan.biz.IUserBiz;
import com.fudan.biz.impl.*;
import com.fudan.entity.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
 
	private static final long serialVersionUID = 1650841713237760844L;
	private User u = new User();
	private int page;
	private int id;
	private String verification;
	private String result;
	private String oldPassword;
	private String rePassword;
	private List<Message> meList;
	private List<House> hoList;
	private List<Order> orList;
	IUserBiz uBiz = new UserBizImpl();
	IHouseBiz hoBiz = new HouseBizImpl();
	IMessageBiz meBiz = new MessageBizImpl();
	IOrderBiz orBiz = new OrderBizImpl();
	
	private int maxPage;
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	private List<User> userList;
	
	//注册2015/7/22 23测试修改
	public String add(){//添加用户前先调用检查函数,应该让Biz层做
		//System.out.println("ok");
		String num = (String)ActionContext.getContext().getSession().get("num");//验证码
		if (u.getUsername().equals("")) {//验证用户名
			result = "用户名不能为空";
			return "error";
		}
		else if (u.getUsername().length()>14) {//验证用户名
			result = "用户名不能超过14位";
			return "error";
		}
		else if(u.getPassword().length()<6){//验证密码
			result = "密码至少六位";
			return "error";
		}
		else if(u.getPassword().equals("")){//验证密码
			result = "密码不能为空";
			return "error";
		}
		else if ((Pattern.compile("^[0-9]{1,}$").matcher(u.getPassword()).matches())){//验证密码
			result = "密码不能是纯数字";
			return "error";
		}
		else if (Pattern.compile("^[^0-9a-zA-Z_\\-+=*/!@#$%^&*()~`,.;'\"￥]{1,}$").matcher(u.getPassword()).matches()){//验证密码
			result = "密码只允许数字,字母,符号";
			return "error";
		}
		else if(!u.getPassword().equals(rePassword)){//密码两次确认
			result = "密码不一致";
			return "error";
		}
		else if(!(Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(u.getMobile()).matches())){//手机号验证
			System.out.println("手机号不正确");
			result = "手机号不正确";
			return "error";
		}
		else if(!num.equals(verification)) {
			System.out.println("验证码错误"+num+verification);
			result = "验证码错误";
			return "error";//验证码错误，
		}	
		else if(uBiz.add(getU())) {
			System.out.println("注册成功");
			ActionContext.getContext().getSession().put("user", uBiz.findUserByName(u.getUsername()));//将user对象放入session中，2015/07/22
			result="0";//成功
			return "welcome";//2015/07/22
			
		}
		else {
			result = "用户已存在";
			return "error";//用户名已存在
		}
	}
	//登陆检查2015/7/22 23测试修改
	public String login(){
		//ActionContext.getContext().getSession().put("user", uBiz.findUserByName(u.getUsername()));//调试时使用
		//System.out.println("ok");
		String num = (String)ActionContext.getContext().getSession().get("num");//验证码
		if(!num.equals(verification)) {
			//System.out.println("验证码错误");
			result = "验证码错误";
			return "error";//验证码错误，
		}	
		if(uBiz.verify(u.getUsername(),u.getPassword())!=null) {
			ActionContext.getContext().getSession().put("user", uBiz.findUserByName(u.getUsername()));//将user对象放入session中，2015/07/22
			//System.out.println("welcome");
			result="0";
			return "welcome";//2015/07/22
		}
		else {
			//System.out.println("用户名或密码错误");
			result = "用户名或密码错误";
			return "error";//用户名或密码错误
		}
	}
	
	//登出2015/7/20
	public String logout() {
		setU(null);
		ActionContext.getContext().getSession().put("user", null);
		return "logout";
	}
	
	/**
	 * 删除用户
	 * 2015/08/04 改进写法
	 */
	public String delete(){
		return (uBiz.delete(u)) ? "success" : "error";
	}
	
	//显示修改用户资料界面2015/7/20
	public String toUpdate(){
		return "success";
	}
	
	/**
	 * 修改用户资料
	 * 2015/07/30
	 */
	public String update(){
		// 检测
		if (!u.getPassword().trim().equals("")) {  // 用户输入了新密码，认为用户要修改密码
			if (uBiz.verify(u.getUsername(), oldPassword) == null) {  // 旧密码不正确
				result = "旧密码不正确";
				return "error";
			} else if (u.getPassword().length() < 6) {
				result = "密码至少六位";
				return "error";
			} else if ((Pattern.compile("^[0-9]{1,}$").matcher(u.getPassword()).matches())) {
				result = "密码不能是纯数字";
				return "error";
			} else if (Pattern.compile("^[^0-9a-zA-Z_\\-+=*/!@#$%^&*()~`,.;'\"￥]{1,}$").matcher(u.getPassword()).matches()) {
				result = "密码只允许数字,字母,符号";
				return "error";
			} else if (!u.getPassword().equals(rePassword)) {
				result = "密码不一致";
				return "error";
			} 
		}
		if (!u.getMobile().trim().equals("") && !(Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(u.getMobile()).matches())) {
			result = "手机号不正确";
			return "error";
		} else if (!u.getEmail().trim().equals("") && !(Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$").matcher(u.getEmail()).matches())) {
			result = "邮箱不正确";
			return "error";
		} else {  // 更新资料		
			if (uBiz.update(u)) {  // 更新用户
				result = "0";
				return "success";
			} else {
				result = "数据库连接失败，请重试";
				return "error";
			}

		}
	}
	
	/**
	 * 去房屋审核的页面
	 * 2015/08/02
	 */
	public String toUserAdmin() {
		// 使用url的 ?page= 传值
		User u = (User) ActionContext.getContext().getSession().get("user");
		// 判断权限
		if (u.getState() == 0) {  // 不是管理员
			return "error";
		}
		// 处理page相关
		maxPage = uBiz.maxPageOfUsers();
		page = (page > maxPage || page < 1) ? 1 : page;  // 不合法的page，默认第一页
		// 设置主要参数
		setUserList(uBiz.findUsers(page));
		return "success";
	}
	

	//显示房屋管理界面2015/7/20
	public String toHouseAdmin(){
		setHoList(hoBiz.findHousesByUserId(u.getId(),getPage()));//2015/07/21修改
		return "success";
	}
	//显示发站内信界面,无需填充参数，直接跳转2015/7/20
	public String toSendMessage(){
		return "success";
	}
	//显示收信内信界面2015/7/20
	public String toReceiveMessage(){
		setMeList(meBiz.findMessageByReceiver(u.getId()));
		return "success";
	}
	//显示管理订单界面（我是房东）2015/7/20
	public String toOwnerOrders(){
		setOrList(orBiz.findOrderByOwner(u.getId()));
		return "success";
	}
	//显示管理订单界面（我是房客）2015/7/20
	public String toLogerOrders(){
		setOrList(orBiz.findOrderByUser(u.getId()));
		return "success";
	}
	
	//显示上传头像界面,无需填充参数，直接跳转2015/7/20
	public String toUploadAvatar(){
		return "success";
	}
	
	
	//上传头像功能,由uploadaction实现
	public String uploadAvatar(){
		return null;
	}
	
	//getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setU(User u) {
		this.u = u;
	}

	public User getU() {
		return u;
	}
	public List<House> getHoList() {
		return hoList;
	}
	public void setHoList(List<House> hoList) {
		this.hoList = hoList;
	}
	public List<Message> getMeList() {
		return meList;
	}
	public void setMeList(List<Message> meList) {
		this.meList = meList;
	}
	public List<Order> getOrList() {
		return orList;
	}
	public void setOrList(List<Order> orList) {
		this.orList = orList;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
