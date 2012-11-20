package org.renwei.action;

import com.opensymphony.xwork2.ModelDriven;
import org.renwei.common.UploadFile;
import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;

public class UploadAction extends BaseAction implements ModelDriven<UploadFile>
{
	private static final long serialVersionUID = 802679181390510207L;
	private UploadFile uploadFile = new UploadFile();
	private FileDao fileDao;
	
	public UploadAction()
	{
		fileDao = new FileDaoImpl();
	}
    
	public UploadFile getModel()
	{		
		return uploadFile;
	}

	public String execute() throws Exception
	{	
		try
		{
			uploadFile.setUserInfo(userInfo);
			fileDao.addFiles(uploadFile);
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}