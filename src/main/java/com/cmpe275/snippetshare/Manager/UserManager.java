package com.cmpe275.snippetshare.Manager;

import com.cmpe275.snippetshare.DAO.UserDao;
import com.cmpe275.snippetshare.Model.User;

public class UserManager {

	public static void createUser(String email, String password) {
		User user = new User();
		//user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		
		UserDao.saveUser(user);
	}

	public static void addUser(User user) {
		UserDao.saveUser(user);
		// TODO Auto-generated method stub
		
	}

	public static void loginUser(User user) {
		
		boolean is_valid_user=is_user_exists(user);
		 if(is_valid_user==true)
			 System.out.println("valid user.login");
		 else 
			 System.out.println("invalid user");
		// TODO Auto-generated method stub
		
	}

	public static boolean is_user_exists(User user) {
		return UserDao.is_user_exists(user);
		// TODO Auto-generated method stub
		
	}
	
	public static boolean is_user_email_exists(User user) {
		return UserDao.is_user_email_exists(user);
		// TODO Auto-generated method stub
		
	}


	//create user,search user,update,delete user
}
