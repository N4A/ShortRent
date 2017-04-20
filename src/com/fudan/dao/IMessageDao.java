package com.fudan.dao;

import java.util.List;

import com.fudan.entity.Message;

public interface IMessageDao {
	
	public boolean add(Message m);  // 新增（发出）信息
	public boolean update(Message m);  // 更改信息（就是改变该信息的阅读状态）
	public boolean delete(Message m);  // 删除信息
	public Message findMessage(int id);  // 得到一条信息
	public List<Message> findMessageBySender(int senderId);  // 得到你发出的所有信息
	public List<Message> findMessageByReceiver(int receiverId);  // 得到你收到的所有信息

}
