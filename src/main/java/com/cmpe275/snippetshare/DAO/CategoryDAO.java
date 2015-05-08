package com.cmpe275.snippetshare.DAO;

import java.util.List;

import com.cmpe275.snippetshare.Model.Category;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class CategoryDAO {

//	----------------------------------------------------------------------------------------------------
	
	public static List<Category> getAllCategories() throws Exception{
		List<Category> categories = MongoConfig.getMongoOperationsObj().findAll(Category.class);
		return categories;
	}

//	----------------------------------------------------------------------------------------------------
}
