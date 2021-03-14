package com.example.eshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.Review;
import com.example.eshopping.model.review.ReviewListResponse;
import com.example.eshopping.model.review.ReviewRequest;
import com.example.eshopping.model.review.ReviewResponse;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/addAndUpdateReview")
	public ReviewResponse addAndUpdateReview(@Valid @RequestBody ReviewRequest request) {
		ReviewResponse response = new ReviewResponse();
		try {
			Review checkReviewPresent = reviewService.getReviewByUserIdAndProductId(request.getReview().getUserId(), request.getReview().getProductId());
			if(checkReviewPresent == null || request.getReview().getId() != null) {
				Review review = reviewService.saveReview(request.getReview());
				updateRating(review.getProductId());
				response.setStatus(CommonConstant.SUCCESS);
			}
			else {
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("Review Already added");
			}
			
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setMessage("Review Failed");
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getReviews/{id}")
	public ReviewListResponse getReviewById(@PathVariable String id) {
		ReviewListResponse response = new ReviewListResponse();
		try {
			List<Review> reviews = reviewService.getReviewByProductId(id);
			if(reviews == null) {
				response.setStatusCode(1);
				response.setMessage("No Reviews");
				response.setStatus(CommonConstant.ERROR);
			}else {
				response.setReviews(reviews);
				response.setStatus(CommonConstant.SUCCESS);
			}
		}
		catch(Exception e) {
			response.setStatusCode(1);
			response.setMessage("Invalid Data");
			response.setStatus(CommonConstant.ERROR);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteReview")
	public BaseResponse deleteReview(@RequestBody ReviewRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			reviewService.deleteReview(request.getReviewId());
			System.out.println("delete "+request.getReviewId()+" product "+request.getProductId());
			
				updateRating(request.getProductId());
				response.setStatus(CommonConstant.SUCCESS);
			
		}
		catch(Exception e) {
			response.setStatusCode(1);
			response.setMessage("Invalid Data");
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	public void updateRating(String productId) {
		double rating = reviewService.getRatingAverage(productId);
		Product product = productService.getProductById(productId);
		System.out.println("product "+productId);
		product.setRating(rating);
		productService.saveProduct(product);
	}
}
