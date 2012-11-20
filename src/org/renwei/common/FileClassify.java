package org.renwei.common;

public class FileClassify {
	
	private String type;
	
	public String Classify(String fileName)
	{
		String[] pic = {"bmp","gif","jpg","png","svg","psd","pcx"};
		String[] music = {"mp1","mp2","mp3","wma","wav","ogg","m4a","aac","aa","ape","m3u","flac","cue","cda","krc","mpc","aif","mid","midi","ac3","dts"};
		String[] doc = {"zip","rar","tar","lzh","arc","rtf","wri","doc","docx","txt","pdf","ppt","xls"};
		String[] video = {"asf","wm","wmp","wmv","wma","ram","rm","rmvb","rpm","scm","mov","3gp","amv","avi","dsv","mp4","pmp","swf","flv"};
		String exname = null;
		String type = "unknown"; 
		
		int index = fileName.lastIndexOf(".");
		exname=fileName.substring(++index);
		for(String ex : pic)
		{
			if(ex.equalsIgnoreCase(exname))
			{
				type = "pic";
				return  type;
			}
		}
		for(String ex : music)
		{
			if(ex.equalsIgnoreCase(exname))
			{
				type = "music";
				return  type;
			}
		}
		for(String ex : doc)
		{
			if(ex.equalsIgnoreCase(exname))
			{
				type = "doc";
				return  type;
			}
		}
		for(String ex : video)
		{
			if(ex.equalsIgnoreCase(exname))
			{
				type = "video";
				return  type;
			}
		}
		return type;
	}
}
