package com.example.eshopping.model.user;

import com.example.eshopping.common.BaseResponse;

public class UserResponse extends BaseResponse{

	private String jwt;
	private String userId;
	private String userName;
	private String gstin;
	private String aadharNumber;
	private boolean aadharApproved;
	private boolean gstApproved;
	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public boolean isAadharApproved() {
		return aadharApproved;
	}

	public void setAadharApproved(boolean aadharApproved) {
		this.aadharApproved = aadharApproved;
	}

	public boolean isGstApproved() {
		return gstApproved;
	}

	public void setGstApproved(boolean gstApproved) {
		this.gstApproved = gstApproved;
	}
	
	
}
