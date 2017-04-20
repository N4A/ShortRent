function login(){
    $.ajax({
        cache: true,
        type: "POST",
        url:login.action,
        data:$('#loginForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            $("#LoginErrorMessage").html("链接错误");

        },
        success: function(data) {
            if(data=="0"){
                $("#LoginErrorMessage").html("用户名或密码错误!");
            }else if(data="1"){
                $("#LoginErrorMessage").html("验证码错误!");
            }else{
history.go(0);
            }
        }
    });
}