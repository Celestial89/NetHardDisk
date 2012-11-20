package org.renwei.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.renwei.dao.FileDaoImpl;
import org.renwei.dao.interfaces.FileDao;

public class DownloadFileAction extends BaseAction
{
	private static final long serialVersionUID = 4080609004037068292L;
	private FileDao fileDao;
	private String name;
	private String path;
	private String id;
	private String username;
	
	public DownloadFileAction()
	{
		fileDao = new FileDaoImpl();
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String execute() throws Exception
	{
		try
		{
			if (id != null && name != null)
			{
				org.renwei.model.File fileInf =  fileDao.getFile(id);
				path = fileInf.getPath();
				username = fileInf.getAccount();
				String filename = userInfo.getRoot() + username
						+ (File.separator.equals("\\") ? path.replaceAll("/",
								"\\\\") : path) + name;
				filename = java.net.URLDecoder.decode(filename, "UTF-8");
				File file = new File(filename);
				if (file.exists())
				{
					response.setContentType("application/octet-stream");
					response.addHeader("Content-Disposition",
							"attachment;filename=" + name);
					response.addHeader("Content-Length", String.valueOf(file
							.length()));
					InputStream is = new FileInputStream(filename);
					OutputStream os = response.getOutputStream();
					byte[] buffer = new byte[8192];
					int count = 0;
					while ((count = is.read(buffer)) > 0)
					{
						os.write(buffer, 0, count);

					}

					os.close();
					is.close();
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