<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-sm-2">
	<ul class="clearfix dropdown menu-vertical boxed boxed-green l-menu">
	    <li class="active">
	        <a href="toUserUpdate.action"><span><i class="icon-user icon-2x"></i></span></a>
	    </li>
	    <li>
	        <a href="../uploadUser.jsp?userId=<s:property value="#session.user.id" />"><span><i class="icon-camera-retro icon-2x"></i></span></a>
	    </li>
	    <li>
	        <a href="#"><span><i class="icon-file-alt icon-2x"></i></span></a>
	        <ul class="l-submenu">
	            <li><a href="toOrderUser.action">我是房客</a></li>
	            <li><a href="toOrderOwner.action">我是房东</a></li>
	        </ul>
	    </li>
	    <li>
	        <a href="toHouseManage.action"><span><i class="icon-home icon-2x"></i></span></a>
	    </li>
	    <li>
	        <a href="#"><span><i class="icon-envelope-alt icon-2x"></i></span></a>
	        <ul class="l-submenu">
	            <li><a href="toMessage.action">收信</a></li>
	            <li><a href="toMessageSend.action">发信</a></li>
	        </ul>
	    </li>
	</ul>
</div>
