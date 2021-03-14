package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findById(String id);
	List<Product> findAll();
	Product findByProductName(String productName);
	Integer deleteById(String id);
//	@Query()
	List<Product> findByTitleRegex(String title);
	List<Product> findByBulkOrders(boolean bulkOrders);
	List<Product> findByUserId(String userId);
}
