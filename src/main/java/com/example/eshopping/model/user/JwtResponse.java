package com.example.eshopping.model.user;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private String userId;
	private String userName;
	private long count;
	private String role;
	private String gstin;
	private String aadharNumber;
	private boolean gstApproved;
	private boolean aadharApproved;

	public JwtResponse(String jwttoken, String userId, String userName, Long count, String role, String gstin, String aadharNumber,
			boolean aadharApproved, boolean gstApproved) {
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.userName = userName;
		this.count = count;
		this.role = role;
		this.aadharNumber = aadharNumber;
		this.gstin = gstin;
		this.aadharApproved = aadharApproved;
		this.gstApproved = gstApproved;
		
		
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getGstin() {
		return gstin;
	}



	public String getAadharNumber() {
		return aadharNumber;
	}


	public String getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCount() {
		return count;
	}

	public String getRole() {
		return role;
	}

	public boolean isGstApproved() {
		return gstApproved;
	}


	public boolean isAadharApproved() {
		return aadharApproved;
	}



	
	
}