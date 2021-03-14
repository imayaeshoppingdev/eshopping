package com.example.eshopping.dao;

import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.SubCategory;

public interface CategoryDAO {

	public Category getCategory(String category);
	
	public SubCategory getSubCategory(String subCategory);
}
