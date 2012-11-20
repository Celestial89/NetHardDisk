package org.renwei.dao;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.renwei.dao.interfaces.*;
import org.renwei.model.User;
import org.renwei.common.*;

public class FileDaoImpl implements FileDao
{
	private static Configuration config;
	private static SessionFactory sessionFactory;
	
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

	public void deleteFiles(UserInfo userInfo, String path)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(userInfo.getCookieUser());
			session.createSQLQuery("delete from Files where userName = " + user.getId()+ " and path like '" + path +"%'").executeUpdate();
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
	
	public org.renwei.model.File getFile(String id)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			org.renwei.model.File file = (org.renwei.model.File)session.createQuery("from File where id = " + id).uniqueResult();
			tx.commit();
			return file;
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

	public void deleteFile(UserInfo userInfo, String fileName, String path)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(userInfo.getCookieUser());
			session.createQuery("delete from File where userName = " + user.getId() +" and name = '" + fileName + "' and path = '" + path + "'").executeUpdate();
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
	
	public void shareFile(UserInfo userInfo, String fileName, String path)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(userInfo.getCookieUser());
			session.createQuery("update File set isShare = 'yes' where userName = " + user.getId() +" and name = '" + fileName + "' and path = '" + path + "'").executeUpdate();
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

	public void save(org.renwei.model.File file)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
			tx = session.beginTransaction();
			session.save(file);
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
	
	public void addFiles(UploadFile uploadFile) throws Exception
	{
		int i = 0;	
		SaveFiles saveFiles = new SaveFiles();
		FileClassify classify = new FileClassify();
		for(File f: uploadFile.getUpload())
		{			
			String currentPath = uploadFile.getUserInfo().getUserRoot()
					+ (File.separator.equals("\\") ? uploadFile.getUploadPath().replaceAll("/",
							"\\\\") : uploadFile.getUploadPath());
			String fn = SaveFiles.saveFile(f, currentPath + uploadFile.getUploadFileName().get(i));
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(uploadFile.getUserInfo().getCookieUser());
			org.renwei.model.File file = new org.renwei.model.File();
			file.setUserName(uploadFile.getUserInfo().getCookieUser());
			file.setAccount(uploadFile.getUserInfo().getCookieUser());
			file.setName(new File(fn).getName());
			file.setPath(uploadFile.getUploadPath());
			file.setSize(f.length());
			file.setUploadTime(new java.util.Date());
			file.setUser(user);
			file.setIsShare("no");
			file.setType(classify.Classify(new File(fn).getName()));
			save(file);			
			i++;
		}
	}
	
	public List<org.renwei.model.File> getFiles(String username, String path)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(username);
			List<org.renwei.model.File> files = session.createQuery("from File where userName = " + user.getId() +" and path= '" + path + "' order by uploadTime").list();
			tx.commit();
			return files;
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
	
	public List<org.renwei.model.File> myShareFiles(String username, String path)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(username);
			List<org.renwei.model.File> files = session.createQuery("from File where userName = " + user.getId() +" and isShare = 'yes' order by uploadTime").list();
			tx.commit();
			return files;
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
	
	public List<org.renwei.model.File> commonShareFiles(String path)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<org.renwei.model.File> files = session.createQuery("from File where isShare = 'yes' order by uploadTime").list();
			tx.commit();
			return files;
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
	
	public List<org.renwei.model.File> getClassifyFiles(String username, String path, String type)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(username);
		    List<org.renwei.model.File> files = null;
		    if(path.equals("我的分享"))
		    	files = session.createQuery("from File where userName = " + user.getId() +" and isShare = 'yes' and type = '" + type + "'order by uploadTime").list();
		    else if(path.equals("公共分享"))
		    	files = session.createQuery("from File where isShare = 'yes' and type = '" + type + "'order by uploadTime").list();
		    else
		    	files = session.createQuery("from File where userName = " + user.getId() +" and type = '" + type +"' order by uploadTime").list();
		    tx.commit();
			return files;
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
	
	public long getUserDiskSize(String username)
	{
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDao userDao = new UserDaoImpl();
		    User user = userDao.searchUserByName(username);
		    List<Long> fileSize = session.createQuery("select sum(f.size) as diskSize from File f where f.user = " + user.getId()).list();
			tx.commit();
			if(fileSize.size() > 0)
				return fileSize.get(0);
			return 0;
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
