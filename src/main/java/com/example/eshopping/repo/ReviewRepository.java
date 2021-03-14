package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

	Review findById(String id);
	List<Review> findByProductId(String productId);
	Integer deleteById(String id);
	Review findByUserIdAndProductId(String userId, String productId);
	
}
