<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
        <script type="text/javascript" src="javascript/show.js"></script>
		<title>用户登录</title>
		<sx:head parseContent="true"/>
		<script type="text/javascript">		 
		    function refresh()
            {
                var img = document.getElementById("img_validation_code")
                img.src = "validate_code.action?" + Math.random() ;            
            }
        </script>		
	</head>
	<body>	  
		<center>
		    <div style="margin-top: 20px; margin-left: 20px; font-size: 20px; height: 20px">
                <FONT style="FONT-SIZE: 20pt; FILTER: glow(color:0000CC 22DD22,strength=50); WIDTH: 100%; COLOR: white; LINE-HEIGHT: 350%; FONT-FAMILY: 华文彩云; HEIGHT: 110px">
                                                          圣灵仙居网络硬盘
                </FONT> 
			</div> 
            <br>
			<table width="1000px" height="300">
			    <tr>
			        <td width="600px">
			            <div  id="slideshow">
                            <img id="list" src="images/list.jpg">
                        </div>
                        <div  id="spiclist">
                            <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td><a href="1"><img src="images/1s.jpg"></a></td>
                                <td><a href="2"><img src="images/2h.jpg"></a></td>
                                <td><a href="3"><img src="images/3h.jpg"></a></td>
                                <td><a href="4"><img src="images/4h.jpg"></a></td>
                            </tr>
                            </table>
                        </div>
			        </td>
			        <td width="50px">
			        </td>
			        <td>
			          <br><br><br><br><br>
			            <sx:tabbedpanel id="tab" cssStyle="width:300px height:500px" doLayout="true" selectedTab="login">
			                <sx:div id="login" label="用户登录" theme="ajax">
			                  <center>
			                    <br>
			                                                      如果您还没注册，单击此处<a href="register_page.action">注册</a>
			                    <FONT color="red"><s:actionerror/></FONT>
			                    <s:form action="login" validate="true">
				                    <s:textfield label="用户名"  cssClass="input_list" name="userName" value="renwei" />
				                    <s:password label="密码" name="password" cssClass="input_list" />
				                    <s:textfield label="验证码" name="validationCode" cssClass="input_list" />
				                    <s:submit value="登录" />
			                    </s:form> 
			
			                                                      用户验证码：<img id="img_validation_code" src="validate_code.action"/>
			                    <a href="#" onClick="refresh()">重新获得验证码</a>	
			                  </center>
			                </sx:div>
			                <sx:div id="read" label="网盘介绍" theme="ajax">
			                    <img src="images/read.jpg">
			                </sx:div>
			            </sx:tabbedpanel>
			        </td>
			    </tr>
			</table>
		</center>
	</body>
</html>
