package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.ProductListing;

public interface ProductListRepository extends CrudRepository<ProductListing, Long>{

	List<ProductListing> findBySubCategoryId(String subCategoryId);
	
	ProductListing findByProductListing(String productListing);
}
