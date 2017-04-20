/**
 * 用于对百度地图的展示
 * 2015/07/23 first version
 * 2015/07/24 增加标注弹出窗口功能
 * 2015/08/04 在特定应用上设置了css
 */
var map = new BMap.Map("container");          // 创建地图实例  
var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
map.addControl(new BMap.NavigationControl());    // 增加控件
map.addControl(new BMap.ScaleControl());    
map.addControl(new BMap.OverviewMapControl());  

// 创建地址解析器实例     
var myGeo = new BMap.Geocoder();     

// 设置container样式
$("#container").css("height","20rem");
$("#container").css("margin-bottom","1rem");

