package org.renwei.action;

import org.renwei.common.*;
import org.renwei.dao.DirectoryDaoImpl;
import org.renwei.dao.interfaces.DirectoryDao;

import com.opensymphony.xwork2.ModelDriven;

public class CreateDirAction extends BaseAction implements ModelDriven<UserInfo>
{
	private static final long serialVersionUID = 1693905284344090243L;
	private DirectoryDao directoryDao;
	
	public CreateDirAction()
	{
		directoryDao = new DirectoryDaoImpl();
	}
	
	public String execute() throws Exception
	{
		try
		{
			setResult(directoryDao.addDirectory(userInfo));
			return SUCCESS;
		}
		catch (Exception e)
		{
			setResult("½¨Á¢Ä¿Â¼Ê§°Ü");
		}
		return ERROR;
	}

	public UserInfo getModel() {
		return userInfo;
	}

}