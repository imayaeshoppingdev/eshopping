package com.example.eshopping.model.product;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductListing;

public class ProductListingResponse extends BaseResponse{

	private List<Product> product;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
}
