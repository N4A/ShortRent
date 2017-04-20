/**
 * ajax对用户修改资料的合法性进行检测
 * link to： user/user.jsp
 * 2015/07/30
 */
function updateUser() {
	$.ajax({
        cache: true,
        type: "POST",
        url:"userUpdate.action",
        data:$('#l-form-user-update').serialize(),
        async: false,
        error: function(request) {
            $("#l-error-prompt-user").html("连接服务器失败");
        },
        success: function(data) {
    		if (data.result==="0") {
    			alert("更改成功！");
    			location.href="toUserUpdate.action";
    		} else {
            	$("#l-error-prompt-user").text(data.result);
    		}
        }
    });
}