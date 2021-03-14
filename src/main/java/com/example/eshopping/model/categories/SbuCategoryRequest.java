package com.example.eshopping.model.categories;

import java.util.List;

public class SbuCategoryRequest {

	private String name;
	private List<ProductListingRequest> product;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductListingRequest> getProduct() {
		return product;
	}
	public void setProduct(List<ProductListingRequest> product) {
		this.product = product;
	}
	
	
}
