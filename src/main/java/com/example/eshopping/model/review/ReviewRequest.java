package com.example.eshopping.model.review;

import com.example.eshopping.entity.Review;

public class ReviewRequest {

	Review review;
	private String reviewId;
	private String productId;

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
}
