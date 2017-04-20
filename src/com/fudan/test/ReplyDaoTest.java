package com.fudan.test;

import org.junit.Test;

import com.fudan.dao.IReplyDao;
import com.fudan.dao.impl.ReplyDaoImpl;
import com.fudan.entity.Reply;

public class ReplyDaoTest {
	// 测试ReplyDao，完成于2015/07/22
	
	@Test  // 测试新增
	public void testAdd() {
		Reply r = new Reply();
		r.setUserId(1);
		r.setCommentId(1);
		r.setContent("谢谢！");
		IReplyDao rd = new ReplyDaoImpl();
		rd.add(r);
	}
	
	@Test  // 测试得到
	public void testFindReplyByComment() {
		IReplyDao rd = new ReplyDaoImpl();
		Reply r = rd.findReplyByComment(1);
		System.out.println(r.getContent());
	}
	
	@Test  // 测试删除
	public void testDelete() {
		IReplyDao rd = new ReplyDaoImpl();
		Reply r = rd.findReplyByComment(1);
		rd.delete(r);
	}

}
