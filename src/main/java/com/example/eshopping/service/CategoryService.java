package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.entity.SubCategory;

public interface CategoryService {

	public Category saveCategory(Category category);
	
	public List<Category> getCategory();
	
	public SubCategory saveSubCategory(SubCategory subCategory);
	
	public List<SubCategory> getSubCategoryByCategoryId(String categoryId);
	
	public ProductListing saveProductList(ProductListing productListing);
	
	public List<ProductListing> getProductList(String subCategoryId);
	
	public Category getCategoryById(String id);
	
	public Category getByCategory(String category);
	
	public SubCategory getSubCategoryById(String id);
	
	public SubCategory getBySubCategory(String subCategory);
	
	public ProductListing getProductListingByProduct(String productListing);
}
