package com.example.eshopping.model.user;

import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;

public class UserFullDetailsResponse {

	private User user;
	private UserDetails userDetails;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	
}
