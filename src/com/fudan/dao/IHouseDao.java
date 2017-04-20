package com.fudan.dao;

import java.util.List;

import com.fudan.entity.House;
import com.fudan.util.SearchUtil;

public interface IHouseDao {
	
	public int add(House h);  // 新增房源（返回自增id2015/08/05）
	public boolean delete(House h);  // 删除房源
	public boolean update(House h);  // 更新房源
	public House findHouse(int id);  // 得到一个房源
	public List<House> findLatestHouses(int num);   // 得到最新的房屋（用于首页展示）
	public List<House> findHouses(int page);  // 得到所有房屋并分页显示
	public List<House> findHousesByUserId(int userId, int page);  // 根据user id得到房源
	public int maxPageOfHouses();   // 得到所有房屋的最大页码
	public int maxPageOfHousesByUserId(int userId);   // 得到user id查找房屋的最大页码
	public List<House> searchHouses(SearchUtil scondition);  // 得到符合条件的房屋
	public List<String> getPictures(int num);  // 按照时间得到房源的大图，用于首页展示

}
