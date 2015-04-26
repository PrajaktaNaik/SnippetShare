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
	
	public static MongoOperations mongoOperation = null;
	
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
		if(mongoOperation == null){
			ApplicationContext appContext = new AnnotationConfigApplicationContext(MongoConfig.class);
			MongoOperations mongoOperation = (MongoOperations) appContext.getBean("mongoTemplate");
			return mongoOperation;
		}else{
			return mongoOperation;
		}
	}
}
