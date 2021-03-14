package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.entity.Address;
import com.example.eshopping.repo.AddressRespository;
import com.example.eshopping.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRespository addressRespository;
	
	@Override
	public Address saveAddress(Address address) {
		return addressRespository.save(address);
	}
	
	@Override
	public Address findAddressById(String id) {
		return addressRespository.findById(id);
	}
	
	@Override
	public Address findDefaultAddress(String userId, boolean isDefault) {
		System.out.println("userId "+userId+" default "+isDefault);
		return addressRespository.findByUserIdAndDefaultAddress(userId, isDefault);
	}
	
	@Override
	public List<Address> findAddressByUserId(String userId){
		return addressRespository.findByUserId(userId);
	}
	
	@Override
	public Integer deleteAddress(String id) {
		return addressRespository.deleteById(id);
	}
}
