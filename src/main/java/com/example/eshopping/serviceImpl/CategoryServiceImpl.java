package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.dao.CategoryDAO;
import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.entity.SubCategory;
import com.example.eshopping.repo.CategoryRepository;
import com.example.eshopping.repo.ProductListRepository;
import com.example.eshopping.repo.SubcategoryRepository;
import com.example.eshopping.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SubcategoryRepository subcategoryRepository;
	
	@Autowired
	ProductListRepository productListRepository;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	public Category saveCategory(Category category) {
		return this.categoryRepository.save(category);
	}
	
	public List<Category> getCategory(){
		return this.categoryRepository.findAll();
	}
	
	public Category getCategoryById(String id) {
		return this.getByCategory(id);
	}
	
	public SubCategory saveSubCategory(SubCategory subCategory) {
		return this.subcategoryRepository.save(subCategory);
	}
	
	public SubCategory getSubCategoryById(String id) {
		return this.subcategoryRepository.findById(id);
	}
	
	public List<SubCategory> getSubCategoryByCategoryId(String categoryId){
		return this.subcategoryRepository.findByCategoryId(categoryId);
	}
	
	public ProductListing saveProductList(ProductListing productListing) {
		return this.productListRepository.save(productListing);
	}
	
	public List<ProductListing> getProductList(String subCategoryId){
		return this.productListRepository.findBySubCategoryId(subCategoryId);
	}
	
	public Category getByCategory(String category) {
		return this.categoryDAO.getCategory(category);
	}
	
	
	public SubCategory getBySubCategory(String subCategory) {
		return this.categoryDAO.getSubCategory(subCategory);
	}
	
	public ProductListing getProductListingByProduct(String productListing) {
		return this.productListRepository.findByProductListing(productListing);
	}
}
