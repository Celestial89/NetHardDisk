<%@ page pageEncoding="UTF-8"%>
<html>
	<head>
		<title>建立目录</title>
		<script src="javascript/prototype.js" type="text/javascript"></script>
		<script type="text/javascript" src="javascript/common.js"></script>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
	</head>

	<body>

		<div id="div" style="left: 10px; top: 10px;position: absolute;" >
			请输入文件夹名称：
			<input type="text" name="dir" />
			<input type="hidden" name="parentPath" id="parentPath" /> 
			<p/>
			      当前路径：<label id = "lbl_path"></label>
			<p/>
			<input type="button" value="确定" onclick="jsonCreateDir()" />
			
		</div>
		
	<script type="text/javascript">
	    var path = window.dialogArguments.path;
	    var parentWindow = window.dialogArguments.window;;
	    var lbl_path = document.getElementById("lbl_path");
	    var parentPath = document.getElementById("parentPath");
	    lbl_path.innerHTML=path;	
	    parentPath.value = path;    	    
	</script>
		
	</body>
</html>

