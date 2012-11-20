<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

{"files": 
[
<s:iterator id="element" value="files" status="status" >
   {
      "id":"<s:property value='#element.id' />",
      "name":"<s:property value='#element.name' />", 
      "path":"<s:property value='#element.path' />",
      "time":"<s:property value='#element.time' />",
      "size":"<s:property value='#element.size' />"
   }
   <s:if test="#status.count < files.size">,</s:if>   

</s:iterator>
] }

