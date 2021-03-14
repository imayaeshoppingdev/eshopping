package com.example.eshopping.model.user;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;

public class UserListVO extends BaseResponse{

	private List<User> users;
	private List<UserDetails> userDetails;
	private List<UserFullDetailsResponse> fullResponse;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserFullDetailsResponse> getFullResponse() {
		return fullResponse;
	}

	public void setFullResponse(List<UserFullDetailsResponse> fullResponse) {
		this.fullResponse = fullResponse;
	}
	
	
}
