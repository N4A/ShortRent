function newOrder() {

	var checkin = $("#checkin").val();
	var checkout = $("#checkout").val();
	
    var year1=checkin.substr(0,4);
    var year2=checkout.substr(0,4);
    var month1=checkin.substr(5,2);
    var month2=checkout.substr(5,2);
    var day1=checkin.substr(8,2);
    var day2=checkout.substr(8,2);
    if (checkin == "") {
		$("#date_info").html("入住时间不能为空");
		return;
	}
	if (checkout == "") {
		$("#date_info").html("退房时间不能为空");
		return;
	}
	if (checkin == checkout) {
		$("#date_info").html("入住时间不能与退房时间相同");
		return;
	}
	if(year1>year2){	
		$("#date_info").html("入住时间不能晚于退房时间");
		return;
	}
	else{
		if(month1>month2&&year1==year2){
			$("#date_info").html("入住时间不能晚于退房时间");
			return;
		}else{
			if(day1>day2&&month1==month2){
				$("#date_info").html("入住时间不能晚于退房时间");
				return;
			}
			
		}
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "addOrder.action",
		data:$('#orderForm').serialize(),
		async : false,
		error : function(request) {
			$("#date_info").html("链接错误");
		},
		success : function(data) {
			$("#date_info").html(data);
//			if (data==="下单成功") {
				alert(data);
//			}
//			if (data) {
//				$("#date_info").html("下单成功");
//			} else if (data) {
//				$("#date_info").html("该段时间已出租");
//			}
//			else if(data){
//				$("#date_info").html("您的入住时间不足最小天数");
//			}
//			else if(data){
//				$("#date_info").html("您的入住时间超出最大天数");
//			}
//
//			else {
//				$("#date_info").html("输入日期不正确");
//			}
		}
	});
}

	
	
