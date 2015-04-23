package com.cmpe275.snippetshare.DAO;

import org.springframework.data.mongodb.core.MongoOperations;

import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.dbconfigs.MongoConfig;

public class UserDao {

	static MongoOperations mongoOperation = null;
	public static void saveUser(User user) {
		mongoOperation = MongoConfig.getMongoOperationsObj();
		
		mongoOperation.save(user);
		
		
		
		System.out.println("User" + user.toString());
		
	}

}
