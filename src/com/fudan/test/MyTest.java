package com.fudan.test;

import java.sql.Connection;

import org.junit.Test;

import com.fudan.biz.impl.*;
import com.fudan.dao.impl.*;
import com.fudan.entity.*;
import com.fudan.util.JdbcUtil;
import com.fudan.util.SearchUtil;

public class MyTest {
	
//	//工具测试2015/07/17
//	@Test
//	public void testConnection() {
//		Connection conn = new JdbcUtil().getConnection();
//		System.out.println("this conn is: "+conn);
//	}
//	
//	//dao层测试
//	//评论测试2015/07/17
//	@Test
//	//增加评论测试2015/07/17
//	public void testCoDaoAdd() {
//		CommentDaoImpl coDao = new CommentDaoImpl();
//		Comment co = new Comment();
//		co.setContent("hello");
//		if(coDao.add(co)){
//			System.out.println("新增评论成功");
//		}
//	}
//	@Test
//	//删除评论测试2015/07/17
//	public void testCoDaoDelete() {
//		CommentDaoImpl coDao = new CommentDaoImpl();
//		Comment co = new Comment();
//		co.setId(1);
//		if(coDao.delete(co)){
//			System.out.println("删除评论成功");
//		}
//	}
//	@Test
//	//查询评论测试2015/07/17
//	public void testCoDaoFind() {
//		CommentDaoImpl coDao = new CommentDaoImpl();
//		int houseId = 1;
//		System.out.println("打出第一条评论内容(有内容则成功)："+coDao.findCommentByHouse(houseId).get(0).getContent());
//	}
//	
//	//房屋操作测试
//	@Test
//	//增加房屋测试2015/07/17
//	public void testhoDaoAdd() {
//		HouseDaoImpl hoDao = new HouseDaoImpl();
//		House ho = new House();
//		ho.setName("tanxin");
//		if(hoDao.add(ho) != 0){
//			System.out.println("新增房屋成功");
//		}
//	}
//	@Test
//	//删除房屋测试2015/07/17
//	public void testhoDaoDelete() {
//		HouseDaoImpl hoDao = new HouseDaoImpl();
//		House ho = new House();
//		ho.setId(1);
//		if(hoDao.delete(ho)){
//			System.out.println("删除房屋成功");
//		}
//	}
//	@Test
//	//修改房屋测试2015/07/17
//	public void testhoDaoUpdate() {
//		HouseDaoImpl hoDao = new HouseDaoImpl();
//		House ho = new House();
//		ho.setId(2);
//		ho.setName("liu");
//		if(hoDao.update(ho)){
//			System.out.println("修改房屋成功");
//		}
//	}
//	@Test
//	//查询一个房屋测试2015/07/17
//	public void testhoDaoFindHouse() {
//		HouseDaoImpl hoDao = new HouseDaoImpl();
//		int id = 3;
//		System.out.println("房屋名（有内容则成功）："+hoDao.findHouse(id).getName());
//	}
//	@Test
//	//查询多个房屋测试2015/07/21
//	public void testhoDaoFindHouses() {
//		HouseDaoImpl hoDao = new HouseDaoImpl();
//		SearchUtil sc = new SearchUtil();//待补充
//		int page = 3;
//		System.out.println("房屋名（有内容则成功）："+hoDao.searchHouses(sc).get(0).getName());
//	}
//	
//	//站内信测试
//	@Test
//	//增加站内信测试2015/07/17
//	public void testmeDaoAdd() {
//		MessageDaoImpl meDao = new MessageDaoImpl();
//		Message me = new Message();
//		me.setContent("hello");
//		if(meDao.add(me)){
//			System.out.println("增加站内信成功");
//		}
//	}
//	@Test
//	//删除站内信测试2015/07/17
//	public void testmeDaoDelete() {
//		MessageDaoImpl meDao = new MessageDaoImpl();
//		Message me = new Message();
//		me.setId(1);
//		if(meDao.delete(me)){
//			System.out.println("删除站内信成功");
//		}
//	}
//	@Test
//	//查询一条站内信测试2015/07/17
//	public void testmeDaoFindMessage() {
//		MessageDaoImpl meDao = new MessageDaoImpl();
//		int id = 2;
//		System.out.println("查询一条站内信（有内容则ok）："+meDao.findMessage(id).getContent());
//	}
//	@Test
//	//查询发送方站内信测试2015/07/17
//	public void testmeDaoFindMessageBySender() {
//		MessageDaoImpl meDao = new MessageDaoImpl();
//		int id = 1;
//		System.out.println("查询发出的信（有内容则ok）："+meDao.findMessageBySender(id).get(0).getContent());
//	}
//	@Test
//	//查询发送方站内信测试2015/07/17
//	public void testmeDaoFindMessageByReceiver() {
//		MessageDaoImpl meDao = new MessageDaoImpl();
//		int id = 2;
//		System.out.println("查询收到的信（有内容则ok）："+meDao.findMessageByReceiver(id).get(0).getContent());
//	}
//	
//	///订单测试
//	@Test
//	//增加订单测试2015/07/17
//	public void testorDaoAdd() {
//		OrderDaoImpl orDao = new OrderDaoImpl();
//		Order or = new Order();
//		or.setDayPrice(123.4);
//		if(orDao.add(or)){
//			System.out.println("增加订单成功");
//		}
//	}
//	
//	@Test
//	//删除订单测试2015/07/17
//	public void testorDaoDelete() {
//		OrderDaoImpl orDao = new OrderDaoImpl();
//		Order or = new Order();
//		or.setId(1);
//		if(orDao.delete(or)){
//			System.out.println("删除订单成功");
//		}
//	}
//	@Test
//	//更新订单测试2015/07/17
//	public void testorDaoUpdate() {
//		OrderDaoImpl orDao = new OrderDaoImpl();
//		Order or = new Order();
//		or.setId(1);
//		if(orDao.update(or)){
//			System.out.println("更新订单成功");
//		}
//	}
//	@Test
//	//查询用户订单测试2015/07/17
//	public void testorDaoFindByUser() {
//		OrderDaoImpl orDao = new OrderDaoImpl();
//		int id =1;
//		System.out.println("查询用户订单(有内容则ok):"+orDao.findOrderByUser(id).get(0).getDayPrice());
//	}
//	@Test
//	//查询房屋订单测试2015/07/17
//	public void testorDaoFindByHouse() {
//		OrderDaoImpl orDao = new OrderDaoImpl();
//		int id =1;
//		System.out.println("查询房屋订单(有内容则ok):"+orDao.findOrderByOwner(id).get(0).getDayPrice());
//	}
//	
//	//房主回复测试
//	@Test
//	//增加回复测试2015/07/17
//	public void testreDaoAdd() {
//		ReplyDaoImpl reDao = new ReplyDaoImpl();
//		Reply re = new Reply();
//		re.setContent("hello");
//		if(reDao.add(re)){
//			System.out.println("增加回复成功");
//		}
//	}
//	@Test
//	//删除回复测试2015/07/17
//	public void testreDaoDelete() {
//		ReplyDaoImpl reDao = new ReplyDaoImpl();
//		Reply re = new Reply();
//		re.setId(1);
//		if(reDao.delete(re)){
//			System.out.println("删除回复成功");
//		}
//	}
//	@Test
//	//查询回复测试2015/07/17
//	public void testreDaoFind() {
//		ReplyDaoImpl reDao = new ReplyDaoImpl();
//		int id =1;
//		System.out.println("查询回复结果（有内容则ok）："+reDao.findReplyByComment(id).getContent());
//	}
//	
//	//用户管理测试
//	@Test
//	//增加用户测试2015/07/17
//	public void testusDaoAdd() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		User us = new User();
//		us.setUsername("tan");
//		if(usDao.add(us)){
//			System.out.println("增加用户成功");
//		}
//	}
//	@Test
//	//删除用户测试2015/07/17
//	public void testusDaoDelete() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		User us = new User();
//		us.setId(1);
//		if(usDao.delete(us)){
//			System.out.println("删除用户成功");
//		}
//	}
//	@Test
//	//修改用户测试
//	public void testusDaoUpdate() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		User us = new User();
//		us.setId(2);
//		us.setPassword("123456");
//		if(usDao.update(us)){
//			System.out.println("修改用户成功");
//		}
//	}
//	@Test
//	//验证用户测试
//	public void testusDaoVerify() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		if(usDao.verify("tan","123456")==null){
//			System.out.println("验证用户成功：用户密码错误");
//		}
//		else{
//			System.out.println("验证用户成功：欢迎你tan");
//		}
//	}
//	@Test
//	//查询用户测试
//	public void testusDaoFindUser() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		User us = new User();
//		us.setId(1);
//		if(usDao.findUser(us.getId())==null){
//			System.out.println("查询用户成功：用户不存在");
//		}
//		else{
//			System.out.println("查询用户成功：欢迎你"+usDao.findUser(us.getId()).getUsername());
//		}
//	}
//	@Test
//	//以name查询用户测试
//	public void testusDaoFindUserByName() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		if(usDao.findUserByName("tan")==null){
//			System.out.println("查询用户成功：用户不存在");
//		}
//		else{
//			System.out.println("查询用户成功：欢迎你tan");
//		}
//	}
//	@Test
//	//查询多用户测试
//	public void testusDaoFindUsers() {
//		UserDaoImpl usDao = new UserDaoImpl();
//		int page = 1;
//		System.out.println("查询多用户（有内容则ok）："+usDao.findUsers(page).get(0).getUsername());
//	}
//	
//	//Biz层测试
//	//评论测试
//	@Test
//	//增加评论测试
//	public void testCoBizAdd() {
//		CommentBizImpl coBiz = new CommentBizImpl();
//		Comment co = new Comment();
//		co.setContent("hello");
//		if(coBiz.add(co)){
//			System.out.println("新增评论成功");
//		}
//	}
//	@Test
//	//删除评论测试
//	public void testCoBizDelete() {
//		CommentBizImpl coBiz = new CommentBizImpl();
//		Comment co = new Comment();
//		co.setId(1);
//		if(coBiz.delete(co)){
//			System.out.println("删除评论成功");
//		}
//	}
//	@Test
//	//查询评论测试
//	public void testCoBizFind() {
//		CommentBizImpl coBiz = new CommentBizImpl();
//		int houseId = 1;
//		System.out.println("打出第一条评论内容(有内容则成功)："+coBiz.findCommentByHouse(houseId).get(0).getContent());
//	}
//	
//	//房屋操作测试
//	@Test
//	//增加房屋测试
//	public void testhoBizAdd() {
//		HouseBizImpl hoBiz = new HouseBizImpl();
//		House ho = new House();
//		ho.setName("tanxin");
//		if(hoBiz.add(ho) != 0){
//			System.out.println("新增房屋成功");
//		}
//	}
//	@Test
//	//删除房屋测试
//	public void testhoBizDelete() {
//		HouseBizImpl hoBiz = new HouseBizImpl();
//		House ho = new House();
//		ho.setId(1);
//		if(hoBiz.delete(ho) != 0){
//			System.out.println("删除房屋成功");
//		}
//	}
//	@Test
//	//修改房屋测试
//	public void testhoBizUpdate() {
//		HouseBizImpl hoBiz = new HouseBizImpl();
//		House ho = new House();
//		ho.setId(2);
//		ho.setName("liu");
//		if(hoBiz.update(ho)){
//			System.out.println("修改房屋成功");
//		}
//	}
//	@Test
//	//查询一个房屋测试
//	public void testhoBizFindHouse() {
//		HouseBizImpl hoBiz = new HouseBizImpl();
//		int id = 3;
//		System.out.println("房屋名（有内容则成功）："+hoBiz.findHouse(id).getName());
//	}
//	@Test
//	//查询多个房屋测试
//	public void testhoBizFindHouses() {
//		HouseBizImpl hoBiz = new HouseBizImpl();
//		SearchUtil sc = new SearchUtil();//待补充
//		int page = 3;
//		System.out.println("房屋名（有内容则成功）："+hoBiz.searchHouses(sc,page).get(0).getName());
//	}
//	
//	//站内信测试
//	@Test
//	//增加站内信测试
//	public void testmeBizAdd() {
//		MessageBizImpl meBiz = new MessageBizImpl();
//		Message me = new Message();
//		me.setContent("hello");
//		if(meBiz.add(me,"tan")){//tan是收件人姓名
//			System.out.println("增加站内信成功");
//		}
//	}
//	@Test
//	//删除站内信测试
//	public void testmeBizDelete() {
//		MessageBizImpl meBiz = new MessageBizImpl();
//		Message me = new Message();
//		me.setId(1);
//		if(meBiz.delete(me)){
//			System.out.println("删除站内信成功");
//		}
//	}
//	@Test
//	//查询一条站内信测试
//	public void testmeBizFindMessage() {
//		MessageBizImpl meBiz = new MessageBizImpl();
//		int id = 2;
//		System.out.println("查询一条站内信（有内容则ok）："+meBiz.findMessage(id).getContent());
//	}
//	@Test
//	//查询发送方站内信测试
//	public void testmeBizFindMessageBySender() {
//		MessageBizImpl meBiz = new MessageBizImpl();
//		int id = 1;
//		System.out.println("查询发出的信（有内容则ok）："+meBiz.findMessageBySender(id).get(0).getContent());
//	}
//	@Test
//	//查询发送方站内信测试
//	public void testmeBizFindMessageByReceiver() {
//		MessageBizImpl meBiz = new MessageBizImpl();
//		int id = 2;
//		System.out.println("查询收到的信（有内容则ok）："+meBiz.findMessageByReceiver(id).get(0).getContent());
//	}
//	
//	///订单测试
//	@Test
//	//增加订单测试
//	public void testorBizAdd() {
//		OrderBizImpl orBiz = new OrderBizImpl();
//		Order or = new Order();
//		or.setDayPrice(123.4);
//		if(orBiz.add(or)){
//			System.out.println("增加订单成功");
//		}
//	}
//	
//	@Test
//	//删除订单测试
//	public void testorBizDelete() {
//		OrderBizImpl orBiz = new OrderBizImpl();
//		Order or = new Order();
//		or.setId(1);
//		if(orBiz.delete(or)){
//			System.out.println("删除订单成功");
//		}
//	}
//	@Test
//	//更新订单测试
//	public void testorBizUpdate() {
//		OrderBizImpl orBiz = new OrderBizImpl();
//		Order or = new Order();
//		or.setId(1);
//		if(orBiz.update(or)){
//			System.out.println("更新订单成功");
//		}
//	}
//	@Test
//	//查询用户订单测试
//	public void testorBizFindByUser() {
//		OrderBizImpl orBiz = new OrderBizImpl();
//		int id =1;
//		System.out.println("查询用户订单(有内容则ok):"+orBiz.findOrderByUser(id).get(0).getDayPrice());
//	}
//	@Test
//	//查询房屋订单测试
//	public void testorBizFindByHouse() {
//		OrderBizImpl orBiz = new OrderBizImpl();
//		int id =1;
//		System.out.println("查询房屋订单(有内容则ok):"+orBiz.findOrderByOwner(id).get(0).getDayPrice());
//	}
//	
//	//房主回复测试
//	@Test
//	//增加回复测试
//	public void testreBizAdd() {
//		ReplyBizImpl reBiz = new ReplyBizImpl();
//		Reply re = new Reply();
//		re.setContent("hello");
//		if(reBiz.add(re)){
//			System.out.println("增加回复成功");
//		}
//	}
//	@Test
//	//删除回复测试
//	public void testreBizDelete() {
//		ReplyBizImpl reBiz = new ReplyBizImpl();
//		Reply re = new Reply();
//		re.setId(1);
//		if(reBiz.delete(re)){
//			System.out.println("删除回复成功");
//		}
//	}
//	@Test
//	//查询回复测试
//	public void testreBizFind() {
//		ReplyBizImpl reBiz = new ReplyBizImpl();
//		int id =1;
//		System.out.println("查询回复结果（有内容则ok）："+reBiz.findReplyByComment(id).getContent());
//	}
//	
//	//用户管理测试
//	@Test
//	//增加用户测试
//	public void testusBizAdd() {
//		UserBizImpl usBiz = new UserBizImpl();
//		User us = new User();
//		us.setUsername("tan");
//		if(usBiz.add(us)){
//			System.out.println("增加用户成功");
//		}
//	}
//	@Test
//	//删除用户测试
//	public void testusBizDelete() {
//		UserBizImpl usBiz = new UserBizImpl();
//		User us = new User();
//		us.setId(1);
//		if(usBiz.delete(us)){
//			System.out.println("删除用户成功");
//		}
//	}
//	@Test
//	//修改用户测试
//	public void testusBizUpdate() {
//		UserBizImpl usBiz = new UserBizImpl();
//		User us = new User();
//		us.setId(2);
//		us.setPassword("123456");
//		if(usBiz.update(us)){
//			System.out.println("修改用户成功");
//		}
//	}
//	@Test
//	//验证用户测试
//	public void testusBizVerify() {
//		UserBizImpl usBiz = new UserBizImpl();
//		if(usBiz.verify("tan","123456")==null){
//			System.out.println("验证用户成功：用户密码错误");
//		}
//		else{
//			System.out.println("验证用户成功：欢迎你tan");
//		}
//	}
//	@Test
//	//查询用户测试
//	public void testusBizFindUser() {
//		UserBizImpl usBiz = new UserBizImpl();
//		User us = new User();
//		us.setId(1);
//		if(usBiz.findUser(us.getId())==null){
//			System.out.println("查询用户成功：用户不存在");
//		}
//		else{
//			System.out.println("查询用户成功：欢迎你"+usBiz.findUser(us.getId()).getUsername());
//		}
//	}
//	@Test
//	//以name查询用户测试
//	public void testusBizFindUserByName() {
//		UserBizImpl usBiz = new UserBizImpl();
//		if(usBiz.findUserByName("tan")==null){
//			System.out.println("查询用户成功：用户不存在");
//		}
//		else{
//			System.out.println("查询用户成功：欢迎你tan");
//		}
//	}
//	@Test
//	//查询多用户测试
//	public void testusBizFindUsers() {
//		UserBizImpl usBiz = new UserBizImpl();
//		int page = 1;
//		System.out.println("查询多用户（有内容则ok）："+usBiz.findUsers(page).get(0).getUsername());
//	}

}
