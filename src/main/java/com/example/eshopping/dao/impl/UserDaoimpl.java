package com.example.eshopping.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;

@Repository
public class UserDaoimpl {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		Query query = new Query();
		query.fields().exclude("password");
		users = mongoTemplate.find(query, User.class);
		return users;
	}
	
	public User findUserById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().exclude("password");
		User users = mongoTemplate.findOne(query, User.class);
		return users;
	}
	
	public List<User> getAadharApproval(){
		List<User> users = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("aadharNumber").ne(""));
		query.addCriteria(Criteria.where("aadharApproved").is(false));
		query.fields().exclude("password");
		users = mongoTemplate.find(query, User.class);
		return users;
	}
	
	public List<UserDetails> getGstInApproval(){
		List<UserDetails> userDetails = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("gstin").ne(""));
		query.addCriteria(Criteria.where("gstApproved").is(false));
//		query.fields().exclude("password");
		userDetails = mongoTemplate.find(query, UserDetails.class);
		return userDetails;
	}
}
