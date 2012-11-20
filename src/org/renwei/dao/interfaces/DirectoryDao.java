package org.renwei.dao.interfaces;

import java.util.List;
import org.renwei.common.*;
import org.renwei.model.*;

public interface DirectoryDao
{
    public void saveDir(Directory directory);
	public void deleteDir(UserInfo userInfo, String path);	
	public String addDirectory(UserInfo userInfo) throws Exception;
	public List<DirInfo> getDirInfo(String user, String parentPath);
}
