package com.fudan.biz.impl;

import com.fudan.biz.IReplyBiz;
import com.fudan.dao.IReplyDao;
import com.fudan.dao.impl.ReplyDaoImpl;
import com.fudan.entity.Reply;
import com.fudan.util.SensitiveWordUtil;

public class ReplyBizImpl implements IReplyBiz {
	IReplyDao dao = new ReplyDaoImpl();
	
	//房东回复房客2015/7/20
	@Override
	public boolean add(Reply r) {
		if ((new SensitiveWordUtil()).hasSensitiveWord(r.getContent())) {
			return false;
		} else {
			return dao.add(r);
		}
	}

	//删除回复2015/7/20
	@Override
	public boolean delete(Reply r) {
		// TODO Auto-generated method stub
		return dao.delete(r);
	}

	//查询回复2015/7/20
	@Override
	public Reply findReplyByComment(int commentId) {
		// TODO Auto-generated method stub
		return dao.findReplyByComment(commentId);
	}

}
