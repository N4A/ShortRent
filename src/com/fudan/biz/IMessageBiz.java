package com.fudan.biz;

import java.util.List;

import com.fudan.entity.Message;

public interface IMessageBiz {
	public boolean add(Message m);  // 新增（发出）信息2015/07/17
	public boolean delete(int id);  // 删除信息2015/07/17
	public boolean update(int id);  // 修改阅读状态为已读2015/07/20
	public Message findMessage(int id);  // 得到一条信息2015/07/17
	public List<Message> findMessageBySender(int senderId);  // 得到你发出的所有信息2015/07/17
	public List<Message> findMessageByReceiver(int receiverId);  // 得到你收到的所有信息2015/07/17

}
