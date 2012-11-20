package org.renwei.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.renwei.dao.interfaces.DirectoryDao;
import org.renwei.dao.interfaces.UserDao;
import org.renwei.model.DirInfo;
import org.renwei.model.Directory;
import org.renwei.model.User;
import org.renwei.common.*;

public class DirectoryDaoImpl implements DirectoryDao
{
	private static Configuration config;
	private static SessionFactory sessionFactory;
	private UserDao userDao = new UserDaoImpl();
	static{
		try{
			config = new Configuration();
	        config.configure();       //加载hibernate.cfg.xml文件中配置的信息
	        sessionFactory = config.buildSessionFactory();
	        }
		catch(RuntimeException e){
	        	e.printStackTrace();throw e;
	        	}
		}
	
	public void deleteDir(UserInfo userInfo, String path)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = userDao.searchUserByName(userInfo.getCookieUser());
			session.createSQLQuery("delete from Directorys where userName = " + user.getId() +" and path = '" + path + "'").executeUpdate();
			session.createSQLQuery("delete from Directorys where userName = " + user.getId() +" and parentPath like '" + path + "%'").executeUpdate();
			tx.commit();
		}
		catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} 
		finally {
			session.close();
		}
	}

	public void saveDir(Directory directory)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
			tx = session.beginTransaction();
			session.save(directory);
			tx.commit();
		}
		catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} 
		finally {
			session.close();
		}
	}
	
	public String addDirectory(UserInfo userInfo) throws Exception
	{

		String currentPath = userInfo.getUserRoot() + userInfo.getParentPath()
				+ userInfo.getDir() + File.separator;
		currentPath = File.separator.equals("\\") ? currentPath.replaceAll("/",
				"\\\\") : currentPath;
		
		UserDao userDao = new UserDaoImpl();
	    User user = userDao.searchUserByName(userInfo.getCookieUser());
		Directory directory = new Directory();
		directory.setUser(user);
		directory.setUserName(user.getUserName());
		directory.setDir(userInfo.getDir());
		directory.setParentPath(userInfo.getParentPath());
		directory.setPath(userInfo.getParentPath() + userInfo.getDir() + "/");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		userInfo.setTime(dateFormat.format(date));
 
		directory.setCreateTime(date);
		saveDir(directory);
		File dir = new File(currentPath);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		return "成功建立目录";

	}
	
	public List<DirInfo> getDirInfo(String userName, String parentPath)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(userName);
		    List<DirInfo> directories = session.createSQLQuery("select d.path, d.dir, d.create_Time as createTime, sum(f.filesize) as fileSize, count(f.filesize) as count from Directorys d left join Files f on f.path = d.path where d.userName = " + user.getId() +" and d.parentPath = '" + parentPath + "' group by d.path, d.dir, d.create_Time")
		                                    .addScalar("PATH", Hibernate.STRING)
		                                    .addScalar("DIR", Hibernate.STRING)
		                                    .addScalar("CREATETIME", Hibernate.DATE)
		                                    .addScalar("COUNT", Hibernate.INTEGER)
		                                    .addScalar("FILESIZE", Hibernate.LONG)
		                                    .setResultTransformer(Transformers.aliasToBean(DirInfo.class)).list();
		    tx.commit();
			return directories;
		}
		catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} 
		finally {
			session.close();
		}
	}

}
