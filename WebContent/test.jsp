<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="newRoomForm" action="newHouse.action" method="post">
				<div class="row">
					<div class="col-sm-4 ">

						<div class="form-wrapper boxed-green">
							<span><input type="text" id="name" name="ho.name"
								placeholder="房屋名称(必填)"> 
								<div id="showMessage1">&nbsp;</div>
							</span> <span> <input type="text" id="dayPrice"
								name="dayPrice" placeholder="日租价(必填)"> <div
								id="showMessage2">&nbsp;</div>
							</span> <span> <textarea cols="27" rows="2" id="address"
									name="ho.address" placeholder="详细地址(必填)"></textarea> <div
								id="showMessage3">&nbsp;</div>
							</span>
							<div class="field_select">
								最大可住人数 <select class="select_styled" name="ho.guestNum"
									id="guestNum">
									<option value="1">1人</option>
									<option value="2">2人</option>
									<option value="3">3人</option>
									<option value="4">4人</option>
									<option value="5">5人</option>
									<option value="6">6人</option>
									<option value="7">7人</option>
									<option value="8">8人</option>
									<option value="9">9人</option>
									<option value="10">10人</option>

								</select>
							</div>
							<div class="field_select">
								房间数 <select class="select_styled" name="ho.roomNum" id="roomNum">
									<option value="1">1间</option>
									<option value="2">2间</option>
									<option value="3">3间</option>
									<option value="4">4间</option>
									<option value="5">5间</option>
									<option value="6">6间</option>
									<option value="7">7间</option>
									<option value="8">8间</option>
									<option value="9">9间</option>
									<option value="10">10间</option>
								</select>
							</div>
							<span> <input type="text" id="area" name="area"
								placeholder="总面积"> <div id="showMessage4">&nbsp;</div>
							</span>
							<div class="input_styled inlinelist">
								<div class="rowRadio">
									<label><input checked="checked" name="ho.rentType"
										type="radio" value="1" />整租 </label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.rentType"
										type="radio" value="2" />单间 </label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.rentType"
										type="radio" value="3" />床位 </label>
								</div>
							</div>
							<label id="pic">上传图片<input type="file" name="mf"  class="btn btn-yellow but"></label>
							
						</div>

					</div>
					<div class="col-sm-4">
						<div class="form-wrapper boxed-yellow">
						<div class="field_select">
								卧室数 <select class="select_styled" name="ho.bedroomNum" id="bedroomNum">
									<option value="1">1间</option>
									<option value="2">2间</option>
									<option value="3">3间</option>
									<option value="4">4间</option>
									<option value="5">5间</option>
									<option value="6">6间</option>
								</select>
							</div>
							<div class="field_select">
								卫生间数 <select class="select_styled" name="ho.toiletNum" id="toiletNum">
									<option value="1">1间</option>
									<option value="2">2间</option>
									<option value="3">3间</option>
									<option value="4">4间</option>
									
								</select>
							</div>
							<div class="field_select">
								床位数 <select class="select_styled" name="ho.bedNum" id="bedNum">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>	
								</select>
							</div>
							<div class="field_select">
								全额退款日 <select class="select_styled" name="ho.refundDay" id="">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									</select>
							</div>
							 <span> <textarea cols="27" rows="2" id="useRule"
									name="ho.useRule" placeholder="使用规则"></textarea> <span
								>&nbsp;</span>
							</span>
							 <span> <textarea cols="27" rows="2" id="roomDesc"
									name="ho.roomDesc" placeholder="房间描述"></textarea> <span
								>&nbsp;</span>
							</span>
							<div class="input_styled inlinelist" id="bedType">
								<div class="rowRadio">
									<label><input checked="checked" name="ho.bedType"
										type="radio" value="1" />单人床</label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.bedType"
										type="radio" value="2" />双人床 </label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.bedType"
										type="radio" value="3" />高低床</label>
								</div>
							</div>
							
							<div class="input_styled inlinelist" id="kind">
								<div class="rowRadio">
									<label><input checked="checked" name="ho.kind"
										type="radio" value="1" />酒店</label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.kind"
										type="radio" value="2" />旅店 </label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.kind"
										type="radio" value="3" />客栈</label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.kind"
										type="radio" value="4" />民居</label>
								</div>
							</div>
						<span> <input type="text" id="payRule"
								name="ho.payRule" placeholder="付款规则"> <span
								>&nbsp;</span>
							</span>
						</div>
					</div>
					<div class="col-sm-4 ">
						<div class="form-wrapper">
							<div class="row form-area boxed-red">
							<span> <textarea cols="27" rows="2" id="facility"
									name="ho.facility" placeholder="设施描述"></textarea> <span
								>&nbsp;</span>
							</span>
							<div class="input_styled inlinelist" id="bill">
								<div class="rowRadio">
									<label><input checked="checked" name="ho.bill"
										type="radio" value="1" />提供发票</label>
								</div>
								<div class="rowRadio">
									<label><input  name="ho.bill"
										type="radio" value="0" />不提供发票</label>
								</div>
								</div>
							
							
							<div class="field_select">
								最少天数 <select class="select_styled" name="ho.minDay" id="minDay">
									<option value="1">1天</option>
									<option value="2">2天</option>
									<option value="3">3天</option>
									<option value="4">4天</option>
									<option value="5">5天</option>
									<option value="6">6天</option>
									<option value="7">7天</option>
									<option value="8">8天</option>
									<option value="9">9天</option>
									<option value="10">10天</option>
									<option value="11">11天</option>
									<option value="12">12天</option>
									<option value="13">13天</option>
									<option value="14">14天</option>
									<option value="15">15天</option>
									</select>
							</div>
							<div class="field_select">
								最多天数 <select class="select_styled" name="ho.maxDay" id="maxDay">
									<option value="1">1天</option>
									<option value="2">2天</option>
									<option value="3">3天</option>
									<option value="4">4天</option>
									<option value="5">5天</option>
									<option value="6">6天</option>
									<option value="7">7天</option>
									<option value="8">8天</option>
									<option value="9">9天</option>
									<option value="10">10天</option>
									<option value="11">11天</option>
									<option value="12">12天</option>
									<option value="13">13天</option>
									<option value="14">14天</option>
									<option value="15">15天</option>
									</select>
							</div>
							</div>
							<div class="row button-area boxed-turquoise">
								<input type="text" id="verification" placeholder="请输入验证码">
								<a href="javascript:;" id="verify" onclick="document.getElementById('image').src = 'verification.action?time='+Math.random();"> 
								<img id="ver-pic" src="verification.action" alt="验证码挂了" /> </a><br>

								<a onclick="newRoom()" class="btn btn-blue but"><span>提交</span></a> <span
									class="btn btn-blue but"><input type="reset"
									name="reset-button" /></span>
							</div>
						</div>
					</div>
				</div>
				<input type="submit" value="提交" />

</form>
</body>
</html>