package com.fudan.action;

import java.util.List;

import com.fudan.biz.IMessageBiz;
import com.fudan.biz.IUserBiz;
import com.fudan.biz.impl.MessageBizImpl;
import com.fudan.biz.impl.UserBizImpl;
import com.fudan.entity.Message;
import com.fudan.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class MessageAction {
	private Message me = new Message();
	private List<Message> meList;
	private int messageId;//查询message
	private String receiverName;
	private int senderId;
	private int receiverId;
	private String result;
	IMessageBiz meBiz = new MessageBizImpl();
	IUserBiz uBiz = new UserBizImpl();
	
	/**
	 * 去发站内信的页面,要包括发送的站内信
	 * 2015/07/31
	 * 2015/08/03:检测空指针
	 */
	public String toSend() {
		// 从session中读取user的id
		User u = (User) ActionContext.getContext().getSession().get("user");
		int userId = u.getId();
		// 设置主要参数
		meList = meBiz.findMessageBySender(userId);
		// 替换用户id为用户名
		if (meList != null) {
			for (int i = 0; i < meList.size(); i++) {
				String targetName = uBiz.findUser(meList.get(i).getTargetId()).getUsername();
				meList.get(i).setTargetName(targetName);
			}
		}
		return "success";
	}
	
	/**
	 * 去站内信页面
	 * 2015/08/01
	 * 2015/08/03:检测空指针
	 */
	public String toMessage() {
		// 从session中读取user的id
		User u = (User) ActionContext.getContext().getSession().get("user");
		int userId = u.getId();
		// 设置主要参数
		meList = meBiz.findMessageByReceiver(userId);
		// 替换用户id为用户名
		if (meList != null) {
			for (int i = 0; i < meList.size(); i++) {
				String userName = uBiz.findUser(meList.get(i).getUserId()).getUsername();
				meList.get(i).setUserName(userName);
			}
		}
		return "success";
	}
	
	/**
	 * 发送站内信
	 * 需要先从用户输入的目标用户名得到目标id
	 * 2015/07/31
	 */
	public String send(){
		User targetUser = uBiz.findUserByName(receiverName);
		if (targetUser == null) {  // 用户名不存在
			result = "用户名不存在";
			return "error";
		} else if (me.getContent().length() > 140) {  // 太长
			result = "站内信超出长度";
			return "error";
		} else {
			me.setTargetId(targetUser.getId());  // 设置目标id
			if (meBiz.add(me)) {  // 增加信息
				result = "0";
				return "success";
			} else {
				result = "数据库连接失败，请重试";
				return "error";
			}
		}

	}

	/**
	 * 删除站内信
	 * 2015/08/03
	 */
	public String delete(){
		return (meBiz.delete(me.getId())) ? "success" : "error";
	}
	
	/**
	 * 阅读站内信
	 * 2015/08/03
	 */
	public String read(){
		return (meBiz.update(me.getId())) ? "success" : "error";
	}
	
	//得到一条站内信2015/07/20
	public String findMessage(){
		setMe(meBiz.findMessage(getMessageId()));
		return "success";
	}
	//根据发件人得到信息，实现发件箱功能2015/07/20
	public String findMessageBySender(){
		setMeList(meBiz.findMessageBySender(getSenderId()));
		return "success";
	}
	//根据收件人得到信息，实现收件箱功能2015/07/20
	public String findMessageByReceiver(){
		setMeList(meBiz.findMessageByReceiver(getReceiverId()));
		return "success";
	}
	
	//getter and setter methods
	public Message getMe() {
		return me;
	}
	public void setMe(Message me) {
		this.me = me;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public List<Message> getMeList() {
		return meList;
	}
	public void setMeList(List<Message> meList) {
		this.meList = meList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
