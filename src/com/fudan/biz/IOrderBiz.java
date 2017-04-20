package com.fudan.biz;

import java.util.List;

import com.fudan.entity.Order;

public interface IOrderBiz {
	public boolean add(Order o);  // 新增订单2015/07/17
	public boolean update(Order o, int state);  // 更新订单2015/07/17
	public boolean delete(Order o);  // 删除订单2015/07/17
	public List<Order> findOrderByUser(int userId);  // 由房客查询订单2015/07/17
	//public List<Order> findOrderByHouse(int houseId);  // 房东由房源查询订单2015/07/17
	public List<Order> findOrderByOwner(int userId);// 房东查询订单2015/07/20
	public boolean findConflictOrder(Order o);  // 查找是否有与当前订单时间冲突的订单2015/07/31
	public boolean checkMinDay(Order o);//检擦订单是否小于最小入住时间2015/07/31
	public boolean checkMaxDay(Order o);//检查订单是否大于最大入住时间2015/07/31
	public Order findOrder(int id);   // 得到订单
	public boolean updateCommentState(Order o);  // 更新评论状态
}
