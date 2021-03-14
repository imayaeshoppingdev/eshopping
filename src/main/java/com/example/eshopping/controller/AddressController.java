package com.example.eshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Address;
import com.example.eshopping.model.address.AddressListResponse;
import com.example.eshopping.model.address.AddressRequest;
import com.example.eshopping.model.address.AddressResponse;
import com.example.eshopping.service.AddressService;
import com.example.eshopping.util.JSONUtil;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addAndUpdateAddress")
	public AddressResponse addAndUpdateAddress(@Valid @RequestBody AddressRequest request) {
		AddressResponse response = new AddressResponse();
		try {
			Address address = new Address();
			System.out.println(JSONUtil.toJson(request.getAddress()));
			if(request.getAddress().getId() != null) {
				address = addressService.findAddressById(request.getAddress().getId());
			}
			address.setName(request.getAddress().getName());
			address.setAddress(request.getAddress().getAddress());
			address.setCity(request.getAddress().getCity());
			address.setState(request.getAddress().getState());
			address.setPincode(request.getAddress().getPincode());
			address.setMobile(request.getAddress().getMobile());
			address.setAlternateMobile(request.getAddress().getAlternateMobile());
			address.setEmail(request.getAddress().getEmail());
			address.setAlternateEmail(request.getAddress().getAlternateEmail());
			address.setType(request.getAddress().getType());
			address.setUserId(request.getAddress().getUserId());
			address.setCountry(request.getAddress().getCountry());
			Address addressCheck = new Address();
			if(request.getAddress().isDefaultAddress()) {
				addressCheck = addressService.findDefaultAddress(request.getAddress().getUserId(), true);
				System.out.println("address check "+JSONUtil.toJson(addressCheck));
				if(addressCheck != null && !request.getAddress().getId().equals(addressCheck.getId()) &&  request.getAddress().isDefaultAddress()) {
					addressCheck.setDefaultAddress(false);
					addressService.saveAddress(addressCheck);
				}
			}
			
			
			address.setDefaultAddress(request.getAddress().isDefaultAddress());
			address = addressService.saveAddress(address);
			if(address != null) {
				response.setStatus(CommonConstant.SUCCESS);
				response.setAddress(address);
			}
		}
		catch(Exception e) {
			response.setAddress(null);
			response.setStatusCode(01);
			response.setMessage("Address Failed");
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getDefaultAddress")
	public AddressResponse getDefaultAddress(@RequestBody AddressRequest request) {
		AddressResponse response = new AddressResponse();
		try {
			Address address = addressService.findDefaultAddress(request.getUserId(), true);
			if(address == null) {
				response.setStatusCode(01);
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("No Default Address found");
			}else {
				response.setStatus(CommonConstant.SUCCESS);
				response.setAddress(address);
			}
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAddressList/{userId}")
	public AddressListResponse getAddressList(@PathVariable String userId) {
		AddressListResponse response = new AddressListResponse();
		try {
			System.out.println("userId "+userId);
			if(userId != null) {
				List<Address> address = addressService.findAddressByUserId(userId);
				response.setAddress(address);
				response.setStatus(CommonConstant.SUCCESS);
			}
			else {
				response.setStatusCode(01);
				response.setMessage("Invalid Data");
				response.setStatus(CommonConstant.ERROR);
			}
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteAddress")
	public BaseResponse deleteAddress(@RequestBody AddressRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Address address = addressService.findAddressById(request.getAddressId());
			if(address.getUserId().equals(request.getUserId())) {
				int delete = addressService.deleteAddress(request.getAddressId());
				if(delete == 1) {
					response.setStatus(CommonConstant.SUCCESS);
					response.setMessage("Address Deleted");
				}else {
					response.setStatusCode(01);
					response.setStatus(CommonConstant.ERROR);
				}
			}
			else {
				response.setStatusCode(01);
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("Address No found");
			}
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Invalid Data");
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAddressById/{id}")
	public AddressResponse getAddressById(@PathVariable String id) {
		AddressResponse response = new AddressResponse();
		try {
			Address address = addressService.findAddressById(id);
			if(address == null) {
				response.setStatusCode(01);
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("Check Address Id");
			}
			else {
				response.setAddress(address);
				response.setStatus(CommonConstant.SUCCESS);
			}
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Invalid Data");
			System.out.println(e);
		}
		return response;
	}
}
