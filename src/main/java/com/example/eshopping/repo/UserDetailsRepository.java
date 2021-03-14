package com.example.eshopping.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long>{

	UserDetails findByUserId(String id);
	
	
}
