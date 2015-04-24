package com.cmpe275.snippetshare.Utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return ApplicationConstants.DATABASE;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(new MongoClientURI(ApplicationConstants.URL));
	}
	
	public static MongoOperations getMongoOperationsObj(){
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		return mongoOperation;
	}
}
