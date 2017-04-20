<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>用户管理</title>
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
					<caption>用户管理</caption>
					<tr>
						<th>用户名</th>
						<th>地址</th>
						<th>手机</th>
						<th>邮箱</th>
					</tr>
					<s:iterator value="userList" status="index" >
					<tr>
						<td><s:property value="username" /></td>
						<td><s:property value="address" /></td>
						<td><s:property value="mobile" /></td>
						<td><s:property value="email" /></td>
					</tr>
					</s:iterator>
					</table>
    			</div>
				<div class="tf_pagination style3 l-pagination l-pagination-yellow">
                    <div class="inner">
                        <a id="page-prev" class="page_prev" href="toUserAdmin.action?page=1"><span>&lsaquo;</span></a>
						<!-- 动态生成，或者就这样，然后生成下前一页后一页的链接 -->
						<a id="page-1" href="toUserAdmin.action?page=1" class="page-numbers">1</a>
                        <a id="page-2" href="toUserAdmin.action?page=2" class="page-numbers">2</a>
                        <a id="page-3" href="toUserAdmin.action?page=3" class="page-numbers">3</a>
			            <a id="page-next" class="page_next" href="toUserAdmin.action?page=2"><span>&rsaquo;</span></a>
                    </div>
                </div>
    		</div>
    	</div>
    <%@ include file="../footer.jsp"%>
    </div>    
    </div><!--/ content -->    
    </div><!--/ container -->
    <script type="text/javascript">  // 为分页添加当前分页的高亮功能
	// 获得参数, 不能放在外部文件
	var cpage = <s:property value="page" />;
	var maxPage = <s:property value="maxPage" />;
    </script>
    <script type="text/javascript" src="../js/user/user-manage.js"></script>
</body>
</html>