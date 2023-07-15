package com.infotel.eshop.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.infotel.eshop.dao.CategoryDao;
import com.infotel.eshop.model.Category;

public class CategoryDaoMemory implements CategoryDao{
	
	public  List<Category> findAll() {
		List<Category> categories = new ArrayList<>();
		
		for(Category category : Database.CATEGORIES_TABLE){
			categories.add(category);
		}
		return categories; //.stream().toArray(String[] ::new)
	}
	
		
}
