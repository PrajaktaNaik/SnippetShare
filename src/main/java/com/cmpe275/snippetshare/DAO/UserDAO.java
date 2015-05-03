package com.cmpe275.snippetshare.DAO;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class UserDAO {

	static MongoOperations mongoOperation = null;
	
	public static void saveUser(User user)throws Exception {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		mongoOperation.save(user);
		System.out.println("User" + user.toString());
	}
	
	public static boolean is_user_exists(User user)throws Exception {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		
		Query searchUserQuery = new Query(Criteria.where("email").is(user.getEmail()));
		searchUserQuery.addCriteria(Criteria.where("password").is(user.getPassword()));
		
		User userdata= mongoOperation.findOne(searchUserQuery,User.class);
		if(userdata==null)
			return false;
		else
			return true;
	}
	
	public static boolean is_user_email_exists(User user)throws Exception {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		Query searchUserQuery = new Query(Criteria.where("email").is(user.getEmail()));
		User userdata= mongoOperation.findOne(searchUserQuery,User.class,"user");
		if(userdata==null){
			System.out.println("New user, Ready to sign up");
			return true;
		}
		else
			System.out.println("User Already exists. Login please");
			return false;
	}

	public static List<User> getAllUsers()throws Exception {
		Query query = new Query();
		query.fields().include("email");
		List<User> userList = MongoConfig.getMongoOperationsObj().find(query, User.class);
		return userList;
	}
}
