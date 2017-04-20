/**
 * place header and fixed to show current user's information
 */

function showHeader(isLogined, args) {
	if (!isLogined) {
		document.getElementById("innerHeader").innerHTML="\
			<a href='javascript:openLogin()'>登录</a>　\
			<a href='javascript:openRegister()'>注册</a>\
		";
	} else {
		document.getElementById("innerHeader").innerHTML="\
		<a href='#'>您好，"+ args[0] + "</a>　\
		<a href='home?u=" + args[1] + "'>我的博客</a>　\
		<a href='editor.jsp'>发布</a>　\
		<a href='admin-blog.jsp'>设置</a>　\
		<a href='javascript:clearLoginInfo()'>退出</a>\
		";
	}
}

/**
 * set pop out menu for login and register
 */
 
function openLogin() {
    document.getElementById("popback").style.display="block";
    document.getElementById("popRegister").style.display="none";
    document.getElementById("popLogin").style.display="block" ; 
    document.body.style.overflow = "hidden";
}

function openRegister() {
    document.getElementById("popback").style.display="block";
    document.getElementById("popLogin").style.display="none";
    document.getElementById("popRegister").style.display="block" ; 
    document.body.style.overflow = "hidden";
}

function close() {
	document.getElementById("popback").style.display="none";
    document.getElementById("popLogin").style.display="none"; 
    document.getElementById("popRegister").style.display="none"; 
    document.body.style.overflow = "auto";
}

/**
 * clear login info
 */
function clearLoginInfo() {
	if (confirm("确定退出当前用户吗？")) {
	    var exp = new Date();
	    exp.setTime(exp.getTime() - 1);
		document.cookie = "cdata=;expires="+exp.toGMTString(); 
		location.href="index.jsp";
	}
}


/*
 * use ajax
 * from: http://www.2cto.com/kf/201301/186440.html
 */
//创建XMLHttpRequest对象   
function GetO() {   
	var ajax = false;   
	try {   
		ajax = new ActiveXObject("Msxml2.XMLHTTP");   
	} catch (e) {   
		try {   
			ajax = new ActiveXObject("Microsoft.XMLHTTP");   
		} catch (E) {   
			ajax = false;   
		}   
   }   
   if (!ajax && typeof XMLHttpRequest!='undefined') {   
	   ajax = new XMLHttpRequest();   
   }   
   return ajax;   
}   
  
function getMyHTML(type, serverPage, objID) {   
	var ajax = GetO();   
   //得到了一个html元素，在下面给这个元素的属性赋值   
	var obj = document.all[objID];   
   //设置请求方法及目标，并且设置为异步提交   
	ajax.open(type, serverPage, true);   
	ajax.onreadystatechange = function() {   
		if (ajax.readyState == 4 && ajax.status == 200) {   
			//ajax.responseText是服务器的返回值
			if (obj != undefined) {  // 有时（点赞时）只要ajax后台更改，不需要前台返回提示，则可以忽略第三个参数
				obj.innerHTML = ajax.responseText;
			}
			verifyLoginRedirect(ajax.responseText);   // 如果是登录时的ajax且登录成功那么跳转，见login.js
		}   
	}   
	//发送请求   
	ajax.send(null);   
}   

