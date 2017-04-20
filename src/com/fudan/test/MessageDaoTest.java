package com.fudan.test;

import java.util.List;

import org.junit.Test;

import com.fudan.dao.IMessageDao;
import com.fudan.dao.impl.MessageDaoImpl;
import com.fudan.entity.Message;

public class MessageDaoTest {
	// 测试MessageDaoImpl类，完成于2015/07/21
	
	@Test  // 测试新增
	public void testAdd() {
		Message m = new Message();
		m.setUserId(1);
		m.setTargetId(2);
		m.setContent("hello admin");
		IMessageDao md = new MessageDaoImpl();
		md.add(m);
	}
	
	@Test  // 测试删除
	public void testDelete() {
		IMessageDao md = new MessageDaoImpl();
		Message m = md.findMessage(1);
		md.delete(m);
	}
	
	@Test  // 测试查找
	public void testFindMessage() {
		IMessageDao md = new MessageDaoImpl();
		Message m = md.findMessage(1);
		System.out.println(m.getContent());
	}
	
	@Test  // 测试根据发送者查找 
	public void testFindMessageBySender() {
		IMessageDao md = new MessageDaoImpl();
		List<Message> ms = md.findMessageBySender(1);
		for (int i = 0; i < ms.size(); i++) {
			System.out.println(ms.get(i).getContent());
		}
	}
	
	@Test  // 测试根据发送者查找 
	public void testFindMessageByReceiver() {
		IMessageDao md = new MessageDaoImpl();
		List<Message> ms = md.findMessageByReceiver(2);
		for (int i = 0; i < ms.size(); i++) {
			System.out.println(ms.get(i).getContent());
		}
	}
	
	@Test  // 测试更新阅读状态
	public void testUpdate() {
		IMessageDao md = new MessageDaoImpl();
		Message m = md.findMessage(1);
		md.update(m);
	}

}
