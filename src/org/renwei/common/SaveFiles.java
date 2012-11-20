package org.renwei.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SaveFiles {
	public static String saveFile(File uploadFile, String fn) throws Exception
	{
		File file = new File(fn);
		int index = 0;
		String filename = file.getName();
		while (file.exists())
		{
			int extIndex = filename.lastIndexOf(".");

			if (extIndex > 0)
				fn = filename.substring(0, extIndex) + "("
						+ String.valueOf(index) + ")"
						+ filename.substring(extIndex);
			else
				fn = filename + "(" + String.valueOf(index) + ")";
			fn = file.getPath().substring(0,
					file.getPath().lastIndexOf(file.getName()))
					+ fn;
			file = new File(fn);
			index++;
		}
		FileOutputStream fos = new FileOutputStream(fn);
		InputStream is = new java.io.FileInputStream(uploadFile);
		byte[] buffer = new byte[8192];
		int count = 0;
		while ((count = is.read(buffer)) > 0)
		{
			fos.write(buffer, 0, count);
		}
		fos.close();
		is.close();
		return file.getName();
	}
}
