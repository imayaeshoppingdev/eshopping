package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Address;

public interface AddressService {

	public Address saveAddress(Address address);
	
	public Address findAddressById(String id);
	
	public Address findDefaultAddress(String userId, boolean isDefault);
	
	public List<Address> findAddressByUserId(String userId);
	
	public Integer deleteAddress(String id);
	
}
