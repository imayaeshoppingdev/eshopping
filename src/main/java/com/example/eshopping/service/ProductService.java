package com.example.eshopping.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductApproval;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.model.product.ProductListResponse;
import com.example.eshopping.model.product.ProductRequest;

public interface ProductService {

	public Product saveProduct(Product product);
	
	public ProductListResponse getAllProducts(String category, String subCategory, int skip, String role,  String sort, String displayLocation);
	
	public Product getProductById(String id);
	
	public Product findProductByName(String productName);
	
	public Integer deleteProduct(String id);
	
	public ProductListResponse getProductByFilter(ProductRequest request);
	
	public long totalNoOfProducts();
	
	public List<Product> getProductTitleForSuggestion(String category, String subCategory,String title,  String displayLocation);
	
	public Map<String, Set<String>> getFilterData(ProductRequest request);
	
	public ProductApproval saveProductApproval(ProductApproval productApproval);
	
	public ProductApproval findProductApprovalById(String id);
	
	public ProductApproval findProductApprovalByName(String productName);
	
	public int deleteProductApproval(String id);
	
	public List<Product> getApprovalList(String userId, String role, String status);
	
	public List<Product> getProductFilterAdmin(ProductRequest request);
	
	public List<Product> getBulkOrderProduct(String displayLocation);
	
	public List<ProductListing> getProductListingSuggesion(String title,  String categoryId, String subCategoryId);
	
	public List<Product> getProductNameSuggesion(String title);
	
	public List<Product> getProductByUserId(String userId);
}
