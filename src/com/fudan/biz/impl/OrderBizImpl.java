package com.fudan.biz.impl;

import java.util.List;

import com.fudan.biz.IOrderBiz;
import com.fudan.dao.IHouseDao;
import com.fudan.dao.IOrderDao;
import com.fudan.dao.IUserDao;
import com.fudan.dao.impl.HouseDaoImpl;
import com.fudan.dao.impl.OrderDaoImpl;
import com.fudan.dao.impl.UserDaoImpl;
import com.fudan.entity.Order;

public class OrderBizImpl implements IOrderBiz {
	IOrderDao dao = new OrderDaoImpl();
	IHouseDao hDao = new HouseDaoImpl();
	IUserDao uDao = new UserDaoImpl();
	
	//增加订单2015/07/20
	@Override
	public boolean add(Order o) {
		// TODO Auto-generated method stub
		
		return dao.add(o);
	}
	
	/**
	 * 更新订单状态
	 * 2015/08/03
	 */
	public boolean update(Order o, int state) {
		o.setState(state);
		// 接受订单时，要拒绝所有别的同一时间的订单
		if (state == Order.ACCEPTED) {
			if (!dao.update(o)) return false;  // 先更新这个订单，出错直接返回
			List<Order> os = dao.getConflictOrder(o);  // 得到所有房屋id相同的存在时间冲突的订单
			boolean flag = true;  // 存放标记位
			for (int i = 0; i < os.size(); i++) {
				if (os.get(i).getId() != o.getId()) {  
					// 如果不是当前订单
					os.get(i).setState(Order.REJECTED);
					if (!dao.update(os.get(i))) {
						flag = false;               // 设置为拒绝
					}
				}
			}
			return flag;  // 如果之前有错就返回false
		} else {  // 不是接受订单
			return dao.update(o);
		}
	}

	/**
	 * 更新评论状态为已评论
	 * 2015/08/03
	 */
	public boolean updateCommentState(Order o) {
		o.setCommentState(Order.COMMENTED);
		return dao.update(o);
	}
	
	//删除订单2015/07/20
	@Override
	public boolean delete(Order o) {
		// TODO Auto-generated method stub
		return dao.delete(o);
	}


//	//得到某一房屋的被订列表2015/07/20
//	@Override
//	public List<Order> findOrderByHouse(int houseId) {
//		// TODO Auto-generated method stub
//		return dao.findOrderByHouse(houseId);
//	}



	@Override
	public boolean findConflictOrder(Order o) {
		// TODO Auto-generated method stub
		return dao.findConflictOrder(o);
	}

	@Override
	public boolean checkMinDay(Order o) {
		HouseDaoImpl hoDao = new HouseDaoImpl();
		int days = o.getCheckoutDate().getDeltaDay(o.getCheckinDate());
		if(days<hoDao.findHouse(o.getHouseId()).getMinDay())
		// TODO Auto-generated method stub
			return true;
		else {
			return false;
		}
	}

	@Override
	public boolean checkMaxDay(Order o) {
		// TODO Auto-generated method stub
		HouseDaoImpl hoDao = new HouseDaoImpl();
		int days = o.getCheckoutDate().getDeltaDay(o.getCheckinDate());
		if(days>hoDao.findHouse(o.getHouseId()).getMaxDay())
		// TODO Auto-generated method stub
			return true;
		else {
			return false;
		}
	}

	/**
	 * 根据房东id得到订单
	 * 此处得到房客的名字、状态字符串、房屋名，用于展示
	 * 输入：房东id：userId
	 * 输出：List<Order>
	 * 2015/08/02
	 * 2015/08/03 字符串修正
	 * 2015/08/06 修正空指针问题
	 */
	public List<Order> findOrderByOwner(int userId) {
		List<Order> orList = dao.findOrderByOwner(userId);
		// 设置房客的名字、状态字符串、房屋名
		if (orList == null) return null;
		for (int i = 0; i < orList.size(); i++) {
			String userName = uDao.findUser(orList.get(i).getUserId()).getUsername();
			String houseName = hDao.findHouse(orList.get(i).getHouseId()).getName();
			String stateStr = "undefined";
			switch (orList.get(i).getState()) {
			case Order.WAITING:
				stateStr = "待受理";
				break;
			case Order.ACCEPTED:
				stateStr = "已接受";
				break;
			case Order.REJECTED:
				stateStr = "已拒绝";
				break;
			case Order.CANCELED:
				stateStr = "已取消";
				break;
			case Order.FINISHED:
				stateStr = "已完成";
				break;
			}
			orList.get(i).setUserName(userName);
			orList.get(i).setHouseName(houseName);
			orList.get(i).setStateStr(stateStr);
		}
		return orList;
	}
	
	/**
	 * 根据房客id得到订单
	 * 此处得到状态字符串、房屋名，用于展示
	 * 输入：房东id：userId
	 * 输出：List<Order>
	 * 2015/08/02
	 * 2015/08/03 字符串修正
	 */
	public List<Order> findOrderByUser(int userId) {
		List<Order> orList = dao.findOrderByUser(userId);
		// 设置状态字符串、房屋名
		for (int i = 0; i < orList.size(); i++) {
			String houseName = hDao.findHouse(orList.get(i).getHouseId()).getName();
			String stateStr = "undefined";
			switch (orList.get(i).getState()) {
			case Order.WAITING:
				stateStr = "待受理";
				break;
			case Order.ACCEPTED:
				stateStr = "已接受";
				break;
			case Order.REJECTED:
				stateStr = "已拒绝";
				break;
			case Order.CANCELED:
				stateStr = "已取消";
				break;
			case Order.FINISHED:
				stateStr = "已完成";
				break;
			}
			orList.get(i).setHouseName(houseName);
			orList.get(i).setStateStr(stateStr);
		}
		return orList;
	}

	/**
	 * 根据id得到订单
	 * 2015/08/03
	 */
	public Order findOrder(int id) {
		return dao.findOrder(id);
	}
}
