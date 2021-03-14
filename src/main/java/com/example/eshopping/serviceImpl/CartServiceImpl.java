package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.entity.Cart;
import com.example.eshopping.repo.CartRepository;
import com.example.eshopping.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepository;
	
	@Override
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}
	
	@Override
	public Cart getCartByProductId(String productId, String userId) {
		return cartRepository.findByProductIdAndCartUserId(productId,userId);
	}
	
	@Override
	public List<Cart> getCartListByUser(String userId){
		return cartRepository.findByCartUserId(userId);
	}
	
	@Override
	public Integer deleteCart(String id) {
		return cartRepository.deleteById(id);
	}
	
	@Override
	public Cart finndCartById(String id) {
		return cartRepository.findById(id);
	}
	
	@Override
	public Long countCartItemsByUserId(String userId) {
		return cartRepository.countByCartUserId(userId);
	}
	
	@Override
	public List<Cart> getCartByProductId(String productId){
		return cartRepository.findByProductId(productId);
	}
}
