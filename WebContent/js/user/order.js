/**
 * 处理拒绝
 */
function refuse(id) {
	if (confirm("确定拒绝吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"orderRefuse.action?id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		location.href= "toOrderOwner.action";
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
	        url:"orderAccept.action?id="+id,
	        async: false,
	        error: function(request) {
	            //alert("ok");
	            location.href= "toOrderOwner.action";
	        },
	        success: function(data) {
	    		location.href= "toOrderOwner.action";
	        }
	    });
	}   
}

/**
 * 处理取消
 */
function cancel(id) {
	if (confirm("确定取消吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"orderCancel.action?id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		location.href= "toOrderUser.action";
	        }
	    });
	}   
}

/**
 * 处理完成
 */
function finish(id) {
	if (confirm("确定已经完成订单吗？")) {
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"orderFinish.action?id="+id,
	        async: false,
	        error: function(request) {
	            alert("连接服务器失败");
	        },
	        success: function(data) {
	    		location.href= "toOrderUser.action";
	        }
	    });
	}   
}

/**
 * 处理评价
 */
function showComment(userId, houseId, orderId) {
	$("#popback").show();
	$("#popComment").css("margin", "-45% 0 0 30%");
	$("#popComment").show();
	// 设置type=hidden区域
	$("#userIdFC").attr("value", userId);
	$("#houseIdFC").attr("value", houseId);
	$("#orderIdFC").attr("value", orderId);
}

function comment() {
	$.ajax({
        cache: true,
        type: "POST",
        url:"comment.action",
        data:$('#commentForm').serialize(),
        async: false,
        error: function(request) {
            $("#errorPrompt").html("连接服务器失败");
        },
        success: function(data) {
    		if (data.result==="0") {
    			alert("发表成功！");
    			location.href="toOrderUser.action";
    		} else {
            	$("#errorPrompt").text(data.result);
    		}
        }
    });
}

function closeComment() {
	$("#popback").hide();
	$("#popComment").hide();
}