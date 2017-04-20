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
	    		location.href= "toMessageSend.action";
	        }
	    });
	}
	
}

/**
 * 站内信功能
 */
function sendMessage() {
	$.ajax({
        cache: true,
        type: "POST",
        url:"messageSend.action",
        data:$('#l-form-message-send').serialize(),
        async: false,
        error: function(request) {
            $("#l-error-prompt-message").html("连接服务器失败");
        },
        success: function(data) {
    		if (data.result==="0") {
    			alert("发送成功！");
    			location.href="toMessageSend.action";
    		} else {
            	$("#l-error-prompt-message").text(data.result);
    		}
        }
    });
}
