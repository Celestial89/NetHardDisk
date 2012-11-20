<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
  <html>
 <head>
 <title>上传文件</title>
  <script language="javascript">

function addFile()
{
        var uploadHTML = document.createElement( "<input type='file'  name='upload'/>");
        document.getElementById("files").appendChild(uploadHTML);
        uploadHTML = document.createElement( "<p/>");
        document.getElementById("files").appendChild(uploadHTML);
}
</script>
 </head>   
    <body> 
    <center>
    <div>
        <img src="images/top.gif">
    </div>
    <s:fielderror/>       
    
    <input type="button" onclick="addFile();" value="添加文件" />
   
    <s:form  action="upload" enctype="multipart/form-data">
        <input type="hidden" name="uploadPath" value="<s:property  value='#parameters.uploadPath'/>" />
 
		 <span id="files"> <input type='file' name='upload' />
             <p/>
         </span>           
         <input type="submit" value="上传" style="width:50px"/>
    </s:form>
    </center>
    </body> 
  </html> 