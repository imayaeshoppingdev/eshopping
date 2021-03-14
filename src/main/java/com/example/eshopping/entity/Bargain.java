package com.example.eshopping.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bargain {

	@Id
	private String id;
	private String userId;
	private String categoryId;
	private String todayDate;
	private int noOfBargains;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}
	public int getNoOfBargains() {
		return noOfBargains;
	}
	public void setNoOfBargains(int noOfBargains) {
		this.noOfBargains = noOfBargains;
	}
	
	
	
}
