package com.fudan.action;

import com.fudan.biz.IUserBiz;
import com.fudan.biz.impl.UserBizImpl;
import com.fudan.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class RegisterAction {
	private User u = new User();
	IUserBiz uBiz = new UserBizImpl();
	
	//注册2015/7/22
	public String execute(){//添加用户前先调用检查函数,应该让Biz层做
		if (uBiz.add(getU())) {
			ActionContext.getContext().getSession().put("user", uBiz.findUserByName(u.getUsername()));//将user对象放入session中，2015/07/22
			return "welcome";//2015/07/22
		}
		else {
			return "error";//用户名已存在
		}
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
}
