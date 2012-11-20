package org.renwei.model;
import java.text.SimpleDateFormat;
import java.util.*;
public class Directory
{
    private Long id;
    private String userName;
    private String path;
    private String parentPath;
    private String dir;
    private Date createTime;
    private User user;
    private Integer version;
    
    public Integer getVersion()
	{
		return version;
	}
	
	public void setVersion(Integer version)
	{
		this.version = version;
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
		userName = this.user.getUserName();
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.user.setUserName(userName);
	}

	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user=user;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
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

	public void setDir(String dir)
	{
		this.dir = dir;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}
