<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
	<head>
		<title>网盘</title>
		    <script src="javascript/prototype.js" type="text/javascript"></script>
            <script type="text/javascript" src="javascript/common.js"></script>
			<link type="text/css" rel="stylesheet" href="css/style.css" />
	</head>

	<body>
	  <center>
		<form action="upload_page.action" method="post" name="uploadForm">
			<input type="hidden" value="" name="uploadPath" />
		</form>
		<iframe src="" id="downloadFrame" style="visibility: hidden;height: 0px;width: 0px"></iframe>
		<div>
            <img src="images/top.gif">
        </div>
		<table width="100%" align="center">
			<tr>
				<td>
					<div align="center" style="height: 30px;">
						<c:choose>
							<c:when test="${param.current_path == null}">
								<input type="hidden" value="/" name="txt_path"  />
							</c:when>
							<c:otherwise>						
								<input type="hidden" value="${param.current_path}" name="txt_path" />
							</c:otherwise>
						</c:choose>

                        <br>
						<input id="btn_previous" value="我的分享" type="button"
							onClick="myShare()" />
						&nbsp;&nbsp;
						<input id="btn_upload" value="公共分享" type="button"
							onclick="commonShare()" />
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div align="center" style="height: 30px;">
						<c:choose>
							<c:when test="${param.current_path == null}">
								<input type="hidden" value="/" name="txt_path"  />
							</c:when>
							<c:otherwise>						
								<input type="hidden" value="${param.current_path}" name="txt_path" />
							</c:otherwise>
						</c:choose>
                                                               
                        <br>
                         <input id="btn_previous" value="返回我的网盘" type="button"
							onClick="goMain()" />
						&nbsp;&nbsp;
                                                           文件分类：
						<input id="btn_showDoc" value="文档" type="button"
							onClick="showShareDoc()" />
						&nbsp;&nbsp;
						<input id="btn_showPic" value="图片" type="button"
							onclick="showSharePic()" />
						&nbsp;&nbsp;
						<input id="btn_showVideo" value="视频" type="button"
							onclick="showShareVideo()" />
						&nbsp;&nbsp;
						<input id="btn_showMusic" value="音乐" type="button"
							onClick="showShareMusic()" />
						&nbsp;&nbsp;
						<input id="btn_showUnknown" value="未分类" type="button"
							onClick="showShareUnknown()" />
						&nbsp;&nbsp;
						<input id="btn_returnmain" value="显示全部" type="button"
							onClick="showAll()" />
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			
			<tr>
			    <td>
					<div id="div_current_path"
						style="height: 30px;  margin-top: 10px; margin-left: 250px;">
                    </div>
                </td>
			</tr>

			<tr>
				<td>
					<table id="tbl_list" width="700" border="0" cellspacing="0" align="center"
						cellpadding="0">
						<tr bgcolor="#DDDDDD" style="font: bold">
							<td width="50%" >
								<input type="checkbox" id="checkbox_head" onclick="checkAll(this);"/> &nbsp;目录
							</td>
							<td width="30%">
								上传时间
							</td>
							<td style="text-align: right">
								大小
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</center>
		
		<script type="text/javascript">
            myShare();
        </script>
	</body>
</html>