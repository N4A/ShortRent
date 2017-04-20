package com.fudan.biz;

import com.fudan.entity.Reply;

public interface IReplyBiz {
	public boolean add(Reply r);  // 新增回复2015/07/17
	public boolean delete(Reply r);  // 删除回复2015/07/17
	public Reply findReplyByComment(int commentId);  // 得到回复下的评论2015/07/17
	
}
