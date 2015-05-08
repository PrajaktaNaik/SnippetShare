package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.SnippetImages;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class SnippetImagesDAO {

//	----------------------------------------------------------------------------------------------------
	
	public static void addImage(SnippetImages image){
		MongoConfig.getMongoOperationsObj().save(image);
	}

//	----------------------------------------------------------------------------------------------------
	
	public static List<SnippetImages> getSnippetImages(List<Long> imageIds){
		Query query=new Query();
		query.addCriteria(Criteria.where("imageId").in(imageIds));
		List<SnippetImages> allImages= MongoConfig.getMongoOperationsObj().find(query, SnippetImages.class);
	
		return allImages==null?(new ArrayList<SnippetImages>()):allImages;
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static SnippetImages getImage(Long imageId){
		Query query=new Query();
		query.addCriteria(Criteria.where("imageId").in(imageId));
		SnippetImages imageGot= (SnippetImages) MongoConfig.getMongoOperationsObj().findOne(query, SnippetImages.class);
	
		return imageGot==null?(new SnippetImages()):imageGot;
	}

//	----------------------------------------------------------------------------------------------------
	
	public static void deleteImage(Long imageId) {
		Query query=new Query();
		query.addCriteria(Criteria.where("imageId").in(imageId));
		MongoConfig.getMongoOperationsObj().remove(query, SnippetImages.class);
	}
}
