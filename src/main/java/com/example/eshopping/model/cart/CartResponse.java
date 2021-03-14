package com.example.eshopping.model.cart;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Cart;

public class CartResponse extends BaseResponse{

	Cart cart;
	List<Cart> carts;
	private long count;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
}
