package com.example.eshopping.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.BulkOrders;

@Repository
public class BargainDAOImpl {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<BulkOrders> getBulkOrders(){
		Query query = new Query();
		query.addCriteria(Criteria.where("status").ne("Pending"));
		List<BulkOrders> bulkOrders = mongoTemplate.find(query, BulkOrders.class);
		return bulkOrders;
	}
}
