<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
   

    <div class="row">
    <div class="col-sm-12">
    <!-- Website Menu -->
    <div id="topmenu">
    <ul class="dropdown clearfix boxed boxed-blue">
    <li class="menu-level-0"><a><span><s:property value="#session.user.username" ></s:property>，欢迎你</span></a></li>
    <li class="menu-level-0"><a href="user/toHouseManage.action"><span>控制面板</span></a></li>
    <li class="menu-level-0"><a href="newRoom.jsp"><span>发布房源</span></a></li>
    <li class="menu-level-0"><a href="default.action"><span>返回主页</span></a></li>
    
    <li class="menu-level-0"><a href="javascript:;" onclick="out()"><span>登出</span></a></li>
    <li class="menu-search">
    <form id="searchForm" action="HouseAction!searchHouses.action" class="menu-search-form" method="post">
    <input type="text" name="su.address" value="" class="menu-search-field" placeholder="去哪儿" />
    <input type="submit" value="" class="btn menu-search-submit" name="search-send" />
    </form>
    </li>
    </ul>
    </div>
    <!--/ Website Menu -->
    </div>
    </div>