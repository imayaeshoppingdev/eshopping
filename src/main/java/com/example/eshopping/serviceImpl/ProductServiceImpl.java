package com.example.eshopping.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.dao.ProductDAO;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductApproval;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.model.product.ProductListResponse;
import com.example.eshopping.model.product.ProductRequest;
import com.example.eshopping.repo.ProductApprovalRepository;
import com.example.eshopping.repo.ProductRepository;
import com.example.eshopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productReository;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ProductApprovalRepository productApprovalReposiotry;
	
	
	@Override
	public Product saveProduct(Product product) {
		return productReository.save(product);
	}
	
	@Override
	public ProductListResponse getAllProducts(String category, String subCategory, int skip, String role,  String sort, String displayLocation) {
		System.out.println("skip"+skip);		
		return productDAO.getAllProducts(category, subCategory, skip, role, sort, displayLocation);
	}
	
	@Override
	public Product getProductById(String id) {
		return productReository.findById(id);
	}
	
	@Override
	public Product findProductByName(String productName) {
		return productReository.findByProductName(productName);
	}
	
	@Override
	public Integer deleteProduct(String id) {
		return productReository.deleteById(id);
	}
	
	@Override
	public ProductListResponse getProductByFilter(ProductRequest request){
		return productDAO.getProductUsingFilter(request);
	}
	
	@Override
	public long totalNoOfProducts() {
		return productReository.count();
	}
	
	@Override
	public List<Product> getProductTitleForSuggestion(String category, String subCategory,String title,  String displayLocation){
		return productDAO.getProductTitleForSuggestion(category, subCategory, title, displayLocation);
	}
	
	@Override
	public Map<String, Set<String>> getFilterData(ProductRequest request){
		return productDAO.getFilterData(request);
	}
	
	public ProductApproval saveProductApproval(ProductApproval productApproval) {
		return productApprovalReposiotry.save(productApproval);
	}
	
	public ProductApproval findProductApprovalById(String id) {
		return productApprovalReposiotry.findById(id);
	}
	
	public ProductApproval findProductApprovalByName(String productName) {
		return productApprovalReposiotry.findByProductName(productName);
	}
	
	public int deleteProductApproval(String id) {
		return productApprovalReposiotry.deleteById(id);
	}
	
	public List<Product> getApprovalList(String userId, String role, String status){
		return productDAO.getProductApproval(userId, role, status);
	}
	
	public List<Product> getProductFilterAdmin(ProductRequest request){
		return productDAO.getProductFilterAdmin(request);
	}
	
	public List<Product> getBulkOrderProduct(String displayLocation){
		return productDAO.getBulkOrders(displayLocation);
	}
	
	public List<ProductListing> getProductListingSuggesion(String title,  String categoryId, String subCategoryId){
		return productDAO.getProductListingSuggesion(title, categoryId, subCategoryId);
	}
	
	public List<Product> getProductNameSuggesion(String title){
		return productDAO.getProductNameSuggesion(title);
	}
	
	public List<Product> getProductByUserId(String userId){
		return productReository.findByUserId(userId);
	}
}
