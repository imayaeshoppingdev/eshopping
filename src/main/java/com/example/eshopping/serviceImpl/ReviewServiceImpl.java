package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.dao.ReviewDAO;
import com.example.eshopping.entity.Review;
import com.example.eshopping.repo.ReviewRepository;
import com.example.eshopping.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewDAO reviewDAO;
	
	@Override
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}
	
	@Override
	public List<Review> getReviewByProductId(String productId){
		return reviewRepository.findByProductId(productId);
	}
	
	@Override
	public int deleteReview(String reviewId) {
		System.out.println("delete cart ");
		return reviewRepository.deleteById(reviewId);
	}
	
	@Override
	public double getRatingAverage(String productId) {
		return reviewDAO.getRatingAverage(productId);
	}
	
	@Override
	public Review getReviewByUserIdAndProductId(String userId, String productId) {
		return reviewRepository.findByUserIdAndProductId(userId, productId);
	}
	
}
