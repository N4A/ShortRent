<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title></title>
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
<link href="css/newRoom.css" rel="stylesheet" type="text/css" />

<!-- scripts -->
<script src="js/general.js"></script>
<script type="text/javascript" src="js/header-popmenu.js"></script>
<script type="text/javascript" src="js/header.js"></script>
<script type="text/javascript" src="js/editRoom.js"></script>

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
				<form id="editRoomForm" method="post">
					<input type="hidden" id="houseIdFE" name="ho.id" value=<s:property value="ho.id"/>>
					<div class="row">
						<div class="col-sm-4 ">

							<div class="form-wrapper boxed-green">
								<span><input type="text" id="name" name="ho.name"
									value="<s:property value="ho.name"/>" placeholder="房屋名称(必填)">
									<div id="showMessage1">&nbsp;</div></span> <span> <input
									type="text" id="dayPrice" name="dayPrice"
									placeholder="日租价(必填)" value=<s:property value="ho.dayPrice"/>>
									<div id="showMessage2">&nbsp;</div>
								</span> <span> <textarea cols="27" rows="2" id="address"
										 name="ho.address"
										placeholder="详细地址(必填)"><s:property value="ho.address"/></textarea>
									<div id="showMessage3">&nbsp;</div>
								</span>
								<div class="field_select">
									最大可住人数 <select class="select_styled" name="ho.guestNum"
										id="guestNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="10" />
											<s:iterator>
												<s:if test="ho.guestNum==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />人
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
									房间数 <select class="select_styled" name="ho.roomNum"
										id="roomNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="10" />
											<s:iterator>
												<s:if test="ho.roomNum==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
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
								<span> <input type="text" id="area" name="area"
									placeholder="总面积" value="<s:property value="ho.area"/>">
									<div id="showMessage4">&nbsp;</div>
								</span>
								<div class="input_styled inlinelist">
									<div class="rowRadio">

										<s:if test="ho.rentType==1">
											<label><input checked="checked" name="ho.rentType"
												type="radio" value="1" />整租 </label>
										</s:if>
										<s:else>
											<label><input name="ho.rentType" type="radio"
												value="1" />整租 </label>
										</s:else>

									</div>
									<div class="rowRadio">
										<s:if test="ho.rentType==2">
											<label><input checked="checked" name="ho.rentType"
												type="radio" value="2" />单间 </label>
										</s:if>
										<s:else>
											<label><input name="ho.rentType" type="radio"
												value="2" />单间 </label>
										</s:else>

									</div>
									<div class="rowRadio">
										<s:if test="ho.rentType==3">
											<label><input checked="checked" name="ho.rentType"
												type="radio" value="3" />床位 </label>
										</s:if>
										<s:else>
											<label><input name="ho.rentType" type="radio"
												value="3" />床位 </label>
										</s:else>
									</div>
								</div>
