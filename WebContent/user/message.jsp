<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>站内信</title>
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
					<caption>收件箱</caption>
					<tr>
						<th>发件人</th>
						<th>内容</th>
						<th>操作</th>
					</tr>
				<s:iterator value="meList" status="index" >
				<s:if test="state==0"><!-- 未读 -->
					<tr class="unread">
						<td><s:property value="userName" /></td>
						<td><s:property value="content" /></td>
						<td><a id="unread<s:property value='id' />" href="javascript:;" onclick="readMsg(<s:property value='id' />)">设为已读</a>　
							<a href="toMessageSend.action?receiverName=<s:property value='userName' />">回复</a>　
							<a href="javascript:;" onclick="deleteMsg(<s:property value='id' />)">删除</a>
						</td>
					</tr>
				</s:if><s:else><!-- 已读 -->
					<tr>
						<td><s:property value="userName" /></td>
						<td><s:property value="content" /></td>
						<td>　<a href="toMessageSend.action?receiverName=<s:property value='userName' />">回复</a>　
							 <a href="javascript:;" onclick="deleteMsg(<s:property value='id' />)">删除</a></td>
					</tr>
				</s:else>					
				</s:iterator>
					</table>
    			</div>
    		</div>
    	</div>
    <%@ include file="../footer.jsp"%>
    </div>    
    </div><!--/ content -->    
    </div><!--/ container -->
    <script type="text/javascript" src="../js/user/message.js"></script>
</body>
</html>