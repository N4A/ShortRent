<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>发站内信</title>
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
    			<div class="l-foldbox l-box-yellow l-toggle">
    				<div class="l-foldbox-title">发件箱<a href="javascript:;" onclick="toggle()" class="btn btn-blue l-foldbox-btn"><span>展开</span></a></div>
    			</div>
    			<div class="l-box l-box-yellow l-toggle" style='display:none;'>
    				<a href="javascript:;" onclick="toggle()" class="btn btn-blue l-box-foldbtn"><span>收起</span></a>
    				<table class="l-table">
					<caption>发件箱</caption>
					
					<tr>
						<th>收件人</th>
						<th>内容</th>
						<th>操作</th>
					</tr>
				<s:iterator value="meList" status="index" >
					<tr>
						<td><s:property value="targetName" /></td>
						<td><s:property value="content" /></td>
						<td><a href="javascript:;" onclick="deleteMsg(<s:property value='id' />)">删除</a></td>
					</tr>				
				</s:iterator>
					
					</table>
    			</div>
    			<div>　<br>　</div>
    			<div class="l-box l-box-red">
    				<form action="" class="l-form" id="l-form-message-send" method="post">
    					<div><span>发信人：</span><s:property value="#session.user.username" ></s:property></div>
    					<input type="hidden" name="me.userId" value="<s:property value="#session.user.id" ></s:property>">
    					<div class="field_text">
							<input type="text" name="receiverName" id="receiver" placeholder="请输入收信人的用户名">
						</div>
    					<div class="field_text field_textarea">
						    <textarea id="content" name="me.content" placeholder="请输入内容（限140字）"></textarea>
						</div>

    					<span class="btn btn-icon-left" id="l-message-submit"><input type="button" onclick="sendMessage()" value="发送" /></span>
    					<div id="l-error-prompt-message">&nbsp;</div>
    				</form>
    			</div>
    		</div>
    	</div>
    <%@ include file="../footer.jsp"%>
    </div>    
    </div><!--/ content -->    
    </div><!--/ container -->
    <script type="text/javascript">  // 添加按键切换功能
	    function toggle() {
	    	$(".l-toggle").each(function() {
	    		$(this).toggle();
	    	})
	    }
		$("input#receiver").focus(function(){  // 用户修改时去掉错误信息
			$("#l-error-prompt-message").html("&nbsp;");
		});
		$("#receiver").attr("value", "<s:property value='receiverName' />");
    </script>
    <script type="text/javascript" src="../js/user/message-send.js"></script>
</body>
</html>