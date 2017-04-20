function login(){
   
    $.ajax({
        cache: true,
        type: "POST",
        url:"login.action",
        data:$('#loginForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            $("#LoginErrorMessage").html("链接错误");

        },
        success: function(data) {
        	if(data.result==="0")
        		location.href="default.action";
        	else {
                $("#LoginErrorMessage").html(data.result);
                $("#verify").click();
            }
        }
    });
}
function out(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"logout.action",
        //data:$('#registerForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            //$("#LoginErrorMessage").html("链接错误");

        },
        success: function(data) {
    			location.href="default.action";
        }
    });
}
function register(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"register.action",
        data:$('#registerForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            $("#LoginErrorMessage").html("链接错误");

        },
        success: function(data) {
    		if(data.result==="0")
    			location.href="default.action";
    		else{
            	$("#RegisterErrorMessage").text(data.result);
            	$("#verify").click();
    		}
        }
    });
}
function build(){
	//alert("");
	$.ajax({
        cache: true,
        type: "POST",
        url:"register.action",
        data:$('#newRoomForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
        	alert("error");

        },
        success: function(data) {
    		if(data.result==="0")
    			location.href="default.action";
    		else{
            	$("#RegisterErrorMessage").text(data.result);
            	$("#verify").click();
    		}
        }
    });
}
function submit(pos){
	if($(pos).prev().prev().prev().val()){//file input
		
		$(pos).parent().submit();
		
	}
	else{//未选择图片
		$("#error").html("未选择图片");
	}
}