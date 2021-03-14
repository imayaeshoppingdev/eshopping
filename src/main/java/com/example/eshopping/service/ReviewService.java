package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Review;

public interface ReviewService {

	public Review saveReview(Review review);
	
	public List<Review> getReviewByProductId(String productId);
	
	public int deleteReview(String reviewId);
	
	public double getRatingAverage(String productId);
	
	public Review getReviewByUserIdAndProductId(String userId, String productId);
}
