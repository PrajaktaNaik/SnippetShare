package com.cmpe275.snippetshare.Manager;

import java.util.ArrayList;
import java.util.List;

import com.cmpe275.snippetshare.DAO.UserDAO;
import com.cmpe275.snippetshare.Model.User;

public class UserManager {

	public static void addUser(User user)throws Exception {
		UserDAO.saveUser(user);
	}

	public static boolean loginUser(User user)throws Exception {
		return UserDAO.is_user_exists(user);
	}

	public static boolean is_user_email_exists(User user)throws Exception {
		return UserDAO.is_user_email_exists(user);
	}
	
	public static List<String> getAllUsers(String currentUser)throws Exception{
		List<String> users = new ArrayList<String>();
		List<User> userList = UserDAO.getAllUsers(currentUser);
		for (User user : userList) {
			users.add(user.getEmail());
		}
		return users;
	}
}
