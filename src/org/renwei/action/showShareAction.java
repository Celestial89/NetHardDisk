package org.renwei.action;

import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;
import org.renwei.model.*;

import java.util.*;

public class showShareAction extends BaseAction
{
	private static final long serialVersionUID = 896506998111516734L;
	private List<File> files;
    private String path;
    private FileDao fileDao;
    private String type;
    
    public showShareAction()
	{
		fileDao = new FileDaoImpl();
	}
    
    public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
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
			if(type.equals("my"))
			{
				files = fileDao.myShareFiles(userInfo.getCookieUser(), path);
			    return SUCCESS;
			}
			else
			{
				files = fileDao.commonShareFiles(path);
			    return SUCCESS;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}