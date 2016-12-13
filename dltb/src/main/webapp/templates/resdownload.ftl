<html>
<head>
<title>资源下载</title>
<script src="${base_addr}/static/js/jquery-2.2.0.min.js"></script>
</head>
<style> 
body{ text-align:center} 
#main{ margin:0 auto; width:400px; height:100px; border:1px solid #F00} 
</style> 
<body>
<div id="main">
	<input type="button" id="ida"  onclick="javascript:downloadRes();" value="点击下载《2600款促销水印》" />
</div>

</body>
<script>
var baseAddr = "${base_addr}";//${base_addr}
	function downloadRes(){
		$('#ida').attr("disabled",true);
		var productFormSerialize = $('#productForm').serialize();
		$.ajax({
			url: baseAddr+'/download/do/${resCode}' ,
	        data: '',
	        async: false,
	        dataType: 'json',
	        success: function (data) {
	        	if(data != null && data != ''){
	        		window.open(data);
	        	}
	        },
	        error: function (data) {
	            //alert("失败----"+data);
	        }
	    });
		//$('#ida').attr("disabled",false);
	}
</script>
</html>
