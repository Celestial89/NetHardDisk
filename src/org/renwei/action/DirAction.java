package org.renwei.action;

import com.opensymphony.xwork2.ModelDriven;

import org.renwei.common.UserInfo;
import org.renwei.dao.DirectoryDaoImpl;
import org.renwei.dao.interfaces.DirectoryDao;
import org.renwei.model.DirInfo;

import java.util.*;

public class DirAction extends BaseAction implements ModelDriven<UserInfo>
{
	private static final long serialVersionUID = 1600763784051327534L;
	private List<DirInfo> dirInfo;
	private DirectoryDao directoryDao;
	
	public DirAction()
	{
		directoryDao = new DirectoryDaoImpl();
	}

	public List<DirInfo> getDirInfo()
	{
		return dirInfo;
	}

	public void setDirInfo(List<DirInfo> dirInfo)
	{
		this.dirInfo = dirInfo;
	}

	public UserInfo getModel()
	{
		return userInfo;
	}

	public String execute() throws Exception
	{
		try
		{
			dirInfo = directoryDao.getDirInfo(userInfo.getCookieUser(),userInfo.getParentPath());
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}