package com.example.eshopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Cart;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;
import com.example.eshopping.model.product.ApprovalRequest;
import com.example.eshopping.model.user.ApprovalUserRequest;
import com.example.eshopping.model.user.UserDetailsResponse;
import com.example.eshopping.model.user.UserFullDetailsResponse;
import com.example.eshopping.model.user.UserListVO;
import com.example.eshopping.model.user.UserRequest;
import com.example.eshopping.model.user.UserUpdateRequest;
import com.example.eshopping.service.CartService;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.service.UserService;
import com.example.eshopping.util.EncryptDecrypt;
import com.example.eshopping.util.JSONUtil;

@RestController
@RequestMapping("/authenticateUsers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String firstPage() {
		return "Hello";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getUsers")
	public UserListVO getUsersList() {
		UserListVO response = new UserListVO();
		List<UserFullDetailsResponse> fullResponse = new ArrayList();
		try {
			List<User> users = userService.findAllUsers();
			for(User userList : users) {
				UserFullDetailsResponse userResponse = new UserFullDetailsResponse();
				userResponse.setUser(userList);
				UserDetails userDetails = userService.findUserDetailsById(userList.getId());
				if(userDetails != null) {
					userResponse.setUserDetails(userDetails);
				}
				fullResponse.add(userResponse);
			}
			response.setFullResponse(fullResponse);
			response.setStatus(CommonConstant.SUCCESS);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/roleUpdate")
	public BaseResponse updateRole(@RequestBody UserUpdateRequest userRequest) {
		BaseResponse response = new BaseResponse();
		try {
			User user = userService.findUserById(userRequest.getId());
			System.out.println(JSONUtil.toJson(user));
			if(user == null) {
				response.setStatus(CommonConstant.ERROR);
				response.setStatusCode(01);
				response.setMessage("User Not found");
				return response;
			}
			user.setRole(userRequest.getRole());
			userService.saveUser(user);
			response.setStatus(CommonConstant.SUCCESS);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	
	@GetMapping("/deleteUser/{id}")
	public BaseResponse deleteUser(@PathVariable String id) {
		BaseResponse response = new BaseResponse();
		try {
			int delete = userService.deleteUserById(id);
			List<Product> productList = productService.getProductByUserId(id);
			for(Product product : productList) {
				List<Cart> cartList = cartService.getCartByProductId(product.getId());
				for(Cart cart : cartList) {
					cartService.deleteCart(cart.getId());
				}
				productService.deleteProduct(product.getId());
			}
			System.out.println(" delete user "+delete);
			response.setMessage("User Deleted.");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getAadharApproval")
	public UserListVO getAadharApproval() {
		UserListVO response = new UserListVO();
		try {
			List<User> user = userService.getAadharApproval();
			response.setUsers(user);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getGstApproval")
	public UserListVO getGstInApproval() {
		UserListVO response = new UserListVO();
		try {
			List<UserDetails> user = userService.getGstInApproval();
			response.setUserDetails(user);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/approval")
	public BaseResponse approvalOfAadharAndGst(@RequestBody ApprovalUserRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			System.out.println("request "+JSONUtil.toJson(request));
			if(request.isAadharApproved()) {
				User user = userService.updateUserById(request.getUserId());
				System.out.println("request "+JSONUtil.toJson(user));
				user.setAadharApproved(true);
				userService.saveUser(user);
			}
			if(request.isGstApproved()) {
				UserDetails userDetails = userService.findUserDetailsById(request.getUserId());
				userDetails.setGstApproved(true);
				userService.saveUserDetails(userDetails);
			}
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getUserDetails/{id}")
	public UserDetailsResponse getUserDetails(@PathVariable String id) {
		UserDetailsResponse response = new UserDetailsResponse();
		try {
			UserDetails userDetails = userService.findUserDetailsById(id);
			User user = userService.findUserById(id);
			response.setUserDetails(userDetails);
			response.setUser(user);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/updateUserDetails")
	public BaseResponse getUserDetails(@RequestBody UserUpdateRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			UserDetails userDetails = userService.findUserDetailsById(request.getId());
			userDetails.setGstin(request.getGst());
			userService.saveUserDetails(userDetails);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/updateAadhar")
	public BaseResponse updateAadhar(@RequestBody UserUpdateRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			User user = userService.updateUserById(request.getId());
			user.setAadharNumber(request.getAadharNumber());
			userService.saveUser(user);
			System.out.println(JSONUtil.toJson(user));
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/updateUserProfile")
	public BaseResponse updateUserProfile(@RequestBody UserRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			User user = userService.updateUserById(request.getUser().getId());
			user.setPhoneNumber(request.getUser().getPhoneNumber());
			user.setEmail(request.getUser().getEmail());
			user.setPickUp(request.getUser().getPickUp());
			user.setAadharNumber(request.getUser().getAadharNumber());
			user.setRole(request.getUser().getRole());
			user.setUsername(request.getUser().getUsername());
			userService.saveUser(user);
			
			if(request.getUserDetails() != null) {
				UserDetails userDetails = userService.saveUserDetails(request.getUserDetails());
				response.setMessage("User Details Updated");
			}
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	
	
	
}
