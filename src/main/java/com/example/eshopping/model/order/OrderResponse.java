package com.example.eshopping.model.order;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Order;

public class OrderResponse extends BaseResponse{

	Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}
