package com.example.eshopping.model.review;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.Review;

public class ReviewListResponse extends BaseResponse{

	List<Review> reviews;

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
}
