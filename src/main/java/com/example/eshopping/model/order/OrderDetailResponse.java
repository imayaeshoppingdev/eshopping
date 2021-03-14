package com.example.eshopping.model.order;

import java.util.List;

import com.example.eshopping.entity.Order;
import com.example.eshopping.entity.OrderMaster;

public class OrderDetailResponse {

	Order orders;
	List<OrderMaster> orderMaster;

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public List<OrderMaster> getOrderMaster() {
		return orderMaster;
	}

	public void setOrderMaster(List<OrderMaster> orderMaster) {
		this.orderMaster = orderMaster;
	}
	
	
}
