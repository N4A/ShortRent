package com.fudan.dao;

import java.util.List;

import com.fudan.entity.Order;

public interface IOrderDao {
	
	public boolean add(Order o);  // 新增订单
	public boolean update(Order o);  // 更新订单
	public boolean delete(Order o);  // 删除订单
	public List<Order> findOrderByUser(int userId);  // 由房客查询订单
	public List<Order> findOrderByOwner(int ownerId);  // 房东由房源查询订单
	public boolean findConflictOrder(Order o);  // 查找是否有与当前订单时间冲突的订单
	public Order findOrder(int id);   // 根据id得到订单
	public List<Order> getConflictOrder(Order oc);

}
