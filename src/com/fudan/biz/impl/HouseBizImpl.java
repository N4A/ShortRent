package com.fudan.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.fudan.biz.IHouseBiz;
import com.fudan.dao.IHouseDao;
import com.fudan.dao.IOrderDao;
import com.fudan.dao.impl.HouseDaoImpl;
import com.fudan.dao.impl.OrderDaoImpl;
import com.fudan.entity.House;
import com.fudan.entity.Order;
import com.fudan.util.SearchUtil;

public class HouseBizImpl implements IHouseBiz {
	final private static int NUM_PER_PAGE_IN_SEARCH = 16;
	IHouseDao dao = new HouseDaoImpl();
	
	//增加房屋，实现用户发布房屋功能2015/07/20
	@Override
	public int add(House h) {
		// TODO Auto-generated method stub
		return dao.add(h);
	}
	
	/**
	 * 删除房屋
	 * 2015/08/03
	 */
	public boolean delete(int id) {
		return dao.delete(dao.findHouse(id));
	}
	
	//更新房屋2015/07/20
	@Override
	public boolean update(House h) {
		// TODO Auto-generated method stub
		return dao.update(h);
	}
	
	/**
	 * 更新房屋状态
	 * 2015/08/03
	 */
	public boolean updateState(House h, int state) {
		h.setState(state);
		return dao.update(h);
	}
	
	//根据Id得到某一房屋2015/07/20
	@Override
	public House findHouse(int id) {
		// TODO Auto-generated method stub
		return dao.findHouse(id);
	}
	
	/**
	 * 搜索房屋
	 * 输入：搜索条件，页码
	 * 输出：List<House>
	 * 2015/07/21
	 * 2015/07/22：增加筛选时间冲突的功能
	 * 2015/07/27：增加分页功能
	 * 2015/07/31: 分页功能与原方法分离，这样可以通过hs.size()得到总数
	 */
	public List<House> searchHouses(SearchUtil scondition) {
		// 得到所有符合要求的房屋，不筛选选择的时间是否存在冲突
		List<House> hs = dao.searchHouses(scondition);
		
		if (hs != null) {  // 存在符合要求的房屋
			IOrderDao od = new OrderDaoImpl();
			Order o = new Order();  // 设定不变的条件：两个时间
			o.setCheckinDate(scondition.getCheckinDate());
			o.setCheckoutDate(scondition.getCheckoutDate());
			for (int i = hs.size() - 1; i >=0; i--) {
				// 倒序迭代此列表，准备删除其中时间冲突的房源，因为List的删除导致下标会前移，因此必须从后往前迭代
				o.setHouseId(hs.get(i).getId());
				if (od.findConflictOrder(o)) {  // 存在时间冲突
					hs.remove(i);   // 删除这个房屋
				}
			}
			return (hs.size() == 0) ? null : hs;
		} else {  // 不存在符合要求的房屋
			return null;
		}
	}
	
	/**
	 * 得到搜索结果的最大页数
	 * 输入：List<House>：搜索结果
	 * 输出：int：最大页数
	 * 2015/07/31
	 */
	public int maxPageOfHousesInSearch(List<House> hs) {
		return hs.size() / NUM_PER_PAGE_IN_SEARCH + ((hs.size() % NUM_PER_PAGE_IN_SEARCH == 0) ? 0 : 1); 
	}
	
	/**
	 * 得到某一页的搜索结果
	 * 输入：List<House>：搜索结果，int：所需页数
	 * 输出：List<House>
	 * 2015/07/31
	 * 2015/08/04 修复下标越界错误
	 */
	public List<House> dividePageForSearch(List<House> hs, int page) {
		List<House> hsInPage = new ArrayList<House>();
		// 开始分页
		if ((page - 1) * NUM_PER_PAGE_IN_SEARCH + 1 <= hs.size()) {  // 该页有内容
			for (int i = (page - 1) * NUM_PER_PAGE_IN_SEARCH; i < page * NUM_PER_PAGE_IN_SEARCH; i++) {
				if (i < hs.size()) {  // 有可能一页没有充满，那么有可能越界
					hsInPage.add(hs.get(i));   // 取得该页的内容
				}
			}
		}
		return (hsInPage.size() == 0) ? null : hsInPage;  // 如果长度不为0返回之，否则返回null
	}
	
	//得到首页展示的房屋2015/07/20
	@Override
	public List<House> findLatestHouses(int num) {
		// TODO Auto-generated method stub
		return dao.findLatestHouses(num);
	}
	
	//得到首页展示的图片2015/07/20
	@Override
	public List<String> getPictures(int num) {
		// TODO Auto-generated method stub
		return dao.getPictures(num);
	}
	
	/**
	 * 得到某用户发布的房屋，并分页显示，用于房屋管理
	 * 输入：用户id，页数page
	 * 输出：List<House>
	 * 2015/07/21
	 * 2015/07/31：增加状态int翻译为显示字符串的功能
	 * 2015/08/03:检测空指针
	 */
	public List<House> findHousesByUserId(int id, int page) {
		List<House> hs = dao.findHousesByUserId(id, page);
		if (hs == null) return null;
		String state_str = "undefined";
		// 根据int类型的状态字段翻译对应用于显示的字符串
		for (int i = 0; i < hs.size(); i++) {
			switch (hs.get(i).getState()) {
			case 0:
				state_str = "待审核";
				break;
			case 1:
				state_str = "已发布";
				break;
			case 2:
				state_str = "已拒绝";
				break;
			}
			hs.get(i).setState_str(state_str);
		}
		return hs;
	}

	/**
	 * 得到所有房屋，并分页显示，用于房屋审核
	 * 输入：页数page
	 * 输出：List<House>
	 * 2015/08/02
	 * 2015/08/03:检测空指针
	 */
	public List<House> findHouses(int page) {
		List<House> hs = dao.findHouses(page);
		if (hs == null) return null;
		String state_str = "undefined";
		// 根据int类型的状态字段翻译对应用于显示的字符串
		for (int i = 0; i < hs.size(); i++) {
			switch (hs.get(i).getState()) {
			case 0:
				state_str = "待审核";
				break;
			case 1:
				state_str = "已发布";
				break;
			case 2:
				state_str = "已拒绝";
				break;
			}
			hs.get(i).setState_str(state_str);
		}
		return hs;
	}
	
	@Override
	public int maxPageOfHouses() {
		return dao.maxPageOfHouses();
	}

	@Override
	public int maxPageOfHousesByUserId(int userId) {
		return dao.maxPageOfHousesByUserId(userId);
	}

}
