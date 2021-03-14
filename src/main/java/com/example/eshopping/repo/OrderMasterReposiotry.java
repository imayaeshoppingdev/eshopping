package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.OrderMaster;

@Repository
public interface OrderMasterReposiotry  extends CrudRepository<OrderMaster, Long> {

	List<OrderMaster> findByOrderId(String orderId);
	
	OrderMaster findById(String id);
	
	List<OrderMaster> findBySellerIdAndStatus(String selleId, String status);
	
}
