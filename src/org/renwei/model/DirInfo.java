package org.renwei.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirInfo
{
	private String user;
	private String path;
	private String parentPath;
	private String dir;
	private Date createTime;
	private String time;
	private Integer count;
	private Long fileSize;
	
	public String getTime()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		return dateFormat.format(createTime);
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPath()
	{
		return path;
	}

	public void setPATH(String path)
	{
		this.path = path;
	}

	public String getParentPath()
	{
		return parentPath;
	}

	public void setParentPath(String parentPath)
	{
		this.parentPath = parentPath;
	}

	public String getDir()
	{
		return dir;
	}

	public void setDIR(String dir)
	{
		this.dir = dir;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCREATETIME(Date createTime)
	{
		this.createTime = createTime;
	}

	public long getCount()
	{
		return count;
	}

	public void setCOUNT(Integer count)
	{
		this.count = count;
	}

	public long getFileSize()
	{
		return fileSize;
	}

	public void setFILESIZE(Long fileSize)
	{
		this.fileSize = fileSize;
	}

}
