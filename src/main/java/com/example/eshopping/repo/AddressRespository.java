package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.Address;

@Repository
public interface AddressRespository extends CrudRepository<Address, Long>{

	List<Address> findByUserId(String userId);
	Address findByUserIdAndDefaultAddress(String userId, boolean isDefault);
	Address findById(String id);
	Integer deleteById(String id);
	
}
