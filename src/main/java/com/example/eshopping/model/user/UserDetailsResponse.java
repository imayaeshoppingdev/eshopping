package com.example.eshopping.model.user;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;

public class UserDetailsResponse extends BaseResponse{

	UserDetails userDetails;
	User user;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
