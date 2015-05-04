package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.SnippetImages;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class SnippetImagesDAO {

	public static void addImage(SnippetImages image){
		
		MongoConfig.getMongoOperationsObj().save(image);
	}
	
	public static List<SnippetImages> getSnippetImages(List<Long> imageIds){
		Query query=new Query();
		query.addCriteria(Criteria.where("imageId").in(imageIds));
		List<SnippetImages> allImages= MongoConfig.getMongoOperationsObj().find(query, SnippetImages.class);
	
		return allImages==null?(new ArrayList<SnippetImages>()):allImages;
	}
}
