package com.example.eshopping.model.product;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductApproval;

public class ApprovalProductListResponse extends BaseResponse{

	List<Product> productApproval;

	public List<Product> getProductApproval() {
		return productApproval;
	}

	public void setProductApproval(List<Product> productApproval) {
		this.productApproval = productApproval;
	}
	
	
	
	
}
