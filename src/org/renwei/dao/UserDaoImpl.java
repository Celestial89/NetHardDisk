package org.renwei.dao;

import org.renwei.dao.interfaces.UserDao;
import org.renwei.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDaoImpl implements UserDao
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

	public boolean exists(User user)
	{		
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      java.util.List users= session.createQuery("from User as u where u.userName='"+user.getUserName()+"'").list();
	      tx.commit();
		  if(users.size() > 0)
				return true;
		  return false;
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
	
	public User loadUser(User user)
	{
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      User searchUser= (User)session.createQuery("from User as u where u.userName='"+user.getUserName()+"'").uniqueResult();
	      tx.commit();
	      return searchUser;
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
	
	public User searchUserByName(String userName)
	{
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      User searchUser= (User)session.createQuery("from User as u where u.userName='"+userName+"'").uniqueResult();
	      tx.commit();
	      return searchUser;
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
	
	public User searchUserById(String id)
	{
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      User searchUser= (User)session.createQuery("from User as u where u.id='"+ id +"'").uniqueResult();
	      tx.commit();
	      return searchUser;
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

	public void saveUser(User user)
	{
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    if (exists(user))
		{
	    	try {
	    		throw new Exception("<" + user + ">已经存在！");
			} 
	    	catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				tx = session.beginTransaction();
				session.save(user);
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
	}
}
