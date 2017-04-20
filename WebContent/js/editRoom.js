function editRoom(){
    $.ajax({
        cache: true,
        type: "POST",
        url:"updateRoom.action",
        data:$('#editRoomForm').serialize(),// 你的formid
        async: false,
        error: function(request) {
            $("#showMessage1").html("链接错误");

        },
        success: function(data) {
//如果未通过检测，相应的错误显示在$("#showMessage1").html()中
        	if(data.success==="not"){
        		//1名称
                //2日租价
                //3地址
                //4面积
        		$("#showMessage1").html(data.result0);
        		$("#showMessage2").html(data.result1);
        		$("#showMessage3").html(data.result2);
        		$("#showMessage4").html(data.result3);
        		$("#showMessage5").html(data.result4);
        		$("#verify").click();
        	}
        	else {
        		//alert($("#houseIdFE").val());
//        		location.href="HouseAction!toHouseDetail.action?houseId="+data.houseId;
        		location.href="uploadPics.jsp?houseId="+$("#houseIdFE").val()+"&isCut=0";
        	}
        }
    });
}
$("#name").focus(function(){
    $("#showMessage1").html("");
});
$("#dayPrice").focus(function(){
    $("#showMessage2").html("");
});
$("#address").focus(function(){
    $("#showMessage3").html("");
});
$("#area").focus(function(){
    $("#showMessage4").html("");
});