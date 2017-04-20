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
<script src="js/jquery.min.js"></script>
	<script src="js/jquery.Jcrop.min.js"></script>
	<script src="js/cutUser.js"></script>
	<link rel="stylesheet" href="css/jquery.Jcrop.min.css" type="text/css" />
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
							<h3 id="up_title">裁剪您的头像</h3>
							<h3 id="down_title">让您更炫酷</h3>
						</div>


					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="boxed-green" id="cut">
							<!-- This is the image we're attaching Jcrop to -->
							<div id="img" style="width:800px;height:600px;padding-left:${paddingLeft}px;padding-top:${padding}px;background:gray;">
								<img src="upload/${filePath}" width="${width}px" height="${height}px" id="target" alt="[Jcrop Example]" />
							</div>
							<!-- This is the form that our event handler fills -->
							<form id="coords" class="coords" method="post" action="CutImage.action">
								<div class="inline-labels">
									<input type="hidden" name="type" value="1">
									<input type="hidden" name="toSmall" id="toSmall" value="${toSmall}"/>
									<input type="hidden" name="userId" id="23" value="${userId}"/>
									<input type="hidden" name="filePath" value="${filePath}" />
									<input type="hidden" name="numberOfPic" value="${numberOfPic}" />
									<input type="hidden" size="4" id="x1" name="x1" />
									<input type="hidden" size="4" id="y1" name="y1" />
									<input type="hidden" size="4" id="x2" name="x2" />
									<input type="hidden" size="4" id="y2" name="y2" />
									<input type="hidden" size="4" id="w" name="width" />
									<input type="hidden" size="4" id="h" name="height" />
								</div>
								<span id="error"></span><br>
								<a id="submit_cut" onclick="submit(this)" class="btn btn-blue but"><span>确认裁剪</span></a>
							</form>
							
						</div>
					</div>
				</div>

			</div>

			<%@ include file="footer.jsp"%>
		</div>
	</div>
</body>
</html>