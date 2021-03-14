package com.example.eshopping.model.product;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.NewProduct;

public class NewProductResponse extends BaseResponse{

	List<NewProduct> newProduct;

	public List<NewProduct> getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(List<NewProduct> newProduct) {
		this.newProduct = newProduct;
	}
	
	
}
