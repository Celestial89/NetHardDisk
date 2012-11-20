package org.renwei.action;

import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;
import org.renwei.model.*;

import java.util.*;

public class ShowClassifyAction extends BaseAction
{
	private static final long serialVersionUID = 895506998111516764L;
	private List<File> files;
    private String path;
    private FileDao fileDao;
    private String type;
    private String share;
    
    public ShowClassifyAction()
	{
		fileDao = new FileDaoImpl();
	}
    
    public String getShare()
	{
		return share;
	}

	public void setShare(String share)
	{
		this.share = share;
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
			files = fileDao.getClassifyFiles(userInfo.getCookieUser(), path, type);
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}