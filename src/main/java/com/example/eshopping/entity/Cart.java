package com.example.eshopping.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {

	@Id
	private String id;
	@NotEmpty
	private String productId;
	private String title;
	@NotEmpty
	private String productName;
	private String description;
	private String prodAlt;
	private String prodImage;
	@Min(1)
	private int quantity;
//	private String code;
//	private int isActive;
	private double rating;
	private double price;
	private double offerPrice;
	private String category;
	private String subCategory;
	@NotEmpty
	private String userId;
	private String brand;
	private String os;
	private boolean cod;
	private String gst;
	@NotEmpty
	private String cartUserId;
	private String supplyerprice;
	private String dutytax; 
	private String selectedSize;
	private String size;
	private String handlingFee;
	private String deliveryPartner;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProdAlt() {
		return prodAlt;
	}
	public void setProdAlt(String prodAlt) {
		this.prodAlt = prodAlt;
	}
	public String getProdImage() {
		return prodImage;
	}
	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public boolean isCod() {
		return cod;
	}
	public void setCod(boolean cod) {
		this.cod = cod;
	}
	public String getCartUserId() {
		return cartUserId;
	}
	public void setCartUserId(String cartUserId) {
		this.cartUserId = cartUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
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
	public String getSelectedSize() {
		return selectedSize;
	}
	public void setSelectedSize(String selectedSize) {
		this.selectedSize = selectedSize;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(String handlingFee) {
		this.handlingFee = handlingFee;
	}
	public String getDeliveryPartner() {
		return deliveryPartner;
	}
	public void setDeliveryPartner(String deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}
	
	
	
}
