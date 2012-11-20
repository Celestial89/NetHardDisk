package org.renwei.action;

import org.renwei.dao.DirectoryDaoImpl;
import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.DirectoryDao;
import org.renwei.dao.interfaces.FileDao;

public class ShareAction extends BaseAction
{
	private static final long serialVersionUID = 6971910700244410014L;
	private String[] paths;
	private String currentPath;
    private FileDao fileDao;
    private DirectoryDao directoryDao;
	
	public ShareAction()
	{
		fileDao = new FileDaoImpl();
		directoryDao = new DirectoryDaoImpl();
	}
	
	public void setCurrentPath(String currentPath)
	{
		this.currentPath = currentPath;
	}
	
	public String getCurrentPath()
	{
		return currentPath;
	}

	public void setPaths(String paths)
	{
		this.paths = paths.split(";");
	}

	public String execute() throws Exception
	{
		try
		{
			for (String path : paths)
			{
				if (path != null)
				{
					String fileName = path.substring(currentPath.length(), path.length());
					fileDao.shareFile(userInfo, fileName, currentPath);			
				}
			}		
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
}