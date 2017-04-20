/**
 * 用于房屋管理页面的分页相关处理
 * 由于是立即执行代码且原来引用的js会有问题所以建议放在页面底部
 */
	    //$(function(){  // general.js有个地方有错，所以只能暴力执行下面的代码

	    	// 当页数少于3时隐藏后两个
	    	if (maxPage < 3) {
	    		$("#page-3").hide();
	 			if (maxPage == 1) {
	 				$("#page-2").hide();
	 			}
	    	}
	    	// 页数大于3的场合
	    	if (maxPage > 3 && cpage > 2) {  // 不是1|2|3的场合
	    		if (cpage != maxPage) {  // 不是最大页，那么当前页面在中间一个
	    			$("#page-1").text(cpage - 1);
	    			$("#page-1").attr("href", "toHouseAdmin.action?page=" + (cpage - 1));
	    			$("#page-2").text(cpage);
	    			$("#page-2").attr("href", "toHouseAdmin.action?page=" + (cpage));
	    			$("#page-3").text(cpage + 1);
	    			$("#page-3").attr("href", "toHouseAdmin.action?page=" + (cpage + 1));
	    		} else { // 当前是最大页
	    			$("#page-1").text(cpage - 2);
	    			$("#page-1").attr("href", "toHouseAdmin.action?page=" + (cpage - 2));
	    			$("#page-2").text(cpage - 1);
	    			$("#page-2").attr("href", "toHouseAdmin.action?page=" + (cpage - 1));
	    			$("#page-3").text(cpage);
	    			$("#page-3").attr("href", "toHouseAdmin.action?page=" + (cpage));
	    		}
	    	}
	    	// 设置前一页，后一页的链接。简单+-即可，后台会做判断
	    	$("#page-prev").attr("href", "toHouseAdmin.action?page=" + (cpage - 1));
	    	$("#page-next").attr("href", "toHouseAdmin.action?page=" + (cpage + 1));
	    	// 当前页面高亮
	    	$("a.page-numbers").each(function() {
	    		if ($(this).text() == cpage) {   // 根据传入参数：当前页码 替换
	    			$(this).addClass("page_current");
	    		}
	    	})
	    //});
	    	
	    	
/**
 * 处理拒绝
 */
function refuse(id) {
	if (confirm("确定拒绝吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"houseRefuse.action?id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		location.href= "toHouseAdmin.action";
	        }
	    });
	}    		
}

/**
 * 处理接受
 */
function accept(id) {
	if (confirm("确定接受吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"houseAccept.action?id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		location.href= "toHouseAdmin.action";
	        }
	    });
	}   
}
