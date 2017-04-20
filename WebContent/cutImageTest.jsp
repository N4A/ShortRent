<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Basic Handler | Jcrop Demo</title>
  <meta http-equiv="Content-type" content="text/html;charset=UTF-8" />

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.Jcrop.min.js"></script>
	<script src="js/test.js"></script>
	<link rel="stylesheet" href="css/jquery.Jcrop.min.css" type="text/css" />
</head>
	<body>
	<h1>Basic Handler</h1>
	<!-- This is the image we're attaching Jcrop to -->
	<div id="img" style="width:${divWidth}px;height:${divHeight}px;padding-left:${paddingLeft}px;padding-top:${padding}px;background:gray;">
		<img src="upload/${filePath}" width="${width}px" height="${height}px" id="target" alt="[Jcrop Example]" />
	</div>
	<!-- This is the form that our event handler fills -->
	<form id="coords" class="coords" method="post" action="CutImage.action">
		<div class="inline-labels">
			<input type="text" name="toSmall" id="toSmall" value="${toSmall}"/>
			<input type="text" name="houseId" id="23" value="${houseId}"/>
			<input type="text" name="filePath" value="${filePath}" />
			<input type="text" name="numberOfPic" value="${numberOfPic}" />
			<label>X1 <input type="text" size="4" id="x1" name="x1" /></label>
			<label>Y1 <input type="text" size="4" id="y1" name="y1" /></label>
			<label>X2 <input type="text" size="4" id="x2" name="x2" /></label>
			<label>Y2 <input type="text" size="4" id="y2" name="y2" /></label>
			<label>W <input type="text" size="4" id="w" name="width" /></label>
			<label>H <input type="text" size="4" id="h" name="height" /></label>
		</div>
		<input type="submit" value="提交" />
	</form>
</body>
</html>
