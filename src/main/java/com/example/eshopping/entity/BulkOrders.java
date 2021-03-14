package com.example.eshopping.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BulkOrders {

	@Id
	private String id;
	private String productId;
	private String productName;
	private String quantity;
	private BulkOrderAddress buyerData;
	private BulkOrderAddress sellerData;
	private String status;
	private String buyerId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public BulkOrderAddress getBuyerData() {
		return buyerData;
	}
	public void setBuyerData(BulkOrderAddress buyerData) {
		this.buyerData = buyerData;
	}
	public BulkOrderAddress getSellerData() {
		return sellerData;
	}
	public void setSellerData(BulkOrderAddress sellerData) {
		this.sellerData = sellerData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	
	
	
}
