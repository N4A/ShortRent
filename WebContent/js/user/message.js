
/**
 * 处理删除
 */
function deleteMsg(id) {
	if (confirm("确定删除吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"msgDelete.action?me.id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		alert("删除成功！");
	    		location.href= "toMessage.action";
	        }
	    });
	}
}

/**
 * 处理阅读
 */
function readMsg(id) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"msgRead.action?me.id="+id,
	        async: false,
	        success: function(data) {
	    		$("#unread"+id).hide();
	        }
	    });
}