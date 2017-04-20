package com.fudan.test;

import java.util.List;

import org.junit.Test;

import com.fudan.dao.IOrderDao;
import com.fudan.dao.impl.OrderDaoImpl;
import com.fudan.entity.Order;
import com.fudan.util.DateUtil;

public class OrderDaoTest {
	
	@Test  // 测试新增
	public void testAdd() {
		Order o = new Order();
		o.setHouseId(1);
		o.setUserId(2);
		o.setCheckinDate(new DateUtil("2015-07-24"));
		o.setCheckoutDate(new DateUtil("2015-07-26"));
		o.setDayPrice(200.02);
		IOrderDao od = new OrderDaoImpl();
		od.add(o);
	}
	
	@Test  // 测试由房客查找
	public void testFindOrderByUser() {
		IOrderDao od = new OrderDaoImpl();
		List<Order> os =  od.findOrderByUser(2);
		System.out.println(os.get(1).getDayPrice());
	}
	
	@Test  // 测试删除
	public void testDelete() {
		IOrderDao od = new OrderDaoImpl();
		List<Order> os =  od.findOrderByUser(2);
		od.delete(os.get(1));
	}
	
	@Test  // 测试更新
	public void testUpdate() {
		IOrderDao od = new OrderDaoImpl();
		List<Order> os =  od.findOrderByUser(2);
		os.get(1).setCheckDate(new DateUtil());
		os.get(1).setState(Order.ACCEPTED);
		od.update(os.get(1));
	}
	
	@Test  // 测试根据房东查询
	public void testFindOrderByOwner() {
		IOrderDao od = new OrderDaoImpl();
		List<Order> os =  od.findOrderByOwner(1);
		for (int i = 0; i < os.size(); i++) {
			System.out.println(os.get(i).getId());
		}
	}
	
	@Test  // 测试判断时间冲突
	public void testFindConflictOrder() {
		IOrderDao od = new OrderDaoImpl();
		List<Order> os =  od.findOrderByOwner(1);
		System.out.println(od.findConflictOrder(os.get(0)));
	}

}
