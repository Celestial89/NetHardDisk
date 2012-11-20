package org.renwei.action;

import org.renwei.dao.DirectoryDaoImpl;
import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.DirectoryDao;
import org.renwei.dao.interfaces.FileDao;

public class DeleteAction extends BaseAction
{
	private static final long serialVersionUID = 6971910700244410013L;
	private String[] paths;
	private String currentPath;
    private FileDao fileDao;
    private DirectoryDao directoryDao;
	
	public DeleteAction()
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
					// É¾³ýÄ¿Â¼
					if (path.charAt(path.length() - 1) == '/')
					{												
						directoryDao.deleteDir(userInfo, path);
						fileDao.deleteFiles(userInfo, path);
						org.renwei.common.DeleteFile.deleteAny(userInfo.getAbsolutePath(path));
					}
					//  É¾³ýÎÄ¼þ
					else
					{
						String fileName = path.substring(currentPath.length(), path.length());
						fileDao.deleteFile(userInfo, fileName, currentPath);
						org.renwei.common.DeleteFile.deleteAny(userInfo.getAbsolutePath(path));
					}					
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