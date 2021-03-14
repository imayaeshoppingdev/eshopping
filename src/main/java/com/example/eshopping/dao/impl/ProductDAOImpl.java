package com.example.eshopping.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.eshopping.dao.ProductDAO;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.model.product.ProductListResponse;
import com.example.eshopping.model.product.ProductRequest;
import com.example.eshopping.util.JSONUtil;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	MongoTemplate mongoTemplate;

//	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://eshopping:springBootReact@18.224.18.89:27017/eshopping"));
//	DB database = mongoClient.getDB("eshopping");
//	DBCollection  coll = database.getCollection("product");
	
	@Override
	public ProductListResponse getAllProducts(String category,String subCategory, int skip, String role, String sort, String displayLocation){
		ProductListResponse response = new ProductListResponse();
		List<Product> products = new ArrayList<>();
		try {
			Query query = new Query();
//			int count = mongoTemplate.count(query, entityClass)
			if(category != null && !category.isEmpty())
				query.addCriteria(Criteria.where("categoryId").is(category));
			if(subCategory != null && !subCategory.isEmpty()) {
				query.addCriteria(Criteria.where("subCategoryId").is(subCategory));
			}
			if(!role.equalsIgnoreCase("Admin"))
				query.addCriteria(Criteria.where("isActive").is(1));
			
			query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(sort != null) {
				if(sort.equalsIgnoreCase("ASC")) {
					query.with(Sort.by(Sort.Direction.ASC, "price"));
				}
				else {
					query.with(Sort.by(Sort.Direction.DESC, "price"));
				}
			}
			if(displayLocation.equalsIgnoreCase("Both")) {
				query.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(displayLocation.equalsIgnoreCase("India")) {
				query.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(displayLocation.equalsIgnoreCase("SriLanka")) {
				query.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			System.out.println("display "+query);
			int count = mongoTemplate.find(query, Product.class).size();
			if(skip > 1) {
				skip = (skip - 1) * 15;
				query.skip(skip);
			}
			query.limit(15);
			System.out.println("page "+skip+" query "+query);
			products = mongoTemplate.find(query, Product.class);
			response.setProducts(products);
			response.setCount(count);
			return response;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@Override
	public ProductListResponse getProductUsingFilter(ProductRequest productRequest){
		ProductListResponse response = new ProductListResponse();
		System.out.println(JSONUtil.toJson(productRequest));
		List<Product> products = new ArrayList<>();
		Query query = new Query();
		
		if(productRequest.getCategory() != null && !productRequest.getCategory().isEmpty()) {
			query.addCriteria(Criteria.where("categoryId").is(productRequest.getCategory()));
		}
		if(productRequest.getSubCategory() != null && !productRequest.getSubCategory().isEmpty()) {
			query.addCriteria(Criteria.where("subCategoryId").is(productRequest.getSubCategory()));
		}
		if(productRequest.getRating() != 0) {
			query.addCriteria(Criteria.where("rating").gte(productRequest.getRating()));
		}
		if(productRequest.getMaxPrice() != 0) {
			query.addCriteria(Criteria.where("offerPrice").gte(productRequest.getMinPrice()).lte(productRequest.getMaxPrice()));
		}
		if(productRequest.isCod())
			query.addCriteria(Criteria.where("cod").gte(productRequest.isCod()));
		System.out.println(1);
		if(productRequest.getFilterType() != null && !productRequest.getFilterType().isEmpty()) {
			System.out.println(2);
			query.addCriteria(Criteria.where("filterType").is(productRequest.getFilterType()));
		}
		if(productRequest.getFilterName() != null && !productRequest.getFilterName().isEmpty()) {
			query.addCriteria(Criteria.where("filterName").is(productRequest.getFilterName()));
		}
		System.out.println("brand "+productRequest.getBrand());
		if(productRequest.getBrand() != null && !productRequest.getBrand().isEmpty()) {
		String brandValues[] = productRequest.getBrand().split(",");
		
			List<String> brandList = new ArrayList<>();
			for(String value : brandValues) {
				brandList.add(value);
			}
			System.out.println("brand "+brandList);
			query.addCriteria(Criteria.where("brand").in(brandList));
		}
		if(productRequest.getOs() != null && !productRequest.getOs().isEmpty()) {
		String osValues[] = productRequest.getOs().split(",");
		List<String> osList = new ArrayList<>();
			for(String value : osValues) {
				osList.add(value);
			}
			query.addCriteria(Criteria.where("os").in(osList));
		}
		if(productRequest.getColor() != null && !productRequest.getColor().isEmpty()) {
			String colorValues[] = productRequest.getColor().split(",");
			List<String> coloorList = new ArrayList<>();
			for(String value : colorValues) {
				coloorList.add(value);
			}
			query.addCriteria(Criteria.where("color").in(coloorList));
		}
		if(productRequest.getWeight() != null && !productRequest.getWeight().isEmpty()) {
			String weightValues[] = productRequest.getWeight().split(",");
			List<String> weightList = new ArrayList<>();
			for(String value : weightValues) {
				weightList.add(value);
			}
			query.addCriteria(Criteria.where("weight").in(weightList));
		}
		if(productRequest.isCod()) {
			query.addCriteria(Criteria.where("cod").in(true));
		}
		query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
				Criteria.where("status").ne("Rejected")));
		if(productRequest.getDisplayLocation().equalsIgnoreCase("Both")) {
			query.addCriteria(Criteria.where("displayLocation").is("Both"));
		}
		else if(productRequest.getDisplayLocation().equalsIgnoreCase("India")) {
			query.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
		}
		else if(productRequest.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
			query.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
		}
		query.addCriteria(Criteria.where("isActive").is(1));
		long count = mongoTemplate.count(query, "product");
		System.out.println("count "+count);
		int skip = 0;
		if(productRequest.getPage() > 1) {
			skip = (productRequest.getPage() - 1) * 15;
			query.skip(skip);
		}
		query.limit(15);
		System.out.println("query "+query);
		products = mongoTemplate.find(query, Product.class);
		System.out.println("getBrand "+JSONUtil.toJson(products));
		response.setProducts(products);
		response.setCount(count);
		return response;
	}
	
	@Override
	public List<Product> getProductTitleForSuggestion(String category, String subCategory,String title, String displayLocation){
		List<Product> product = new ArrayList<>();
	
		try {
			Query query = new Query();
			
			if(category != null)
				query.addCriteria(Criteria.where("categoryId").is(category));
			if(subCategory != null) {
				query.addCriteria(Criteria.where("subCategoryId").is(subCategory));
			}
			query.addCriteria(Criteria.where("title").regex("^"+title,"i"));
			query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(displayLocation.equalsIgnoreCase("Both")) {
				query.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(displayLocation.equalsIgnoreCase("India")) {
				query.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(displayLocation.equalsIgnoreCase("SriLanka")) {
				query.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			query.addCriteria(Criteria.where("isActive").is(1));
			query.limit(5);
			query.fields().include("id").include("title").include("categoryId").include("subCategoryId").include("productListingId");
			
			System.out.println(query);
			product = mongoTemplate.find(query, Product.class);
//			
//			JSONArray jsonArray = new JSONArray();
//			BasicDBObject criteria = new BasicDBObject();
//			BasicDBObject projections = new BasicDBObject();
//			projections.put("_id", 0);
//			projections.put("productName", 1);
//			projections.put("categoryId", 1);
//			projections.put("subCategoryId", 1);
//			projections.put("productListingId", 1);
//			criteria.append("title", "^"+title).put("$options","i");
//			
//			DBCursor cursor = coll.find(criteria, projections);
//			 while(cursor.hasNext()) {
//				    BasicDBObject obj = (BasicDBObject) cursor.next();
//				    JSONObject jsonobj = new JSONObject();
//				    jsonobj.put("productName", obj.getString("productName"));
//				    jsonobj.put("categoryId", obj.getString("categoryId"));
//				    jsonobj.put("subCategoryId", obj.getString("subCategoryId"));
//				    jsonobj.put("productListingId", obj.getString("productListingId"));
////				    jsonobj.put("Status", obj.getString("Status"));
//				    jsonArray.put(jsonobj);
//				  }
//			 System.out.println("JSON "+jsonArray);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return product;
	}
	
	@Override
	public Map<String, Set<String>> getFilterData(ProductRequest request){
		Map<String, Set<String>> filterData = new HashMap<>();
		try {
			Query query = new Query();
			query = getConditionalQuery(request.getCategory(), request.getSubCategory(), request.getProductListing());
			query.addCriteria(Criteria.where("Brand").ne(null));
			query.fields().include("Brand");
			query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(request.getDisplayLocation().equalsIgnoreCase("Both")) {
				query.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("India")) {
				query.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
				query.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			query.addCriteria(Criteria.where("isActive").is(1));
			List<Product> filterBrand = mongoTemplate.find(query, Product.class);
			
			Set<String> resultBrand = filterBrand.stream().map(a -> a.getBrand()).collect(Collectors.toSet());
			
			Query typeQuery = new Query();
			typeQuery.fields().include("filterType");
			typeQuery = getConditionalQuery(request.getCategory(), request.getSubCategory(), request.getProductListing());
			typeQuery.addCriteria(Criteria.where("filterType").ne(null));
			typeQuery.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(request.getDisplayLocation().equalsIgnoreCase("Both")) {
				typeQuery.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("India")) {
				typeQuery.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
				typeQuery.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			typeQuery.addCriteria(Criteria.where("isActive").is(1));
			List<Product> filterType = mongoTemplate.find(typeQuery, Product.class);
			Set<String> resultType = filterType.stream().map(a -> a.getFilterType()).collect(Collectors.toSet());
			
			Query nameQuery = new Query();
			nameQuery.fields().include("filterName");
			nameQuery = getConditionalQuery(request.getCategory(), request.getSubCategory(), request.getProductListing());
			nameQuery.addCriteria(Criteria.where("filterName").ne(null));
			nameQuery.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(request.getDisplayLocation().equalsIgnoreCase("Both")) {
				nameQuery.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("India")) {
				nameQuery.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
				nameQuery.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			nameQuery.addCriteria(Criteria.where("isActive").is(1));
			List<Product> filterName = mongoTemplate.find(nameQuery, Product.class);
			Set<String> resultName = filterName.stream().map(a -> a.getFilterName()).collect(Collectors.toSet());
			
			Query colorQuery = new Query();
			colorQuery.fields().include("color");
			colorQuery = getConditionalQuery(request.getCategory(), request.getSubCategory(), request.getProductListing());
			colorQuery.addCriteria(Criteria.where("color").ne(null));
			colorQuery.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(request.getDisplayLocation().equalsIgnoreCase("Both")) {
				colorQuery.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("India")) {
				colorQuery.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
				colorQuery.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			colorQuery.addCriteria(Criteria.where("isActive").is(1));
			List<Product> color = mongoTemplate.find(colorQuery, Product.class);
			Set<String> resultColor = color.stream().map(a -> a.getColor()).collect(Collectors.toSet());
			
			Query weightQuery = new Query();
			weightQuery.fields().include("weight");
			weightQuery = getConditionalQuery(request.getCategory(), request.getSubCategory(), request.getProductListing());
			weightQuery.addCriteria(Criteria.where("weight").ne(null));
			weightQuery.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			if(request.getDisplayLocation().equalsIgnoreCase("Both")) {
				weightQuery.addCriteria(Criteria.where("displayLocation").is("Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("India")) {
				weightQuery.addCriteria(Criteria.where("displayLocation").in("India", "Both"));
			}
			else if(request.getDisplayLocation().equalsIgnoreCase("SriLanka")) {
				weightQuery.addCriteria(Criteria.where("displayLocation").in("SriLanka", "Both"));
			}
			weightQuery.addCriteria(Criteria.where("isActive").is(1));
			List<Product> weight = mongoTemplate.find(weightQuery, Product.class);
			Set<String> resultweight = weight.stream().map(a -> a.getWeight()).collect(Collectors.toSet());
			
			filterData.put("Brand",resultBrand);
			filterData.put("FilterType", resultType);
			filterData.put("FilterName", resultName);
			filterData.put("Color", resultColor);
			filterData.put("Weight", resultweight);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return filterData;
	}
	
	@Override
	public List<Product> getProductFilterAdmin(ProductRequest request){
		List<Product> product = new ArrayList<>();
		try {
			Query query = new Query();
			if(request.getCategory() != null)
				query.addCriteria(Criteria.where("categoryId").is(request.getCategory()));
			if(request.getSubCategory() != null) {
				query.addCriteria(Criteria.where("subCategoryId").is(request.getSubCategory()));
			}
			if(request.getProductListing() != null) {
				query.addCriteria(Criteria.where("productListingId").is(request.getProductListing()));
			}
			if(request.getUserId() != null) {
				query.addCriteria(Criteria.where("userId").is(request.getUserId()));
			}
			else {
				query.addCriteria(Criteria.where("isActive").is(1));
			}
			query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne("Pending"),
					Criteria.where("status").ne("Rejected")));
			
			product = mongoTemplate.find(query, Product.class);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return product;
	}
	
	public List<Product> getProductApproval(String userId, String role, String status){
		List<Product> product = new ArrayList<>();
		try {
			Query query = new Query();
			if(!role.equalsIgnoreCase("Admin")) {
				query.addCriteria(Criteria.where("userId").is(userId));
				query.addCriteria(Criteria.where("status").is(status));
			}else {
				query.addCriteria(Criteria.where("status").is("Pending"));
			}
			product = mongoTemplate.find(query, Product.class);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return product;
	}
	
	public Query getConditionalQuery(String categoryId, String subCategoryId, String productId) {
		Query query = new Query();
		if(categoryId != null)
			query.addCriteria(Criteria.where("categoryId").is(categoryId));
		if(subCategoryId != null) {
			query.addCriteria(Criteria.where("subCategoryId").is(subCategoryId));
		}
		if(productId != null) {
			query.addCriteria(Criteria.where("productListingId").is(productId));
		}
		return query;
	}
	
	public List<ProductListing> getProductListingSuggesion(String title, String categoryId, String subCategoryId){
		List<ProductListing> suggesion = new ArrayList<>();
		try {
			Query query = new Query();
			if(categoryId != null)
				query.addCriteria(Criteria.where("categoryId").is(categoryId));
			if(subCategoryId != null) {
				query.addCriteria(Criteria.where("subCategoryId").is(subCategoryId));
			}
			query.addCriteria(Criteria.where("productListing").regex("^"+title,"i"));
//			query.addCriteria(Criteria.where("isActive").is(1));
			query.limit(5);
			suggesion = mongoTemplate.find(query, ProductListing.class);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return suggesion;
	}
	
	public List<Product> getProductNameSuggesion(String title){
		List<Product> suggesion = new ArrayList<>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("productName").regex("^"+title,"i"));
			query.addCriteria(Criteria.where("isActive").is(1));
			query.limit(5);
			suggesion = mongoTemplate.find(query, Product.class);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return suggesion;
	}
	
	public Query locationQuery(String displayLocation) {
		Query query = new Query();
		if(displayLocation.equalsIgnoreCase("Both")) {
			query.addCriteria(Criteria.where("displayLocation").is("Both"));
		}
		else if(displayLocation.equalsIgnoreCase("India")) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("displayLocation").is("India"),
					Criteria.where("displayLocation").is("Both")));
		}
		else if(displayLocation.equalsIgnoreCase("SriLanka")) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("displayLocation").is("SriLanka"),
					Criteria.where("displayLocation").is("Both")));
		}
		return query;
	}
	
	public List<Product>  getBulkOrders(String displayLocation){
		List<Product> product = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("bulkOrders").is(true));
		if(displayLocation.equalsIgnoreCase("Both")) {
			query.addCriteria(Criteria.where("displayLocation").is("Both"));
		}
		else if(displayLocation.equalsIgnoreCase("India")) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("displayLocation").is("India"),
					Criteria.where("displayLocation").is("Both")));
		}
		else if(displayLocation.equalsIgnoreCase("SriLanka")) {
			query.addCriteria(new Criteria().orOperator(Criteria.where("displayLocation").is("SriLanka"),
					Criteria.where("displayLocation").is("Both")));
		}
		query.addCriteria(Criteria.where("isActive").is(1));
		product = mongoTemplate.find(query, Product.class);
		return product;
	}
	
//	public List<ProductListing> getProductListing(String product){
//		List<ProductListing> productListing = new ArrayList<>()
//	}
}
