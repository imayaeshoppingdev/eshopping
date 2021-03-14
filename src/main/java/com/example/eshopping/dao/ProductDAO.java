package com.example.eshopping.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductApproval;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.model.product.ProductListResponse;
import com.example.eshopping.model.product.ProductRequest;

public interface ProductDAO {

	public ProductListResponse getAllProducts(String category, String subCategory, int skip, String role,  String sort,  String displayLocation);
	
	public ProductListResponse getProductUsingFilter(ProductRequest productRequest);
	
	public List<Product> getProductTitleForSuggestion(String category, String subCategory, String title, String displayLocation);
	
	public Map<String, Set<String>> getFilterData(ProductRequest request);
	
	public List<Product> getProductFilterAdmin(ProductRequest request);
	
	public List<Product> getProductApproval(String userId, String role, String status);
	
	public List<ProductListing> getProductListingSuggesion(String title,  String categoryId, String subCategoryId);

	public List<Product> getProductNameSuggesion(String title);
	
	public List<Product>  getBulkOrders(String displayLocation);
}
