package com.example.eshopping.model.categories;

import java.util.List;

public class CategoryDataRequest {
	
	private String category;
	
	private List<SbuCategoryRequest> subCategory;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<SbuCategoryRequest> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SbuCategoryRequest> subCategory) {
		this.subCategory = subCategory;
	}

	
	
	

}
