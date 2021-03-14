package com.example.eshopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.eshopping.dao.CategoryDAO;
import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.SubCategory;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Category getCategory(String category) {
		Category categoryEntity = new Category();
		try {
			
			Query query = new Query();
			if(category != null && !category.isEmpty())
				query.addCriteria(Criteria.where("category").is(category));
			
			categoryEntity = (Category) mongoTemplate.findOne(query,Category.class);
			}
	
		catch(Exception e) {
			System.out.println(e);
		}
		return categoryEntity;
	}
	
	public SubCategory getSubCategory(String subCategory) {
		SubCategory categoryEntity = new SubCategory();
		try {
			
			Query query = new Query();
			if(subCategory != null && !subCategory.isEmpty())
				query.addCriteria(Criteria.where("subCategory").is(subCategory));
			
			categoryEntity = (SubCategory) mongoTemplate.findOne(query,SubCategory.class);
			}
	
		catch(Exception e) {
			System.out.println(e);
		}
		return categoryEntity;
	}
}
