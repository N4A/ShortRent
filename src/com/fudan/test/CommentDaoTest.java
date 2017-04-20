package com.fudan.test;

import java.util.List;

import org.junit.Test;

import com.fudan.dao.ICommentDao;
import com.fudan.dao.impl.CommentDaoImpl;
import com.fudan.entity.Comment;

public class CommentDaoTest {
	// 测试CommentDao， 完成于2015/07/22
	
	@Test  // 测试新增
	public void testAdd() {
		Comment c = new Comment();
		c.setUserId(2);
		c.setHouseId(1);
		c.setContent("我很满意");
		ICommentDao cd = new CommentDaoImpl();
		cd.add(c);	
	}
	
	@Test  // 测试查找
	public void testFindCommentByHouseId() {
		ICommentDao cd = new CommentDaoImpl();
		List<Comment> cs = cd.findCommentByHouse(1);
		for (int i = 0; i < cs.size(); i++) {
			System.out.println(cs.get(i).getContent());
		}
	}
	
	@Test // 测试删除
	public void testDelete() {
		ICommentDao cd = new CommentDaoImpl();
		List<Comment> cs = cd.findCommentByHouse(1);
		cd.delete(cs.get(0));
	}

}
