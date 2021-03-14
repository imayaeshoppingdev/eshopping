package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.eshopping.entity.User;

@Service
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	List<User> findAll();
	
	User findById(String id);
	
	int deleteById(String id);
}
