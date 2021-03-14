package com.example.eshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Category;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.entity.SubCategory;
import com.example.eshopping.model.categories.CategoryDataRequest;
import com.example.eshopping.model.categories.CategoryRequest;
import com.example.eshopping.model.categories.CategoryResponse;
import com.example.eshopping.model.categories.ProductListingRequest;
import com.example.eshopping.model.categories.SbuCategoryRequest;
import com.example.eshopping.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/addCatregory")
	public BaseResponse addCategory(@RequestBody CategoryRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Category category = new Category();
			category.setCategory(request.getCategory());
			Category categoryisPresent = categoryService.getByCategory(request.getCategory());
			if(categoryisPresent != null && categoryisPresent.getCategory().equalsIgnoreCase(request.getCategory())) {
				response.setStatus(CommonConstant.ERROR);
				 response.setMessage("Category is already present");
				 response.setStatusCode(2);
				 return response;
			}
			if(categoryisPresent == null) {
				categoryService.saveCategory(category);
			}
			
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Category added to Failed");
			 System.out.println("message "+e);
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/addSubCatregory")
	public BaseResponse addSubCategory(@RequestBody CategoryRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			SubCategory subCategory = new SubCategory();
			subCategory.setCategoryId(request.getCategoryId());
			subCategory.setSubCategory(request.getSubCategory());
			SubCategory subCategoryIsPresent = categoryService.getBySubCategory(request.getSubCategory());
			if(subCategoryIsPresent != null && subCategoryIsPresent.getCategoryId().equals(request.getCategoryId())) {
				response.setStatus(CommonConstant.ERROR);
				 response.setMessage("Sub Category is already present");
				 response.setStatusCode(2);
				 return response;
			}
			categoryService.saveSubCategory(subCategory);
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Sub Category added to Failed");
			 response.setStatusCode(01);
			 System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/addProductListing")
	public BaseResponse addProductListing(@RequestBody CategoryRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			ProductListing productListing = new ProductListing();
			productListing.setCategoryId(request.getCategoryId());
			productListing.setSubCategoryId(request.getSubCategoryId());
			productListing.setProductListing(request.getProductListing());
			SubCategory subCategory = categoryService.getSubCategoryById(request.getSubCategoryId());
			if(!subCategory.getId().equals(request.getSubCategoryId())) {
				response.setStatus(CommonConstant.ERROR);
				 response.setMessage("Product Listing is already present");
				 response.setStatusCode(2);
				 return response;
			}
			else {
				ProductListing product = categoryService.getProductListingByProduct(request.getProductListing());
				if(product != null) {
					response.setStatus(CommonConstant.ERROR);
					 response.setMessage("Product Listing is already present");
					 response.setStatusCode(2);
					 return response;	
				}
			}
			categoryService.saveProductList(productListing);
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("product Listing added to Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getCategory")
	public CategoryResponse getCategory() {
		CategoryResponse response = new CategoryResponse();
		try {
			List<Category> category = categoryService.getCategory();
			response.setCategory(category);
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Get Category to Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getSubCategory/{id}")
	public CategoryResponse getSubCategory(@PathVariable String id) {
		CategoryResponse response = new CategoryResponse();
		try {
			List<SubCategory> category = categoryService.getSubCategoryByCategoryId(id);
			response.setSubCategory(category);
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Get Category to Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@GetMapping("/getProductListing/{id}")
	public CategoryResponse getProductListing(@PathVariable String id) {
		CategoryResponse response = new CategoryResponse();
		try {
			List<ProductListing> category = categoryService.getProductList(id);
			response.setProductList(category);
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Get Category to Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/dataUpload")
	public BaseResponse dataUpload(@RequestBody CategoryDataRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Category category = new Category();
			category.setCategory(request.getCategory());
			categoryService.saveCategory(category);
			for(SbuCategoryRequest result : request.getSubCategory()) {
				SubCategory subCategory = new SubCategory();
				subCategory.setCategoryId(category.getId());
				subCategory.setSubCategory(result.getName());
				categoryService.saveSubCategory(subCategory);
				for(ProductListingRequest value : result.getProduct()) {
					ProductListing productListing = new ProductListing();
					productListing.setCategoryId(category.getId());
					productListing.setSubCategoryId(subCategory.getId());
					productListing.setProductListing(value.getName());
					categoryService.saveProductList(productListing);
				}
			}
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Get Category to Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
}
