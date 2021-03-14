package com.example.eshopping.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.Bargain;

public interface BargainRepository extends CrudRepository<Bargain, Long>{

	Bargain findByUserIdAndCategoryId(String userId, String catrgoryId);
	
}
