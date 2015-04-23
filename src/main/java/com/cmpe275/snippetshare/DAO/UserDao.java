package com.cmpe275.snippetshare.DAO;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.dbconfigs.MongoConfig;

import org.springframework.data.mongodb.core.query.Criteria;

public class UserDao {

	static MongoOperations mongoOperation = null;
	public static void saveUser(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		
		mongoOperation.save(user);
		
		System.out.println("User" + user.toString());
		
	}
	public static boolean is_user_exists(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		//User userdata=mongoOperation.findOne(new Query(Criteria where("_id") is()), User.class, "user");
		Query searchUserQuery = new Query(Criteria.where("id").is(user.getUserId()));
		User userdata= mongoOperation.findOne(searchUserQuery,User.class,"user");
		if(userdata==null || userdata.getPassword()!=user.getPassword()){
			System.out.println("Invalid Username or password");
			return false;
		}
		else
			System.out.println("valid user");
			return true;
		// TODO Auto-generated method stub
		
	}

}