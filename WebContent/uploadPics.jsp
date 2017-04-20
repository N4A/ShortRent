<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>裁剪图片</title>
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
<link href="css/upload.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- scripts -->
<script type="text/javascript" src="js/general.js"></script>
<script type="text/javascript" src="js/header-popmenu.js"></script>
<script type="text/javascript" src="js/header.js"></script>

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
				<!--/ row -->

				<s:hidden name="houseId" value=""></s:hidden>
				<div class="row">
					<div class="col-sm-12">

						<div id="title" class="boxed-green">
							<h3 id="up_title">上传您的房屋图片</h3>
							<h3 id="down_title">让您的房屋介绍更精彩</h3>
						</div>


					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="boxed-yellow">

							<i id="icon" class="icon-cloud-upload icon-3x"></i>
							<s:if test="isCut==1">
								<form action="upload.action" method="post"
									enctype="multipart/form-data">
									上传文件(图片2M以内)：<br> 
									<input type="hidden" name="type" value="0">
									<input type="hidden" name="houseId" value="${houseId}"><br>
									<input type="hidden" name="numberOfPic" value="${numberOfPic}"><br>
									<input type="file" name="mf"><span id="error"></span><br>
									<a id="submit_cut" onclick="submit(this)"
										class="btn btn-blue but"><span>继续上传</span></a>  <a
										class="btn btn-blue but"
										href="default.action">不再上传,回到主页</a>
								</form>
							</s:if >
							<s:else>
								<form action="upload.action" method="post" enctype="multipart/form-data">
									上传文件(图片2M以内)：<br> 
									<input type="hidden" name="type" value="0">
									<input type="hidden" name="houseId" value="<%=request.getParameter("houseId")%>">
									<input type="file" name="mf"><span id="error"></span><br>
									<a id="submit_cut" onclick="submit(this)" class="btn btn-blue but"><span>确认上传</span></a>
								</form>
							</s:else>
						</div>
					</div>
				</div>

			</div>






			<%@ include file="footer.jsp"%>
		</div>
	</div>



</body>
</html>