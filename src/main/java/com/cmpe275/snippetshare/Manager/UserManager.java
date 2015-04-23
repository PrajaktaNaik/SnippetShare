package com.cmpe275.snippetshare.Manager;

import com.cmpe275.snippetshare.DAO.UserDao;
import com.cmpe275.snippetshare.Model.User;

public class UserManager {

	public static void createUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		UserDao.saveUser(user);
	}

	//create user,search user,update,delete user
}
