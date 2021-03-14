package com.example.eshopping.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.example.eshopping.dao.ReviewDAO;
import com.example.eshopping.entity.Review;
import com.mongodb.DBObject;

@Repository
public class ReviewDAOImpl implements ReviewDAO{

	@Autowired
	MongoTemplate mongoTemplate;
	
	public double getRatingAverage(String productId) {
		double rating = 0;
		Criteria c1 = new Criteria();
		c1.where("productId").is(productId);
		TypedAggregation<Review> agg = newAggregation(Review.class,
				match(c1),
			      group("productId")            
			        .avg("rating").as("avgRating"),
			      limit(1)
			);

			AggregationResults<DBObject> result = mongoTemplate.aggregate(agg, DBObject.class);
			List<DBObject> resultList = result.getMappedResults();
			for(DBObject value : resultList) {
				System.out.println(value);
				String ratingDouble = value.get("avgRating").toString();
				rating = Double.parseDouble(ratingDouble);
			}
		
		return rating;
	}
	
	
}
