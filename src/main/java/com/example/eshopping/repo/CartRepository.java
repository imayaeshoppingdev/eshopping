package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{

	List<Cart> findByCartUserId(String userId);
	Cart findByProductIdAndCartUserId(String productId, String userId);
	Integer deleteById(String id);
	Cart findById(String id);
	Long countByCartUserId(String userId);
	List<Cart> findByProductId(String productId);
}
