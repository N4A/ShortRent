<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
	<title>轻松短租网</title>
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
    

    <!-- scripts -->
    <script src="js/general.js"></script>
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
    <div class="row">
    <div class="col-sm-12">
     <!-- Image Slider -->
                    <div class="widget-container widget-gallery boxed">
                        <div class="inner">
                            <div id="myCarousel" class="carousel slide" data-interval="20000">
                                <!-- Carousel items -->
                                <div class="carousel-inner">
                                	<s:iterator value="pics" status="index">
                                		<s:if test="#index.first">
                                			<div class="active item"><a href="upload/<s:property/>" class="prettyPhoto" data-rel="prettyPhoto" title="Show Image"><img width="100%" height="100%" src="upload/<s:property/>" alt="" /></a></div>                                	
                                		</s:if>
                                		<s:else>
                                			<div class="item"><a href="upload/<s:property/>" class="prettyPhoto" data-rel="prettyPhoto" title="Show Image"><img src="upload/<s:property/>" alt="" /></a></div>
                                		</s:else>                                	
	                                                                    	
                                	</s:iterator>
                                </div>
                                <div class="carousel-controls">
                                    <!-- Carousel indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                        <li data-target="#myCarousel" data-slide-to="1"></li>
                                        <li data-target="#myCarousel" data-slide-to="2"></li>
                                        <li data-target="#myCarousel" data-slide-to="3"></li>
                                        <li data-target="#myCarousel" data-slide-to="4"></li>
                                    </ol>
                                    <!-- Carousel nav -->
                                    <a class="carousel-control left" href="#myCarousel" data-slide="prev"></a>
                                    <a class="carousel-control right" href="#myCarousel" data-slide="next"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/ Image Slider -->
    
    </div>
    </div>
    <div class="row">
    <div class="col-sm-12">
    <!-- Grid Menu -->
    <div class="grid-menu clearfix">
<!--     房屋列表 -->
    	<s:iterator value="list" status="index">
    		<div class="grid-box"><a href="HouseAction!toHouseDetail.action?houseId=<s:property value="id"/>"><img src="upload/h0_<s:property value="id"/>.png" alt="" /></a>
   			<div ><p class="grid-p"><s:property value="name"/></p></div></div>
    	</s:iterator>
    </div>
    <!--/ Grid Menu -->
    </div>
    </div>
    <%@ include file="footer.jsp"%>    
    </div>
    <!--/ content -->
    </div>
    <!--/ container -->
    </div>

</body>
</html>