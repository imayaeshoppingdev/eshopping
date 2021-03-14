package com.example.eshopping.service;



import java.util.List;

import com.example.eshopping.entity.Roles;
import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;


public interface UserService {

	public User saveUser(User user);
	
	public User findByEmailAndPassword(String email, String Password);
	
	public User findByUsername(String userName);
	
	public User findByEmail(String email);
	
	public List<User> findAllUsers();
	
	public User findUserById(String id);
	
	public List<Roles> getRoles();
	
	public int deleteUserById(String id);
	
	public UserDetails saveUserDetails(UserDetails userDetails);
	
	public UserDetails findUserDetailsById(String id);
	
	public List<UserDetails> getGstInApproval();
	
	public List<User> getAadharApproval();
	
	public User updateUserById(String id);
	
}
