package com.fudan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fudan.biz.ICommentBiz;
import com.fudan.biz.IHouseBiz;
import com.fudan.biz.IReplyBiz;
import com.fudan.biz.IUserBiz;
import com.fudan.biz.impl.*;
import com.fudan.entity.*;
import com.fudan.util.DateUtil;
import com.fudan.util.SearchUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HouseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private House ho = new House();
	private List<Comment> co = new ArrayList<Comment>();
	private List<Reply> re = new ArrayList<Reply>();
	private List<House> hos = new ArrayList<House>();
	private User u;
	private int houseId;
	
	private String address;//搜索功能的房屋地址
	private String dayPrice;//用于房屋价格检查
	private String area;
	String[] result1 = {"","","","",""};//用于返回发布房屋的检测结果2015/07/24
	private Map<String,Object> dataMap = new HashMap<String, Object>();;  //返回房屋是否成功发布
	private String verification;//验证码
	private String[] pics;
	boolean toWarn =false;//用于回显发布房屋是否正确
	private SearchUtil su = new SearchUtil();//房屋搜索条件2015/07/21
	private String checkinDate;//structs 不能自动转化我们自己设计的date类型，故挑出单独处理2015-08-01
	private String checkoutDate;//structs 不能自动转化我们自己设计的date类型，故挑出单独处理2015-08-01
	private int page;//房屋列表分页，当前页码数
	private int maxPage;//最大页码数
	private List<House> totalHoList;//所有的搜索结果
	private List<House> hoList;//返回的搜索结果2015/07/21
	IHouseBiz hoBiz = new HouseBizImpl();
	ICommentBiz coBiz = new CommentBizImpl();
	IReplyBiz reBiz = new ReplyBizImpl();
	IUserBiz uBiz = new UserBizImpl();
	
	private int userId;  // 用户id，用于后台用户管理
	private int id;
	//发布房源,2015/7/24
	public String build(){
		System.out.println(ho.getName());
		String num = (String)ActionContext.getContext().getSession().get("num");//验证码
		if(ho.getName().equals("")) {//判断房屋名称
			result1[0]="房屋名称不能为空";
			//System.out.println("房屋名称不能为空");
			toWarn=true;
		}
		if(dayPrice.equals("")) {//判断房屋价格
			result1[1]="价格不能为空";
			//System.out.println("价格不能为空");
			toWarn=true;
		}
		else {//判断房屋价格
			try {
				ho.setDayPrice(Double.parseDouble(dayPrice));
			} catch(Exception e) {
				result1[1]="价格应为小数或整数";
				//System.out.println("价格应为小数或整数");
				toWarn=true;
			}
		}
		if(ho.getAddress().equals("")) {//判断地址
			result1[2]="地址不能为空";
			toWarn=true;
		}
		if(area.equals("")) {//判断房屋面积
			result1[3]="房屋面积不能为空";
			toWarn=true;
		}
		else {//判断房屋面积
			try {
				ho.setArea(Double.parseDouble(area));
			} catch(Exception e){
				result1[3]="价格应为小数或整数";
				toWarn=true;
			}
		}
		if(!num.equals(verification)) {
			result1[4] = "验证码错误";
			toWarn=true;
		}	
		if (toWarn) {//创建房屋有问题
			dataMap.put("success","not");
			dataMap.put("result0",result1[0]);
			dataMap.put("result1",result1[1]);
			dataMap.put("result2",result1[2]);
			dataMap.put("result3",result1[3]);
			dataMap.put("result4",result1[4]);
			return "error";
		} else {
			ho.setUserId(((User) (ActionContext.getContext().getSession().get("user"))).getId());//将user对象放入session中，2015/07/22);
			houseId = hoBiz.add(getHo());
			if(houseId != 0) { 
				//添加房屋成功
				dataMap.put("success","yes");
				//houseId=hoBiz.findLatestHouses(1).get(0).getId();
				System.out.println(houseId);
				dataMap.put("houseId",houseId);//储存房屋id
				return "success";
			} else {				
				dataMap.put("success","not");
				dataMap.put("result0",result1[0]);
				dataMap.put("result1",result1[1]);
				dataMap.put("result2",result1[2]);
				dataMap.put("result3",result1[3]);
				dataMap.put("result4",result1[4]);
				return "error";
			}
		}
	}
	
	//到显示房屋的详细界面
	public String toHouseDetail() {
		//需要评论，房子信息，回复，该房主的信息，以及房主的其他房屋
		ho=hoBiz.findHouse(houseId);
		ho.setJson_str(ho.toJSONString());  /** 2015/08/04 得到时生成jsonstr用于地图展示 */
		co = coBiz.findCommentByHouse(houseId);
		//System.out.println(co);
		if (co!=null) {
			for (int i=0;i<co.size();i++){
				Reply temp = reBiz.findReplyByComment(co.get(i).getId());
				if(temp==null)
					re.add(null);
				else {
					re.add(temp);
				}
			}
		}
		if (ho!=null) {
			u = uBiz.findUser(ho.getUserId());
			hos = hoBiz.findHousesByUserId(ho.getUserId(),1);
			if (ho.getPicNum() > 0) {//初始化图像数组
				pics=new String[ho.getPicNum()-1];
				for(int i=0;i<pics.length;i++){
					pics[i]="h"+(i+1)+"_"+houseId+".png";
				}
			}
			return "toHouseDetail";
		} else {
			return "error";
		}
	}

	//载入修改房屋页面2015/7/20
	public String updateBegin() {
		setHo(hoBiz.findHouse(getHouseId()));
		return "edit";
	}
	//修改房屋2015/7/20 2015/8/1修改，增加用户输入检查
	public String update() {
		String num = (String)ActionContext.getContext().getSession().get("num");//验证码
		if (ho.getName().equals("")) {//判断房屋名称
			result1[0]="房屋名称不能为空";
			toWarn=true;
		}
		if(dayPrice.equals("")){//判断房屋价格
			result1[1]="价格不能为空";
			toWarn=true;
		} else {//判断房屋价格
			try {
				ho.setDayPrice(Double.parseDouble(dayPrice));
			} catch (Exception e) {
				result1[1]="价格应为小数或整数";
				toWarn=true;
			}
		}
		if (ho.getAddress().equals("")) {//判断地址
			result1[2]="地址不能为空";
			toWarn=true;
		}
		if(area.equals("")){//判断房屋面积
			result1[3]="房屋面积不能为空";
			toWarn=true;
		} else {//判断房屋面积
			try {
				ho.setArea(Double.parseDouble(area));
			} catch (Exception e) {
				result1[3]="价格应为小数或整数";
				toWarn=true;
			}
		}
		if(!num.equals(verification)) {
			result1[4] = "验证码错误";
			toWarn=true;
		}	
		if(toWarn){//修改房屋有问题
			dataMap.put("success","not");
			dataMap.put("result0",result1[0]);
			dataMap.put("result1",result1[1]);
			dataMap.put("result2",result1[2]);
			dataMap.put("result3",result1[3]);
			dataMap.put("result4",result1[4]);
			return "error";
		} else {
			ho.setUserId(((User) (ActionContext.getContext().getSession().get("user"))).getId());//将user对象放入session中，2015/07/22);
			if(hoBiz.update(ho)) {
				//修改房屋成功
				dataMap.put("success","yes");
				//houseId=hoBiz.findLatestHouses(1).get(0).getId();
				//dataMap.put("houseId",houseId);//储存房屋id
				return "success";
			} else {								
				return "error";
			}
		}
	}
	//根据id找房屋2015/7/20
	public String findHouse(){
		setHo(hoBiz.findHouse(getHouseId()));
		return "success";
	}
	
	//根据搜索条件搜索房屋2015/07/21 31修改，完善搜索条件
	
	public String searchHouses() {
		//System.out.println("ok");
		DateUtil checkinDateUtil = new DateUtil(checkinDate);
		DateUtil checkoutDateUtil = new DateUtil(checkoutDate);
		//System.out.println(su.getCheckinDate());
		//System.out.println(su.getCheckoutDate());
		if (checkinDateUtil.isInitialized()) {//日期格式正确，否则用默认值2015/08/03
			su.setCheckinDate(checkinDateUtil);
		} else {
			checkinDate=su.getCheckinDate().toString();
		}
		if (checkoutDateUtil.isInitialized()) {//日期格式正确，否则用默认值2015/08/03
			su.setCheckoutDate(checkoutDateUtil);
		} else {
			checkoutDate=su.getCheckoutDate().toString();
		}
		
		
		totalHoList=hoBiz.searchHouses(su);//用搜索条件获取结果08-01
		 SearchUtil t=su.clone();
		 su = t;
		 //System.out.print("su" +su);
		int temp=page;
		if(temp==0)
			temp=1;
		
		if (totalHoList!=null) {
			maxPage=hoBiz.maxPageOfHousesInSearch(totalHoList);
			hoList=hoBiz.dividePageForSearch(totalHoList,temp);
			page=temp;
		} else {
			maxPage = 1;
			hoList=hoBiz.findLatestHouses(10);
			page=temp;
		}
		return "showSearchResult";
	}
	
	/**
	 * 删除房屋
	 * 2015/08/03
	 */
	public String delete(){
		return (hoBiz.delete(ho.getId())) ? "success" : "error";
	}
	
	/**
	 * 通过审核
	 * 2015/08/03
	 */
	public String acceptHouse() {
		House h = hoBiz.findHouse(id);
		return (hoBiz.updateState(h, House.POSTED)) ? "success" : "error";	
	}
	
	/**
	 * 拒绝审核
	 * 2015/08/03
	 */
	public String refuseHouse() {
		House h = hoBiz.findHouse(id);
		return (hoBiz.updateState(h, House.REJECTED)) ? "success" : "error";	
	}
	
    /**
	 * 去房屋管理的页面
	 * 2015/07/31
	 */
	public String toHouseManage() {
		//System.out.println(page); 使用url的 ?page= 传值
		// 从session中读取user的id
		User u = (User) ActionContext.getContext().getSession().get("user");
		int userId = u.getId();
		// 处理page相关
		maxPage = hoBiz.maxPageOfHousesByUserId(userId);
		page = (page > maxPage || page < 1) ? 1 : page;  // 不合法的page，默认第一页
		// 设置主要参数
		hoList = hoBiz.findHousesByUserId(userId, page);
		return "success";
	}
	
    /**
	 * 去房屋审核的页面
	 * 2015/08/02
	 */
	public String toHouseAdmin() {
		//System.out.println(page); 使用url的 ?page= 传值
		User u = (User) ActionContext.getContext().getSession().get("user");
		// 判断权限
		if (u.getState() == 0) {  // 不是管理员
			return "error";
		}
		// 处理page相关
		maxPage = hoBiz.maxPageOfHouses();
		page = (page > maxPage || page < 1) ? 1 : page;  // 不合法的page，默认第一页
		// 设置主要参数
		hoList = hoBiz.findHouses(page);
		return "success";
	}
	//待完成
	//搜索功能，根据地址搜索2015/7/20
	public String findHousesByAddress(){
		return null;
	}
	//搜索功能，根据日期搜索2015/7/20
	public String findHousesByDay(){
		return null;
	}
	//getter and setter methods
	public House getHo() {
		return ho;
	}
	public void setHo(House ho) {
		this.ho = ho;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SearchUtil getSu() {
		return su;
	}
	public void setSu(SearchUtil su) {
		this.su = su;
	}
	public List<House> getHoList() {
		return hoList;
	}
	public void setHoList(List<House> hoList) {
		this.hoList = hoList;
	}
	public String getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Map<String,Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String,Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public List<Comment> getCo() {
		return co;
	}
	
	public void setCo(List<Comment> co) {
		this.co = co;
	}
	
	public List<Reply> getRe() {
		return re;
	}
	
	public void setRe(List<Reply> re) {
		this.re = re;
	}
	
	public List<House> getHos() {
		return hos;
	}
	
	public void setHos(List<House> hos) {
		this.hos = hos;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public List<House> getTotalHoList() {
		return totalHoList;
	}

	public void setTotalHoList(List<House> totalHoList) {
		this.totalHoList = totalHoList;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
