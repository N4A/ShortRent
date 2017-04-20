<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>个人信息</title>
    <!-- main JS libs -->
    <script src="../js/libs/modernizr.min.js"></script>
    <script src="../js/libs/jquery-1.10.0.js"></script>
    <script src="../js/libs/jquery-ui.min.js"></script>
    <script src="../js/libs/bootstrap.min.js"></script>
    <script src="../js/jquery.powerful-placeholder.min.js"></script>

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
    <script type="text/javascript" src="../js/user/user.js"></script>
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
    				<form action="" id="l-form-user-update" class="l-form" method="post">
    					<div><span>用户名：</span><s:property value="#session.user.username" ></s:property></div>
    					<input type="hidden" name="u.username" value="<s:property value="#session.user.username" ></s:property>">
    					<div><span>性别：</span>
    						 <span class="input_styled inlinelist">
							    <span class="rowRadio l-radio">
							        <input type="radio" name="u.gender" id="male" value="1" checked>
							        <label for="male">男</label>
							    </span>
							    <span class="rowRadio l-radio">
							        <input type="radio" name="u.gender" id="female" value="2">
							        <label for="female">女</label>
							    </span>
							</span>
    					</div>
    					<div class="field_text">
							<input type="text" name="u.mobile" id="mobile" placeholder="手机号（不更改请留空）">
						</div>
    					<div class="field_text">
							<input type="text" name="u.address" id="address" placeholder="地址（不更改请留空）">
						</div>
    					<div class="field_text">
							<input type="text" name="u.email" id="email" placeholder="邮箱（不更改请留空）">
						</div>
						<div>&nbsp;</div>
						<div><span>更改密码：</span></div>
						<div class="field_text">
							<input type="password" name="u.password" id="password" placeholder="新密码（不更改请留空）">
						</div>
						<div class="field_text">
							<input type="password" name="rePassword" id="rePassword" placeholder="重复新密码">
						</div>
						<div class="field_text">
							<input type="password" name="oldPassword" id="oldPassword" placeholder="旧密码">
						</div>
    					<span class="btn btn-icon-left" id="l-user-submit"><input type="button" onclick="updateUser()" value="提交" /></span>
    					<div id="l-error-prompt-user">&nbsp;</div>
    				</form>


    			</div>
    		</div>
    	</div>
    <%@ include file="../footer.jsp"%>
    </div>    
    </div><!--/ content -->    
    </div><!--/ container -->
    <script>
	$("input").focus(function(){  // 用户修改时去掉错误信息
		$("#l-error-prompt-user").html("&nbsp;");
	});
</script>
</body>
</html>