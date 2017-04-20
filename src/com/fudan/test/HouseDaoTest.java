package com.fudan.test;

import java.util.List;

import org.junit.Test;

import com.fudan.dao.IHouseDao;
import com.fudan.dao.impl.HouseDaoImpl;
import com.fudan.entity.House;
import com.fudan.util.DateUtil;
import com.fudan.util.SearchUtil;

public class HouseDaoTest {
	// 测试HouseDao，完成于2015/07/31
	
	@Test  // 测试新增
	public void testAdd() {
		House h = new House();
		h.setUserId(1);
		h.setName("测试房源3");
		h.setBill(House.HAS_BILL);
		h.setRentType(House.RENT_HOUSE);
		h.setKind(House.INN);
		h.setArea(50.64);
		h.setGuestNum(5);
		h.setBedNum(3);
		h.setBedroomNum(1);
		h.setRoomNum(5);
		h.setBedType(House.DOUBLE_BED);
		h.setToiletNum(1);
		h.setRoomDesc("欢迎入住");
		h.setUseRule("禁止吸烟");
		h.setFacility("泳池");
		h.setAddress("xx路xx号");
		h.setMinDay(1);
		h.setMaxDay(7);
		h.setRefundDay(2);
		h.setPayRule("paypal");
		h.setDayPrice(120.55);
		IHouseDao hd = new HouseDaoImpl();
		hd.add(h);
	}
	
	@Test  // 测试根据id查找
	public void testFindHouse() {
		IHouseDao hd = new HouseDaoImpl();
		House h = hd.findHouse(1);
		System.out.println(h.getName() + ":" + h.getPicNum());
	}
	
	@Test  // 测试删除
	public void testDelete() {
		IHouseDao hd = new HouseDaoImpl();
		House h = hd.findHouse(1);
		hd.delete(h);
	}
	
	@Test  // 测试修改
	public void testUpdate() {
		IHouseDao hd = new HouseDaoImpl();
		House h = hd.findHouse(1);
		h.setFacility("修改后的设施");
		hd.update(h);
	}
	
	@Test  // 测试搜索
	public void testSearchHouses() {
		SearchUtil su = new SearchUtil();
		su.setCheckinDate(new DateUtil("2015-07-20"));
		su.setCheckoutDate(new DateUtil("2015-07-22"));
		su.setMinGuestNum(2);
		su.setRentType(House.RENT_HOUSE);
		su.setMaxPrice(500);
		su.setMinArea(0);
		su.setBedNum(1);
		su.setRoomNum(1);
		su.setBedroomNum(1);
		su.setToiletNum(1);
		su.setAddress("");
		IHouseDao hd = new HouseDaoImpl();
		List<House> hs = hd.searchHouses(su);
		for (int i = 0; i < hs.size(); i++) {
			System.out.println(hs.get(i).getName());
		}
	}

	@Test  // 测试得到首页图片路径
	public void testGetPictures() {
		IHouseDao hd = new HouseDaoImpl();
		List<String> ps = hd.getPictures(5);
		for (int i = 0; i < ps.size(); i++) {
			System.out.println(ps.get(i));
		}
	}
	
	@Test  // 测试得到最近房源
	public void testFindLatestHouses() {
		IHouseDao hd = new HouseDaoImpl();
		List<House> hs = hd.findLatestHouses(1);
		for (int i = 0; i < hs.size(); i++) {
			System.out.println(hs.get(i).getName());
		}
	}
	
	@Test  // 测试得到所有房源
	public void testFindHouses() {
		IHouseDao hd = new HouseDaoImpl();
		List<House> hs = hd.findHouses(1);
		for (int i = 0; i < hs.size(); i++) {
			System.out.println(hs.get(i).getName());
		}
	}
	
	@Test  // 测试按用户id得到房源
	public void testFindHousesByUserId() {
		IHouseDao hd = new HouseDaoImpl();
		List<House> hs = hd.findHousesByUserId(1, 1);
		for (int i = 0; i < hs.size(); i++) {
			System.out.println(hs.get(i).getName());
		}
	}
	
	@Test  // 测试得到页数
	public void testMaxPageOfHouses() {
		IHouseDao hd = new HouseDaoImpl();
		System.out.println(hd.maxPageOfHouses());	
	}
	
	@Test  // 测试得到页数根据用户id
	public void testMaxPageOfHousesByUserId() {
		IHouseDao hd = new HouseDaoImpl();
		System.out.println(hd.maxPageOfHousesByUserId(1));	
	}
}
