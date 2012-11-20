package org.renwei.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class File
{
	private Long id;
	private String userName;
	private String account;
	private String name;
	private String path;
	private Long size;
	private Date uploadTime;
	private String time;
	private User user;
	private Integer version;
	private String type;
	private String isShare;

	public String getTime()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		return dateFormat.format(uploadTime);
	}
	
	public String getIsShare()
	{
		return isShare;
	}
	
	public void setIsShare(String isShare)
	{
		this.isShare = isShare;
	}
	
	public String getAccount()
	{
		return account;
	}
	
	public void setAccount(String account)
	{
		this.account = account;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public Integer getVersion()
	{
		return version;
	}
	
	public void setVersion(Integer version)
	{
		this.version = version;
	}
	
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	private void setId(Long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public Long getSize()
	{
		return size;
	}

	public void setSize(Long size)
	{
		this.size = size;
	}

	public Date getUploadTime()
	{
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime)
	{
		this.uploadTime = uploadTime;
	}
}
