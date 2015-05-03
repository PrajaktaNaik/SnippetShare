package com.cmpe275.snippetshare.Manager;

import java.util.List;

import com.cmpe275.snippetshare.DAO.CategoryDAO;
import com.cmpe275.snippetshare.Model.Category;

public class CategoryManager {

	public static List<Category> getAllCategories() throws Exception{
		return CategoryDAO.getAllCategories();
	} 
}
