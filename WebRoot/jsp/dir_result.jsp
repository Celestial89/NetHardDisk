<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

{"dirInfo": 
[

<s:iterator id="element" value="dirInfo" status="status" >
   {
      "name":"<s:property value='#element.dir' />", 
      "path":"<s:property value='#element.path' />",
      "time":"<s:property value='#element.time' />",
      "size":"<s:property value='#element.fileSize' />",
      "count":"<s:property value='#element.count' />"
   }
   <s:if test="#status.count < dirInfo.size">,</s:if>   

</s:iterator>
] }

