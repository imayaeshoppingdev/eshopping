package com.example.eshopping.model.product;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Product;

public class ProductResponse extends BaseResponse{

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
