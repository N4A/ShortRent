package com.fudan.biz.impl;

import java.util.List;

import com.fudan.biz.IMessageBiz;
import com.fudan.dao.IMessageDao;
import com.fudan.dao.IUserDao;
import com.fudan.dao.impl.MessageDaoImpl;
import com.fudan.dao.impl.UserDaoImpl;
import com.fudan.entity.Message;
import com.fudan.entity.User;

public class MessageBizImpl implements IMessageBiz {
	IUserDao uDao = new UserDaoImpl();
	IMessageDao mDao = new MessageDaoImpl();
	User u;
	
	/**
	 * 发站内信
	 * 输入：Message对象，这个对象需包含用户id，目标id和内容
	 * 输出：true：成功，false：失败
	 * 2015/07/31
	 */
	public boolean add(Message m) {
		return mDao.add(m);
	}
	
	/**
	 * 删除一条
	 * 获取由前台ajax传递的id
	 * 2015/08/03
	 */
	public boolean delete(int id) {
		return mDao.delete(mDao.findMessage(id));
	}

	//查询一条站内信2015/07/20
	@Override
	public Message findMessage(int id) {
		// TODO Auto-generated method stub
		return mDao.findMessage(id);
	}

	//查询发件人发出的信2015/07/20
	@Override
	public List<Message> findMessageBySender(int senderId) {
		// TODO Auto-generated method stub
		return mDao.findMessageBySender(senderId);
	}
	
	//查询收件箱里的信2015/07/20
	@Override
	public List<Message> findMessageByReceiver(int receiverId) {
		// TODO Auto-generated method stub
		return mDao.findMessageByReceiver(receiverId);
	}

	/**
	 * 阅读站内信
	 * 根据id得到站内信，将其状态改为已读
	 * 2015/08/03
	 */
	public boolean update(int id) {
		Message m = mDao.findMessage(id);
		m.setState(Message.READ);
		return mDao.update(m);
	}

}
