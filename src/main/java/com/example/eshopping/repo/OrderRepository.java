package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

	Order findById(String id);
	List<Order> findByOrderUserId(String userId);
	
}
