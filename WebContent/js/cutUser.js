/**
 * 
 */
/**
 * 
 */
jQuery(function($){

    var jcrop_api;
    $('#target').Jcrop({
      onChange:   showCoords,
      onSelect:   showCoords,
      aspectRatio:1/1
    },function(){
      jcrop_api = this;
    });

    $('#coords').on('change','input',function(e){
      var x1 = $('#x1').val(),
          x2 = $('#x2').val(),
          y1 = $('#y1').val(),
          y2 = $('#y2').val();
      jcrop_api.setSelect([x1,y1,x2,y2]);
    });
});
	
  // Simple event handler, called from onChange and onSelect
  // event handlers, as per the Jcrop invocation above
function showCoords(c)
  {
    $('#x1').val(c.x);
	$('#y1').val(c.y);
	$('#x2').val(c.x2);
	$('#y2').val(c.y2);
	$('#w').val(c.w);
	$('#h').val(c.h);
  };
  function submit(pos){
		if($("#h").val()){//输入高度不为空则都不为空
			
			$(pos).parent().submit();
			
		}
		else{//未选择图片
			$("#error").html("未选择裁剪区域");
		}
	}