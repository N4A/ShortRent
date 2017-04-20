<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div class="row">
    <div class="col-sm-12">
    <!-- Website Menu -->
    <div id="topmenu">
    <ul class="dropdown clearfix boxed boxed-blue">
    <li class="menu-level-0"><a href="javascript:openLogin()"><span>登录</span></a></li>
    <li class="menu-level-0"><a href="javascript:openRegister()"><span>注册</span></a></li>
    <li class="menu-level-0"><a href="default.action"><span>返回主页</span></a></li>
    <li class="menu-search">
    <form id="searchForm" action="HouseAction!searchHouses.action" class="menu-search-form" method="post">
    <input type="text" name="su.address" value="" class="menu-search-field" placeholder="想去哪?" />
    <input type="submit" value="" class="btn menu-search-submit" name="search-send" />
    </form>
    </li>
    </ul>
    </div>
    <!--/ Website Menu -->																																																																							
    </div>
    </div>
    <div id="popback"></div>
<div id="popLogin" class="popmenu">
	<a href="javascript:close()" class="closePop">x</a>
	<h3>登录</h3>
	<p class="prompt" id="LoginErrorMessage" >&nbsp;</p>
	<form id="loginForm" method="post" >
		<p class="prompt" id="LoginErrorMessage" >&nbsp;</p>
        <p><label class="inputLabel">输入用户名:
			<input type="text" id="LUsername" name="u.username" size="20" />
		</label></p>
		<p><label class="inputLabel">输入密码:
			<input type="password" id="LPassword" name="u.password" size="20" />
		</label></p>
		<p>
		<p><label class="inputLabel">输入验证码
			<input type="text" id="verification" name="verification" size="20" maxlength="4" />
			<a href="javascript:;" id="verify" onclick="document.getElementById('image').src = 'verification.action?time='+Math.random();"> 
			<img id="image" src= "verification.action" /></a>
		</label></p>
		<label class="inputLabel">
			<a class="blue-color"  id="LSubmit" onclick="login()" >登录</a>
			<a class="blue-color" href="javascript:void(0)" onclick="openRegister()" >注册页面</a>
		</label>
		</p>		
	</form>
</div>
<div id="popRegister" class="popmenu">
	<a href="javascript:close()" class="closePop">x</a>
	<h3>注册</h3>
	<form id="registerForm" action="register" method="post" >
		<p class="prompt" id="RegisterErrorMessage" >&nbsp;</p>
		<p><label class="inputLabel">用户名:
			<input type="text" id="username" name="u.username" size="20" maxlength="15" />
		</label></p>
		<p><label class="inputLabel">密码:
			<input type="password" id="password" name="u.password" size="20" maxlength="20" />
		</label></p>
		<p><label class="inputLabel">确认密码
			<input type="password" id="rePassword" name="rePassword" size="20" maxlength="20" />
		</label></p>
		<p><label class="inputLabel">手机号
			<input type="text" id="mobile" name="u.mobile" size="20" maxlength="11" />
		</label></p>
		<p><label class="inputLabel">验证码
			<input type="text" id="verification" name="verification" size="20" maxlength="4" />
			<a href="javascript:;" id="verify" onclick="document.getElementById('image1').src = 'verification.action?time='+Math.random();">
			<img id="image1" src= "verification.action" /></a>
		</label></p>
		<p>
		<label class="inputLabel">
			<a class="blue-color"  class="inputLabel" onclick="register()">注册</a>
			<a class="blue-color" href="javascript:void(0)" onclick="openLogin()"  >登录页面</a>
		</label>
		</p>		
	</form>
</div>

<!-- 添加处理脚本 -->
<script>
	$("input").focus(function(){
		$("#RegisterErrorMessage").html("&nbsp;");
		$("#LoginErrorMessage").html("&nbsp;");
	});
</script>