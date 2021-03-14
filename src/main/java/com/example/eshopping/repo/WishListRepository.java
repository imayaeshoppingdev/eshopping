package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.WishList;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Long>{

	List<WishList> findByWishListUserId(String userId);
	
	WishList findByProductId(String productId);
}
