package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.BulkOrders;

public interface BulkOrderReposiotry extends CrudRepository<BulkOrders, Long>{

	List<BulkOrders> findByStatus(String status);
	List<BulkOrders> findByBuyerIdAndStatus(String id,String approved);
	BulkOrders findById(String id);
	int deleteById(String id);
}
