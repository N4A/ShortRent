<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>房屋详情</title>
<!-- main JS libs -->
<script src="js/libs/modernizr.min.js"></script>
<script src="js/libs/jquery-1.10.0.js"></script>
<script src="js/libs/jquery-ui.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>
<script src="js/libs/jquery-ui.js"></script>
<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="css/style.css" media="screen" rel="stylesheet">
<link href="css/head.css" media="screen" rel="stylesheet">
<link href="css/header-popmenu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/showHouse.css">
<link rel="stylesheet" href="css/popReply.css">

<!-- scripts -->
<script type="text/javascript" src="js/general.js"></script>
<script type="text/javascript" src="js/order.js"></script>
<script type="text/javascript" src="js/header-popmenu.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script type="text/javascript" src="js/reply.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=QdWhpP1sVe0h4iSPMedm4Ri5"></script>
<script type="text/javascript" src="js/bmapfunc.js"></script>


<!-- Include all needed stylesheets and scripts here -->
</head>
<body>
<div id="popback"></div>
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

						<!-- Image Slider -->
						<div class="widget-container widget-gallery boxed">
							<div class="inner">
								<div id="myCarousel" class="carousel slide"
									data-interval="20000">
									<!-- Carousel items -->
									<div class="carousel-inner">
										<div class="active item">
											<a href="upload/h0_${houseId}.png" class="prettyPhoto"
												data-rel="prettyPhoto" title="Show Image"><img
												width="100%" height="100%" src="upload/h0_${houseId}.png" alt="" /></a>
										</div>
										<s:iterator value="pics" status="index">
										<div class="item">
											<a href="upload/<s:property/>" class="prettyPhoto"
												data-rel="prettyPhoto" title="Show Image"><img
												width="100%" height="100%" src="upload/<s:property/>" alt="" /></a>
										</div>
										</s:iterator>
									</div>
									<div class="carousel-controls">
										<!-- Carousel indicators -->
										<ol class="carousel-indicators">
											<li data-target="#myCarousel" data-slide-to="0"
												class="active"></li>
												
												
												
											<s:iterator value="pics" status="index">
										<li data-target="#myCarousel" data-slide-to="#index.index"></li>
										</s:iterator>	
											
											
										</ol>
										<!-- Carousel nav -->
										<a class="carousel-control left" href="#myCarousel"
											data-slide="prev"></a> <a class="carousel-control right"
											href="#myCarousel" data-slide="next"></a>
									</div>
								</div>
							</div>
						</div>
						<!--/ Image Slider -->

					</div>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<div id="house_info">


							<div class="boxed-green" id="ess_info">
								<h4>房屋基本信息</h4>
								<p>
									房屋名称:
									<s:property value="ho.name"></s:property>
								</p>
								<p>
									详细地址:
									<s:property value="ho.address"></s:property>
								</p>
								<p>
									日租价:
									<s:property value="ho.dayPrice"></s:property>
									￥ <span class="f_right"> 总面积: <s:property
											value="ho.area"></s:property>
									</span>
								</p>
								<p>
									最大可住人数:
									<s:property value="ho.guestNum"></s:property>
									人 <span class="f_right"> 房间数: <s:property
											value="ho.roomNum"></s:property>
									</span>
								</p>

								<p>
									出租类型:
									<s:if test="ho.rentType==1">整租</s:if>
									<s:if test="ho.rentType==2">单间</s:if>
									<s:if test="ho.rentType==3">床位</s:if>
									<span class="f_right"> 类型: <s:if test="ho.kind==1">酒店</s:if>
										<s:if test="ho.kind==2">旅店</s:if> <s:if test="ho.kind==3">客栈</s:if>
										<s:if test="ho.kind==4">民居</s:if>
									</span>
								</p>
								<p>
									付款规则:
									<s:property value="ho.payRule"></s:property>
								</p>

								<p>
									设施描述:
									<s:property value="ho.facility"></s:property>
								</p>
							</div>
							<div class="boxed-green" id="extra_info">
								<h4>补充信息</h4>
								<p>
									卧室数 :
									<s:property value="ho.bedroomNum"></s:property>
									<span class="f_right"> 卫生间数: <s:property
											value="ho.toiletNum"></s:property>
									</span>
								</p>
								<p>
									床型:
									<s:if test="ho.bedType==1">单人床</s:if>
									<s:if test="ho.bedType==2">双人床</s:if>
									<s:if test="ho.bedType==3">高低床</s:if>
								</p>
								<p>
									床位数数:
									<s:property value="ho.bedNum"></s:property>
									<span class="f_right"> 全额退款日: <s:property
											value="ho.refundDay"></s:property>
									</span>
								</p>
								<p>
									使用规则:
									<s:property value="ho.useRule"></s:property>
								</p>

								<p>
									房间描述:
									<s:property value="ho.roomDesc"></s:property>
								</p>
								<p>
									是否提供发票:
									<s:if test="ho.bill==0">否</s:if>
									<s:if test="ho.bill==1">是</s:if>
								</p>
								<p>
									最少天数:
									<s:property value="ho.minDay"></s:property>
									天 <span class="f_right"> 最多天数: <s:property
											value="ho.maxDay"></s:property> 天
									</span>
								</p>
							</div>
						</div>

						<div id="house_comment" class="boxed-turquoise">
							<h4>评论区</h4>
							<div id="comment_wrapper">


								<s:iterator value="co" status="index">
									<h5>
										房客&nbsp;
										<s:property value="username"></s:property>
										:
									</h5>
									<div class="comment">
										<p class="comment_content">
											<s:property value="content"></s:property>
										</p>
									</div>
									<s:if test="re.get(#index.index)==null">
										<s:if test="#session.user.id==u.id">
											<a class="btn btn-yellow" href="javascript:void(0)"
												onclick="openReply()"><span>回复</span></a>
												
											<div id="popReply">
												<a href="javascript:closeReply()" class="closePop">x</a>
												<h3>发表回复</h3>
												<form id="replyForm" method="post">
													<input type="hidden" value="<s:property value="#session.user.id" />" name="re.userId">
													<input type="hidden" value="${id}" name="re.commentId" />
													<p class="prompt" id="ReplyErrorMessage">&nbsp;</p>
													<p>
														<label class="inputLabel"> <textarea rows="3"
																cols="30" placeholder="回复:" id="writeReply"
																name="re.content"></textarea>
														</label>
													</p>

													<p>
														<label class="inputLabel"> <a class="blue-color"
															class="inputLabel" onclick="reply()">回复</a>
														</label>
													</p>
												</form>
											</div>

										</s:if>
									</s:if>
									<s:else>
										<h5>
											房东&nbsp;
											<s:property value="u.username"></s:property>
											&nbsp;回复：
										</h5>
										<div class="reply">
											<p class="reply_content">
												<s:property value="re.get(#index.index).content"></s:property>
											</p>
										</div>

									</s:else>
								</s:iterator>
								<p class="comment_content">........</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<s:if test="#session.user!=null">
						<form id="orderForm" method="post">

							<div id="submit_order" class="boxed-yellow">
								<h4>下订单</h4>
								<input type="hidden" name="or.houseId" value="<s:property value="ho.id"/>">
								<input type="hidden" name="houseId" value="<s:property value="ho.id"/>">
								<input type="hidden" name="or.userId" value="<s:property value="#session.user.id"/>">
								<input type="hidden" name="userId" value="<s:property value="#session.user.id"/>">
								<input type="hidden" name="dayPrice" value="<s:property value="ho.dayPrice"/>">                                     
								<span><span class="show_time">入住时间</span><input
									type="text" name="checkinDate" id="checkin"
									class="datepicker"></span> <br> <br> <span><span
									class="show_time">退房时间</span><input type="text"
									name="checkoutDate" id="checkout" class="datepicker"></span>
								<div id="date_info">&nbsp;</div>

								<div id="show_dayPrice">
									单日价格:<span id="dayPrice"><s:property value="ho.dayPrice"></s:property></span>￥
								</div>
								<br> <input type="text" size="12" id="verification" name="verification"
									placeholder="请输入验证码"><a href="javascript:;" id="verify"
									onclick="document.getElementById('ver-pic').src = 'verification.action?time='+Math.random();">
									<img id="ver-pic" src="verification.action" alt="验证码挂了" />
								</a><br> <a onclick="newOrder()" class="btn btn-red but" style="padding:1.5rem 0;"><span>下订单</span></a>
								
							</div>
						</form>
						</s:if>
						<div id="owner_info" class="boxed-yegreen">
							<h4>房东信息</h4>
							<img src="upload/user/u_<s:property value="u.id"></s:property>.png"
								width="80%" height="80%" alt="头像挂了" style="padding:10px;" />
							<p>
								用户名：
								<s:property value="u.username"></s:property>
							</p>
							<p>
								联系电话：
								<s:property value="u.mobile"></s:property>
							</p>
							<p>
								性别：
								<s:if test="u.gender==1">&nbsp;男</s:if>
								<s:else>&nbsp;女</s:else>
							</p>
							<p>
								邮箱：
								<s:if test="u.email==null">暂无</s:if>
								<s:else>
									<s:property value="u.email"></s:property>
								</s:else>
							</p>
						</div>
						<div id="other_house" class="boxed-pale">
							<h4>房东其他房源</h4>

							<s:iterator value="hos" status="index">
								<div>
									<a href="HouseAction!toHouseDetail.action?houseId=<s:property value="id"/>"><img class="other_house" src="upload/h0_${id}.png" width="80%" height="80%" alt="图片挂了" style="padding:10px 0;"></a>
									<p>
										<a
											href="HouseAction!toHouseDetail.action?houseId=<s:property value="id"/>"><s:property
												value="name" /></a><br>
										<s:property value="dayPrice" />
										￥/晚
									</p>
								</div>
							</s:iterator>
							....
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
					<input type="hidden" id="spotJsonStr" value="<s:property value="ho.json_str"></s:property>" />
						<div id="container">
						
						</div>
					</div>
					
				</div>

				<%@ include file="footer.jsp"%>
			</div>
			<!--/ content -->
		</div>
		<!--/ container -->
	</div>
<script type="text/javascript" src="js/bmap.js"></script>
<script type="text/javascript">showSpot(eval('(' + $("#spotJsonStr").val() + ')'))</script>
</body>
</html>