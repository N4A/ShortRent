package com.fudan.dao;

import java.util.List;

import com.fudan.entity.Comment;

public interface ICommentDao {
	
	public boolean add(Comment c);  // 新增评论
	public boolean delete(Comment c);  // 删除评论
	public List<Comment> findCommentByHouse(int houseId);  // 得到房源下所有评论

}
