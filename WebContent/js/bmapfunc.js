/**
 * 函数，放在页面开头加载
 */
// 根据合法的地址字符串展示标注
function showSpot(spot) {
	myGeo.getPoint(spot.address, function(point){      
          if (point) {      
              map.centerAndZoom(point, 16);      
              var marker = new BMap.Marker(point);
              map.addOverlay(marker);
              marker.addEventListener("click", function(){    
            	  var opts = {    
            			  width : 250,     // 信息窗口宽度    
            			  height: 100,     // 信息窗口高度    
            			  title : spot.name,  // 信息窗口标题   
            			  offset : new BMap.Size(-3,-18)	
            			 }    
            	 var infoWindow = new BMap.InfoWindow("价格：" + spot.dayPrice + "<br>地址：" + spot.address, opts);  // 创建信息窗口对象    
            	 map.openInfoWindow(infoWindow, point);      // 打开信息窗口
             });
          }      
      }, "");
}