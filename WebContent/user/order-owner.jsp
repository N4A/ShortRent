<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>订单管理</title>
    <!-- main JS libs -->
    <script src="../js/libs/modernizr.min.js"></script>
    <script src="../js/libs/jquery-1.10.0.js"></script>
    <script src="../js/libs/jquery-ui.min.js"></script>
    <script src="../js/libs/bootstrap.min.js"></script>

    <!-- Style CSS -->
    <link href="../css/bootstrap.css" media="screen" rel="stylesheet">
    <link href="../css/style.css" media="screen" rel="stylesheet">
    <link href="../css/head.css" media="screen" rel="stylesheet">
    <link href="../css/header-popmenu.css" rel="stylesheet" type="text/css" />
    <link href="../css/l-style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../css/font-awesome.min.css">

    <!-- scripts -->
    <script src="../js/general.js"></script>
    <script type="text/javascript" src="../js/header-popmenu.js"></script>
    <script type="text/javascript" src="../js/header.js"></script>
</head>
<body>
    <div class="body_wrap">
    <div class="container">
    <div class="content " role="main">
     <%@ include file="afterLogin.jsp"%>
    	<!--/ row -->
    	<div class="row">
			<s:if test="#session.user.state==0">
    			<%@ include file="aside.jsp"%>
    		</s:if>
    		<s:else>
    			<%@ include file="aside-admin.jsp"%>
    		</s:else>	
    		<div class="col-sm-10">
    			<div class="l-box l-box-yellow">
    				<table class="l-table">
					<caption>我是房东</caption>
					<tr>
						<th>房屋名</th>
						<th>用户名</th>
						<th>订单号</th>
						<th>入住时间</th>
						<th>退房时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="orList" status="index" >
					<tr>
						<td><s:property value="houseName" /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="orderNum" /></td>
						<td><s:property value="checkinDate" /></td>
						<td><s:property value="checkoutDate" /></td>
						<td><s:property value="stateStr" /></td>
						<td>
						<s:if test="state==0">
							<a href="javascript:;" onclick="accept(<s:property value='id' />)">接受</a>　
							<a href="javascript:;" onclick="refuse(<s:property value='id' />)">拒绝</a>
						</s:if>						
						</td>
					</tr>
					</s:iterator>
					</table>
    			</div>
    		</div>
    	</div>
    <%@ include file="../footer.jsp"%>
    </div>    
    </div><!--/ content -->    
    </div><!--/ container -->
    <script type="text/javascript" src="../js/user/order.js"></script>
</body>
</html>