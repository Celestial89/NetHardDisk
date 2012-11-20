package org.renwei.dao.interfaces;

import org.renwei.model.User;

public interface UserDao {
	 public void saveUser(User user);
	 public boolean exists(User user);
	 public User loadUser(User user);
	 public User searchUserByName(String userName);
	 public User searchUserById(String id);
}
