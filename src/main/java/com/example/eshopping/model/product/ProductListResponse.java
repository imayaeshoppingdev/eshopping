package com.example.eshopping.model.product;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Product;

public class ProductListResponse extends BaseResponse{

	List<Product> products;
	List<Suggesion> suggesion;
	long count;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Suggesion> getSuggesion() {
		return suggesion;
	}

	public void setSuggesion(List<Suggesion> suggesion) {
		this.suggesion = suggesion;
	}
	
	
}
