package org.renwei.action;

import org.renwei.common.*;
import java.io.File;


public class DownloadMoreFileAction extends BaseAction
{
	private static final long serialVersionUID = 7856188677516571050L;
	private String[] names;
	private String path;

	public void setNames(String names)
	{
		this.names = names.split(";");
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
			if (path != null && names != null)
			{
				response.setContentType("application/octet-stream");

				if (path.equals("/"))
				{
					response.addHeader("Content-Disposition",
							"attachment;filename="
									+ java.net.URLEncoder.encode("圣灵仙居网络硬盘.zip",
											"utf-8"));
				}

				else if (path.length() > 0)
				{
					String[] array = path.split("/");

					if (array.length > 1) // 数组长度至少是2
					{
						String zipName = array[array.length - 1] + ".zip";
						response.addHeader("Content-Disposition",
								"attachment;filename=" + zipName);

					}
					else
						return null;
				}
				else
					return null;

				for (int i = 0; i < names.length; i++)
				{
					String name = names[i];
					if (!name.equals(""))
					{
						String filename = userInfo.getUserRoot()
								+ (File.separator.equals("\\") ? path
										.replaceAll("/", "\\\\") : path) + name;
						filename = java.net.URLDecoder
								.decode(filename, "UTF-8");

						names[i] = filename;
					}
				}
				Zip.compress(response.getOutputStream(), names);

				
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
}