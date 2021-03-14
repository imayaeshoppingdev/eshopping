package com.example.eshopping.model.order;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Order;

public class OrderListResponse extends BaseResponse{

	List<OrderDetailResponse> orderResponse;

	public List<OrderDetailResponse> getOrderResponse() {
		return orderResponse;
	}

	public void setOrderResponse(List<OrderDetailResponse> orderResponse) {
		this.orderResponse = orderResponse;
	}
	
	
	
	
	
}
