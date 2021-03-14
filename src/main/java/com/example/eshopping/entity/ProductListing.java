package com.example.eshopping.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductListing {

	@Id
	private String id;
	@NotEmpty
	private String categoryId;
	private String subCategoryId;
	private String productListing;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getProductListing() {
		return productListing;
	}
	public void setProductListing(String productListing) {
		this.productListing = productListing;
	}
	
	
	
}
