package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.WishList;

public interface WishListService {

	public WishList saveWishList(WishList wishList);
	
	public WishList getWishListByProductId(String productId);
	
	public List<WishList> getWishListByUserId(String userId);
}
