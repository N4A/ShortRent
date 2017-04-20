package com.fudan.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.fudan.biz.ICommentBiz;
import com.fudan.biz.IUserBiz;
import com.fudan.dao.impl.CommentDaoImpl;
import com.fudan.entity.Comment;
import com.fudan.entity.User;

public class CommentBizImpl implements ICommentBiz {
    CommentDaoImpl dao = new CommentDaoImpl();
    List<Comment> co = new ArrayList<Comment>();
    IUserBiz uBiz = new UserBizImpl();
    //添加评论2015/07/20
	@Override
	public boolean add(Comment c) {
		// TODO Auto-generated method stub
		return dao.add(c);
	}
	
	//删除评论2015/07/20
	@Override
	public boolean delete(Comment c) {
		// TODO Auto-generated method stub
		return dao.delete(c);
	}
	
	//根据房屋ID得到所有评论2015/07/20
	@Override
	public List<Comment> findCommentByHouse(int houseId) {
		// TODO Auto-generated method stub
		co = dao.findCommentByHouse(houseId);
		if(co!=null){
			for(int i=0;i<co.size();i++){
				User u = uBiz.findUser(co.get(i).getUserId());
				co.get(i).setUsername(u.getUsername());
			}
		}
		return co;
	}
}
