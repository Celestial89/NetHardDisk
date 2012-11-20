package org.renwei.common;

import java.io.File;

public class DeleteFile
{
	public static void deleteAny(String path)
	{
		File file = new File(path);
		if (file.exists())
		{
			if (file.isDirectory())
			{
				File[] files = file.listFiles();
				for (File myFile : files)
				{
					if (myFile.isDirectory())
						deleteAny(myFile.getPath());
					else
						myFile.delete();
				}
			}
			file.delete();
		}
	}
}
