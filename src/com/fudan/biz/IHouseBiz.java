package com.fudan.biz;

import java.util.List;

import com.fudan.entity.House;
import com.fudan.util.SearchUtil;

public interface IHouseBiz {
	public int add(House h);  // 新增房源2015/07/17
	public boolean delete(int id);  // 删除房源2015/07/17
	public boolean update(House h);  // 更新房源2015/07/17
	public House findHouse(int id);  // 得到一个房源2015/07/17
	public List<House> searchHouses(SearchUtil scondition);  // 得到符合条件的房屋2015/07/21
	List<House> findLatestHouses(int num);//得到首页展示的房屋2015/07/20
	List<House> findHouses(int page);//得到管理员审核的房屋2015/07/20
	public List<House> findHousesByUserId(int id,int page);  // 得到某用户发布的房屋2015/07/20
	public List<String> getPictures(int num);  // 按照时间得到房源的大图，用于首页展示2015/07/20
	public int maxPageOfHouses();   // 得到所有房屋的最大页码
	public int maxPageOfHousesByUserId(int userId);   // 得到user id查找房屋的最大页码
	public boolean updateState(House h, int state);   // 更新房屋状态
	public int maxPageOfHousesInSearch(List<House> totalHoList);
	public List<House> dividePageForSearch(List<House> totalHoList, int page);

}
