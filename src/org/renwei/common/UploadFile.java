package org.renwei.common;

import java.io.File;
import java.util.List;

public class UploadFile
{

	// ��װ�ϴ��ļ��������
	private List<File> upload;
	// ��װ�ϴ��ļ����͵�����
	private List<String> uploadContentType;
	// ��װ�ϴ��ļ���������
	private List<String> uploadFileName;
	
	// ��װ�ϴ��ļ������ڷ�������·����ͨ����������
	private String uploadPath;
	
	private UserInfo userInfo;
	
	public UserInfo getUserInfo()
	{
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
	}
	public String getUploadPath()
	{
		return uploadPath;
	}
	public void setUploadPath(String uploadPath)
	{
		this.uploadPath = uploadPath;
	}
	public List<File> getUpload()
	{
		return upload;
	}
	public void setUpload(List<File> upload)
	{
		this.upload = upload;
	}
	public List<String> getUploadContentType()
	{
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public List<String> getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
}
