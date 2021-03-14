package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.entity.WishList;
import com.example.eshopping.repo.WishListRepository;
import com.example.eshopping.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	WishListRepository wishListReposiotry;
	
	@Override
	public WishList saveWishList(WishList wishList) {
		return wishListReposiotry.save(wishList);
	}
	
	@Override
	public WishList getWishListByProductId(String productId) {
		return wishListReposiotry.findByProductId(productId);
	}
	
	@Override
	public List<WishList> getWishListByUserId(String userId){
		return wishListReposiotry.findByWishListUserId(userId);
	}
}
