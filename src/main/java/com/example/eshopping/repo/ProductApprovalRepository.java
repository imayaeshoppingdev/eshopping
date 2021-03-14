package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.ProductApproval;

public interface ProductApprovalRepository extends CrudRepository<ProductApproval, Long>{

	ProductApproval findById(String id);
	
	ProductApproval findByProductName(String  productName);
	
	int deleteById(String id);
	
	List<ProductApproval> findAll();
}
