package com.cmpe275.snippetshare.DAO;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.Utility;
import com.cmpe275.snippetshare.dbconfigs.MongoConfig;

import org.springframework.data.mongodb.core.query.Criteria;

public class UserDAO {

	static MongoOperations mongoOperation = null;
	public static void saveUser(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		
		mongoOperation.save(user);
		
		System.out.println("User" + user.toString());
		
	}
	public static boolean is_user_exists(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		//User userdata=mongoOperation.findOne(new Query(Criteria where("_id") is()), User.class, "user");
		Query searchUserQuery = new Query(Criteria.where("email").is(user.getEmail()));
		User userdata= mongoOperation.findOne(searchUserQuery,User.class,"user");
		if(userdata==null || userdata.getPassword()!= Utility.getEncryptedValue(user.getPassword())){
			System.out.println("Invalid Username or password");
			return false;
		}
		else
			System.out.println("valid user");
			return true;
		// TODO Auto-generated method stub
		
	}
	
	public static boolean is_user_email_exists(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		//User userdata=mongoOperation.findOne(new Query(Criteria where("_id") is()), User.class, "user");
		Query searchUserQuery = new Query(Criteria.where("email").is(user.getEmail()));
		User userdata= mongoOperation.findOne(searchUserQuery,User.class,"user");
		if(userdata==null){
			System.out.println("New user, Ready to sign up");
			return true;
		}
		else
			System.out.println("User Already exists. Login please");
			return false;
		// TODO Auto-generated method stub
		
	}
}