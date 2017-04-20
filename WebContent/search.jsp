<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索房源</title>
<!-- main JS libs -->
<script src="js/libs/modernizr.min.js"></script>
<script src="js/libs/jquery-1.10.0.js"></script>
<script src="js/libs/jquery-ui.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>

<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="css/style.css" media="screen" rel="stylesheet">
<link href="css/head.css" media="screen" rel="stylesheet">
<link href="css/header-popmenu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/search.css">

<!-- scripts -->
<script type="text/javascript" src="js/general.js"></script>
<script type="text/javascript" src="js/header-popmenu.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script type="text/javascript" src="js/search.js"></script>

<!-- Include all needed stylesheets and scripts here -->
</head>
<body>
	<div class="body_wrap">
		<div class="container">

			<div class="content " role="main">
				<s:if test="#session.user==null">
					<%@ include file="header.jsp"%>
				</s:if>
				<s:else>
					<%@ include file="afterLogin.jsp"%>
				</s:else>
				<!--/ row -->
				<div class="row">
					<div class="col-sm-12">
						<div class="boxed-yellow" id="search_option">
							<form id="searchForm" method="post"
								action="HouseAction!searchHouses.action">
								<input type="hidden" value="<s:property value='checkinDate'/>" id="saveCheckin">
								<input type="hidden" value="<s:property value='checkoutDate'/>" id="saveCheckout">
								<input type="hidden" value="<s:property value='page'/> " name="page" id="desPage">					
								<span id="title">房源地址 <textarea cols="27" rows="2"
										id="address" name="su.address" placeholder="您想去哪？"><s:property
											value="su.address" /></textarea>
								</span> <br> <span><span class="show_time">入住时间</span><input
									type="text" name="checkinDate" id="checkin"
									class="datepicker"></span> <span><span
									class="show_time" id="outTime">退房时间</span><input type="text"
									name="checkoutDate" id="checkout" class="datepicker">

								</span> <input type="text" id="dayPrice" name="su.maxPrice"
									maxlength="5" placeholder="您希望房屋日租价不高于："
									value="<s:property value='su.maxPrice'/>"> <input
									type="text" id="area" maxlength="5" name="su.minArea"
									value="<s:property value='su.minArea'/>"
									placeholder="您希望房屋面积不小于">
								<div class="field_select">
									最小可住人数 <select class="select_styled" name="su.minGuestNum"
										id="guestNum">
										
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="10" />
											<s:iterator>
												<s:if test="su.minGuestNum==current-1">
													<option selected="selected"
														value="<s:property value="su.minGuestNum"/>"><s:property
															value="su.minGuestNum" />人
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />人
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
									</select>
								</div>
								<div class="field_select">
									出租类型 <select class="select_styled" name="su.rentType"
										id="rentType">

										<s:if test="su.rentType==1">
											<option value="1" selected="selected">整租</option>
										</s:if>
										<s:else>
											<option value="1">整租</option>
										</s:else>
										<s:if test="su.rentType==2">
											<option value="2" selected="selected">单间</option>
										</s:if>
										<s:else>
											<option value="2">单间</option>
										</s:else>
										<s:if test="su.rentType==3">
											<option value="3" selected="selected">床位</option>
										</s:if>
										<s:else>
											<option value="3">床位</option>
										</s:else>
									</select>
								</div>

								<div class="field_select">
									最小房间数 <select class="select_styled" name="su.roomNum"
										id="roomNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="10" />
											<s:iterator>
												<s:if test="su.roomNum==current-1">
													<option selected="selected"
														value="<s:property value="su.roomNum"/>"><s:property
															value="su.roomNum" />间
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
									</select>
								</div>
								<div class="field_select">
									最小卧室数 <select class="select_styled" name="su.bedroomNum"
										id="bedroomNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="6" />
											<s:iterator>
												<s:if test="su.bedroomNum==current-1">
													<option selected="selected"
														value="<s:property value="su.bedroomNum"/>"><s:property
															value="su.bedroomNum" />间
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
									</select>
								</div>
								<div class="field_select">
									最小卫生间数 <select class="select_styled" name="su.toiletNum"
										id="toiletNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="4" />
											<s:iterator>
												<s:if test="su.toiletNum==current-1">
													<option selected="selected"
														value="<s:property value="su.toiletNum"/>"><s:property
															value="su.toiletNum" />间
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
													</option>
												</s:else>
											</s:iterator>
										</s:bean>

									</select>
								</div>
								<div class="field_select">
									最小床位数 <select class="select_styled" name="su.bedNum"
										id="bedNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="8" />
											<s:iterator>
												<s:if test="su.bedNum==current-1">
													<option selected="selected"
														value="<s:property value="su.bedNum"/>"><s:property
															value="su.bedNum" />
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />
													</option>
												</s:else>
											</s:iterator>
										</s:bean>

									</select>
								</div>
								<div class="field_select">
									排序 <select class="select_styled" name="su.orderBy" id="orderBy">
										
										<s:if test="su.orderBy==1">
											<option value="1" selected="selected">按容纳人数</option>
										</s:if>
										<s:else>
											<option value="1">按容纳人数</option>
										</s:else>
										<s:if test="su.orderBy==2">
											<option value="2" selected="selected">按面积</option>
										</s:if>
										<s:else>
											<option value="2">按面积</option>
										</s:else>
										<s:if test="su.orderBy==0">
											<option value="0" selected="selected">按价格</option>
										</s:if>
										<s:else>
											<option value="0">按价格</option>
										</s:else>
									</select>
								</div>
								<div class="field_select">
									排序 <select class="select_styled" name="su.sortBy" id="orderBy">
										<s:if test="su.sortBy==1">
											<option value="1" selected="selected">降序</option>
										</s:if>
										<s:else>
											<option value="1">降序</option>
										</s:else>
										<s:if test="su.sortBy==2">
											<option value="0" selected="selected">升序</option>
										</s:if>
										<s:else>
											<option value="0">升序</option>
										</s:else>
									</select>
								</div>
								<br> <a onclick="$(this).parent().submit()" id="search"
									class="btn btn-blue but"><span>搜索</span></a>
							</form>
						</div>
					</div>
				</div>


