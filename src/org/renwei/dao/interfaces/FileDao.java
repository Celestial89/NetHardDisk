package org.renwei.dao.interfaces;

import org.renwei.model.File;

import java.math.BigDecimal;
import java.util.*;

import org.renwei.model.*;
import org.renwei.common.*;

public interface FileDao
{
    public void save(File file);
    public void deleteFile(UserInfo userInfo, String fileName, String path);
	public void deleteFiles(UserInfo userInfo, String path);
	public List<org.renwei.model.File> commonShareFiles(String path);
	public List<org.renwei.model.File> myShareFiles(String username, String path);
	public void shareFile(UserInfo userInfo, String fileName, String path);
	public void addFiles(UploadFile uploadFile) throws Exception;
	public List<org.renwei.model.File> getFiles(String username, String path);
	public List<org.renwei.model.File> getClassifyFiles(String username, String path, String type);
	public long getUserDiskSize(String username);
	public org.renwei.model.File getFile(String id);
} 
