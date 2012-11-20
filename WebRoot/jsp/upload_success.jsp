<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	
	</head>

	<body>
		<form action="main_page.action" method="post" name="mainForm">
			<input type="hidden" value="<s:property value='uploadPath'/>" name="current_path" />
		</form>
	<script type="text/javascript">
    	alert("上传成功!");
    	mainForm.submit();
	</script>

	</body>
</html>