<div class="row">
					<div class="col-sm-12">
						<div class="tf_pagination style3 turn_over">
							<div class="inner">
								<s:if test="page!=1">
									<a class="page_prev" onclick="prev()"><span>‹</span></a>
								</s:if>
								<s:if test="maxPage<11">
									<s:bean name="org.apache.struts2.util.Counter" id="counter">
										<s:param name="first" value="1" />
										<s:param name="last" value="maxPage" />
										<s:iterator>
											<s:if test="page==current-1">
												<span class="page-numbers page_current"><s:property
														value="current-1" /></span>
											</s:if>
											<s:else>
												<span class="page-numbers"><s:property
														value="current-1" /></span>
											</s:else>
										</s:iterator>
									</s:bean>
								</s:if>
								<s:else>
									<s:if test="maxPage-page<10">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="maxPage-9" />
											<s:param name="last" value="maxPage" />
											<s:iterator>
												<s:if test="page==current-1">
													<span class="page-numbers page_current"><s:property
															value="current-1" /></span>
												</s:if>
												<s:else>
													<span class="page-numbers"><s:property
															value="current-1" /></span>
												</s:else>
											</s:iterator>
										</s:bean>
									</s:if>
									<s:else>
										<span class="page-numbers page_current"><s:property
												value="page" /></span>
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="page+1" />
											<s:param name="last" value="page+9" />
											<s:iterator>
												<span class="page-numbers"><s:property
														value="current-1" /></span>
											</s:iterator>
										</s:bean>
									</s:else>
								</s:else>
							

								<s:if test="page == maxPage">
									
								</s:if>
								<s:else>
								<a class="page_next" onclick="next()"><span>›</span></a>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				
				
				
				
				<div class="row">
					<s:iterator value="hoList" status="index">
						<div class="col-sm-6">
							<div class="boxed-green show_house">
								<div class="show_pic">
									<a href="HouseAction!toHouseDetail.action?houseId=<s:property value="id"/>">
										<img src="upload/h0_${id}.png" alt="图片挂了" width="100%" height="100%"></img>
									</a>
								</div>
								<a href="HouseAction!toHouseDetail.action?houseId=<s:property value="id"/>"><s:property value="name" /></a>
								<span class="f_right"><s:property value="dayPrice" />￥</span>
							</div>
						</div>
					</s:iterator>
				</div>







				<%@ include file="footer.jsp"%>
			</div>
		</div>

	</div>

</body>
</html>