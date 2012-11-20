package org.renwei.action;

import java.util.List;

import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;
import org.renwei.model.DirInfo;

public class DiskAction extends BaseAction
{
	private static final long serialVersionUID = 4039529898768652305L;
	private long diskSize;
	private FileDao fileDao;
	
	public DiskAction()
	{
		fileDao = new FileDaoImpl();
	}
	
	public long getDiskSize()
	{
		return diskSize;
	}
	
	public String execute() throws Exception
	{
		try
		{
			diskSize = fileDao.getUserDiskSize(userInfo.getCookieUser());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}