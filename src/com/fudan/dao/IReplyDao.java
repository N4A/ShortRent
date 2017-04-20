package com.fudan.dao;

import com.fudan.entity.Reply;

public interface IReplyDao {

	public boolean add(Reply r);  // 新增回复
	public boolean delete(Reply r);  // 删除回复
	public Reply findReplyByComment(int commentId);  // 得到回复下的评论
	
}
