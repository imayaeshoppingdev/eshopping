package com.example.eshopping.model.categories;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.entity.SubCategory;

public class CategoryResponse extends BaseResponse{

	List<Category> category;
	List<SubCategory> subCategory;
	List<ProductListing> productList;
	
	
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public List<SubCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}
	public List<ProductListing> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductListing> productList) {
		this.productList = productList;
	}
	
	
}
