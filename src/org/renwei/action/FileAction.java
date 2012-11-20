package org.renwei.action;

import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;
import org.renwei.model.*;

import java.util.*;

public class FileAction extends BaseAction
{
	private static final long serialVersionUID = 895506998111516734L;
	private List<File> files;
    private String path;
    private FileDao fileDao;
    
    public FileAction()
	{
		fileDao = new FileDaoImpl();
	}
    
	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public List<File> getFiles()
	{
		return files;
	}

	public void setFiles(List<File> files)
	{
		this.files = files;
	}

	public String execute() throws Exception
	{
		try
		{
		    files = fileDao.getFiles(userInfo.getCookieUser(), path);
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}