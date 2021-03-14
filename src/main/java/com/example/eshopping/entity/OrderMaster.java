package com.example.eshopping.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderMaster {

	@Id
	private String id;
	private String orderId;
	private String productId;
	private String productName;
	private String productImg;
	private String quantity;
	private String price;
	private String sellerId;
	private String status;
	private String gst;
	private boolean returnPolicy;
	private String wayBillNumber;
	private String supplyerprice;
	private String dutytax;
	private String strikePrice;
	private String size;
	private String selectedSize;
	private String handlingFee;
	
	public String getWayBillNumber() {
		return wayBillNumber;
	}
	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}
	
	public boolean isReturnPolicy() {
		return returnPolicy;
	}
	public void setReturnPolicy(boolean returnPolicy) {
		this.returnPolicy = returnPolicy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSupplyerprice() {
		return supplyerprice;
	}
	public void setSupplyerprice(String supplyerprice) {
		this.supplyerprice = supplyerprice;
	}
	public String getDutytax() {
		return dutytax;
	}
	public void setDutytax(String dutytax) {
		this.dutytax = dutytax;
	}
	public String getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(String strikePrice) {
		this.strikePrice = strikePrice;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSelectedSize() {
		return selectedSize;
	}
	public void setSelectedSize(String selectedSize) {
		this.selectedSize = selectedSize;
	}
	public String getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(String handlingFee) {
		this.handlingFee = handlingFee;
	}
	

	
	
	
}
