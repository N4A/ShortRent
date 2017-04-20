package com.fudan.action;

import java.util.List;

import com.fudan.biz.ICommentBiz;
import com.fudan.biz.IOrderBiz;
import com.fudan.biz.impl.CommentBizImpl;
import com.fudan.biz.impl.OrderBizImpl;
import com.fudan.entity.Comment;
import com.fudan.util.SensitiveWordUtil;

public class CommentAction {
	private Comment co = new Comment();
	private List<Comment> coList;
	private int commentId;
	private int houseId;
	private String result;
	ICommentBiz coBiz = new CommentBizImpl();
	IOrderBiz orBiz = new OrderBizImpl();
	
	/**
	 * 添加评论，注意订单状态改为已评论
	 * 2015/08/03
	 */
	public String add(){		
		if (co.getContent().length() > 140) {
			result = "评论太长啦";
			return "error";
		} else if (new SensitiveWordUtil().hasSensitiveWord(co.getContent())) {
			result = "评论存在不和谐内容，请修改";
			return "error";
		} else if (coBiz.add(co)) {
			result = "0";
			orBiz.updateCommentState(orBiz.findOrder(co.getOrderId()));  // 更新为已评论
			return "success";
		} else {
			result = "后台出错";
			return "error";
		}
	}
	
	/**
	 * 删除评论
	 * 2015/08/04 改进写法
	 */
	public String delete(){
		return (coBiz.delete(co)) ? "success" : "error";
	}

	//得到一所房子的所有评论2015/07/20
	public String findComments(){
		setCoList(coBiz.findCommentByHouse(getHouseId()));
		return "success";
	}
	
	//getter and setter methods2015/07/20
	public Comment getCo() {
		return co;
	}
	public void setCo(Comment co) {
		this.co = co;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public List<Comment> getCoList() {
		return coList;
	}
	public void setCoList(List<Comment> coList) {
		this.coList = coList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
