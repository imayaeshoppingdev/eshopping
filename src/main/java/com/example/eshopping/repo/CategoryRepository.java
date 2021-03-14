package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Category;

@Repository
public interface CategoryRepository  extends CrudRepository<Category, Long>{

	List<Category> findAll();
	
	Category findByCategory(String category);
	
	Category findById(String id);
}
