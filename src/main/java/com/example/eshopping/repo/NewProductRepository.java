package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.NewProduct;

public interface NewProductRepository extends CrudRepository<NewProduct, Long>{

	List<NewProduct> findAll();
	
	int deleteById(String id);
}