<%-- 					于他处实现			<a href="#" class="btn btn-yellow but" id="upload"><span>上传图片</span></a> --%>
							</div>

						</div>
						<div class="col-sm-4">
							<div class="form-wrapper boxed-yellow">
								<div class="field_select">
									卧室数 <select class="select_styled" name="ho.bedroomNum"
										id="bedroomNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="6" />
											<s:iterator>
												<s:if test="ho.bedroomNum==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
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
									卫生间数 <select class="select_styled" name="ho.toiletNum"
										id="toiletNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="4" />
											<s:iterator>
												<s:if test="ho.toiletNum==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />间
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
									床位数 <select class="select_styled" name="ho.bedNum" id="bedNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="8" />
											<s:iterator>
												<s:if test="ho.bedNum==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />
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
									全额退款日 <select class="select_styled" name="ho.refundDay"
										id="bedNum">
										<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="15" />
											<s:iterator>
												<s:if test="ho.refundDay==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />日
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />日
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
									</select>
								</div>
								<span> <textarea cols="27" rows="2" id="useRule" 
										name="ho.useRule" placeholder="使用规则"><s:property value="ho.useRule"/></textarea> <span>&nbsp;</span>
								</span> <span> <textarea cols="27" rows="2" id="roomDesc"
										name="ho.roomDesc" placeholder="房间描述"><s:property value="ho.roomDesc"/></textarea> <span>&nbsp;</span>
								</span>
								<div class="input_styled inlinelist" id="bedType">
									<div class="rowRadio">
										<s:if test="ho.bedType==1">
											<label><input checked="checked" name="ho.bedType"
												type="radio" value="1" />单人床 </label>
										</s:if>
										<s:else>
											<label><input name="ho.bedType" type="radio"
												value="1" />单人床</label>
										</s:else>
									</div>
									<div class="rowRadio">
										<s:if test="ho.bedType==2">
											<label><input checked="checked" name="ho.bedType"
												type="radio" value="2" />双人床 </label>
										</s:if>
										<s:else>
											<label><input name="ho.bedType" type="radio"
												value="2" />双人床</label>
										</s:else>
									</div>
									<div class="rowRadio">
										<s:if test="ho.bedType==3">
											<label><input checked="checked" name="ho.bedType"
												type="radio" value="3" />高低床 </label>
										</s:if>
										<s:else>
											<label><input name="ho.bedType" type="radio"
												value="3" />高低床</label>
										</s:else>
									</div>
								</div>

								<div class="input_styled inlinelist" id="kind">
									<div class="rowRadio">
										<s:if test="ho.kind==1">
											<label><input checked="checked" name="ho.kind"
												type="radio" value="1" />酒店</label>
										</s:if>
										<s:else>
											<label><input name="ho.kind" type="radio"
												value="1" />酒店</label>
										</s:else>
									</div>
									<div class="rowRadio">
										<s:if test="ho.kind==2">
											<label><input checked="checked" name="ho.kind"
												type="radio" value="2" />旅店</label>
										</s:if>
										<s:else>
											<label><input name="ho.kind" type="radio"
												value="2" />旅店</label>
										</s:else>
									</div>
									<div class="rowRadio">
										<s:if test="ho.kind==3">
											<label><input checked="checked" name="ho.kind"
												type="radio" value="3" />客栈</label>
										</s:if>
										<s:else>
											<label><input name="ho.kind" type="radio"
												value="3" />客栈</label>
										</s:else>
									</div>
									<div class="rowRadio">
										<s:if test="ho.kind==4">
											<label><input checked="checked" name="ho.kind"
												type="radio" value="4" />民居</label>
										</s:if>
										<s:else>
											<label><input name="ho.kind" type="radio"
												value="4" />民居</label>
										</s:else>
									</div>
								</div>
								<span> <input type="text" id="payRule" name="ho.payRule" value="<s:property value="ho.payRule"/>"
									placeholder="付款规则"> <span>&nbsp;</span>
								</span>
							</div>
						</div>
						<div class="col-sm-4 ">
							<div class="form-wrapper">
								<div class="row form-area boxed-red">
									<span> <textarea cols="27" rows="2" id="facility"
											name="ho.facility" placeholder="设施描述"><s:property value="ho.facility"/></textarea> <span>&nbsp;</span>
									</span>
									<div class="input_styled inlinelist" id="bill">
										<div class="rowRadio">
											<s:if test="ho.bill==1">
											<label><input checked="checked" name="ho.bill"
												type="radio" value="1" />提供发票</label>
										</s:if>
										<s:else>
											<label><input name="ho.bill" type="radio"
												value="1" />提供发票</label>
										</s:else>
										</div>
										<div class="rowRadio">
											<s:if test="ho.bill==2">
											<label><input checked="checked" name="ho.bill"
												type="radio" value="2" />不提供发票</label>
										</s:if>
										<s:else>
											<label><input name="ho.bill" type="radio"
												value="2" />不提供发票</label>
										</s:else>
										</div>
									</div>


									<div class="field_select">
										最少天数 <select class="select_styled" name="ho.minDay"
											id="minDay">
											<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="15" />
											<s:iterator>
												<s:if test="ho.minDay==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />天
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />天
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
											
										</select>
									</div>
									<div class="field_select">
										最多天数 <select class="select_styled" name="ho.maxDay"
											id="maxDay">
											<s:bean name="org.apache.struts2.util.Counter" id="counter">
											<s:param name="first" value="1" />
											<s:param name="last" value="15" />
											<s:iterator>
												<s:if test="ho.maxDay==current-1">
													<option selected="selected"
														value="<s:property value="current-1"/>"><s:property
															value="current-1" />天
													</option>
												</s:if>
												<s:else>
													<option value="<s:property value="current-1"/>"><s:property
															value="current-1" />天
													</option>
												</s:else>
											</s:iterator>
										</s:bean>
										</select>
									</div>
								</div>
								<div class="row button-area boxed-turquoise">
									<input type="text" id="verification" name="verification"
										placeholder="请输入验证码"> <a href="javascript:;"
										id="verify"
										onclick="document.getElementById('ver-pic').src = 'verification.action?time='+Math.random();">
										<img id="ver-pic" width="100px" height="40px"
										src="verification.action" alt="验证码挂了" />
									</a><br>
									<div id="showMessage5">&nbsp;</div>s
									<a onclick="editRoom()" class="btn btn-blue but"><span>提交</span></a>
									<span class="btn btn-blue but"><input type="reset"
										name="reset-button" /></span>
								</div>
							</div>
						</div>
					</div>

				</form>
				<%@ include file="footer.jsp"%>
			</div>

			<!--/ content -->
		</div>
		<!--/ container -->
	</div>

</body>
</html>