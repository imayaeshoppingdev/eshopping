package com.example.eshopping.model.user;

public class ApprovalUserRequest {

	private String userId;
	private boolean aadharApproved;
	private boolean gstApproved;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
