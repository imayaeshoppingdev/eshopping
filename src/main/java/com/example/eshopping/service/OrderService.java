package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Order;
import com.example.eshopping.entity.OrderMaster;
import com.example.eshopping.model.order.OrderRequest;
import com.example.eshopping.model.order.OrderResponse;

public interface OrderService {

	public OrderResponse saveOrder(OrderRequest order);
	
	public List<Order> getOrderByUserId(String userId);
	
	public Order getOrderById(String id);
	
	
	public List<OrderMaster> getOrderMasterByOrderId(String orderId);
	

	public OrderMaster getOrderMasterById(String orderId);
	
	public List<OrderMaster> getOrderBySellerIdAndStatus(String sellerId, String status);
	
	public Order saveOrderDirectly(Order order);
	
	public OrderMaster saveOrderMaster(OrderMaster order);
}
