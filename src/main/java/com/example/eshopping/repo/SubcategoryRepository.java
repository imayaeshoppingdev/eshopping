package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.SubCategory;

public interface SubcategoryRepository extends CrudRepository<SubCategory, Long>{

	List<SubCategory> findByCategoryId(String categoryId);
	
	SubCategory findBySubCategory(String subCategory);
	
	SubCategory findById(String id);
}
