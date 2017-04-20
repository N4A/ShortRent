package com.fudan.biz;

import java.util.List;

import com.fudan.entity.Comment;

public interface ICommentBiz {
	public boolean add(Comment c);  // 新增评论2015/07/17
	public boolean delete(Comment c);  // 删除评论2015/07/17
	public List<Comment> findCommentByHouse(int houseId);  // 得到房源下所有评论2015/07/17

}
