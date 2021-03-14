package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Cart;

public interface CartService {

	public Cart saveCart(Cart cart);
	
	public Cart getCartByProductId(String productId, String userId);
	
	public List<Cart> getCartListByUser(String userId);
	
	public Integer deleteCart(String id);
	
	public Cart finndCartById(String id);
	
	public Long countCartItemsByUserId(String userId);
	
	public List<Cart> getCartByProductId(String productId);
	
}
