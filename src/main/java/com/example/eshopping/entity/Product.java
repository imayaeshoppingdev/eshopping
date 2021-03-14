package com.example.eshopping.entity;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.eshopping.model.product.SliderImage;
import com.google.gson.JsonObject;

@Document
public class Product {

	@Id
	private String id;
	@NotEmpty
	private String productName;
	private String title;
	private String description;
	private String prodImage;
	private String prodAlt;
	@Min(1)
	private int quantity;
//	private String code;
	private int isActive;
	private String color;
	private double rating;
	private double price;
	private double offerPrice;
	private boolean bulkOrders;
	private String categoryId;
	private String categoryName;
	private String subCategoryId;
	private String productListingId;
	private String productListing;
	private String userId;
	private String userName;
	private String brand;
	private String os;
	private boolean cod;
	private List<SliderImage> slideImage;
	private String filterType;
	private String filterName;
	private String point1;
	private String point2;
	private String point3;
	private String point4;
	private String point5;
	private String point6;
	private String specification;
	private String minValue;
	private String maxValue;
	private String discount;
	private String quantityLimits;
	private String weight;
	private boolean imageVerified;
	private boolean returnPolicy;
	private boolean serviceAndRepair;
	private String gst;
	private String createdDate;
	private String status;
	private String displayLocation;
	private String bulkorderMinQuantity;
	private String bulkorderMaxQuantity;
	private String bulkorderPrice;
	private String bulkorderOfferPrice;
	private String keyword;
	private String supplyerprice;
	private String dutytax; 
	private String strikePrice;
	private String size;
	private String handlingFee;
	private String deliveryPartner;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProdImage() {
		return prodImage;
	}
	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	public String getProdAlt() {
		return prodAlt;
	}
	public void setProdAlt(String prodAlt) {
		this.prodAlt = prodAlt;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public boolean isBulkOrders() {
		return bulkOrders;
	}
	public void setBulkOrders(boolean bulkOrders) {
		this.bulkOrders = bulkOrders;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getProductListingId() {
		return productListingId;
	}
	public void setProductListingId(String productListingId) {
		this.productListingId = productListingId;
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
	public List<SliderImage> getSlideImage() {
		return slideImage;
	}
	public void setSlideImage(List<SliderImage> slideImage) {
		this.slideImage = slideImage;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getPoint1() {
		return point1;
	}
	public void setPoint1(String point1) {
		this.point1 = point1;
	}
	public String getPoint2() {
		return point2;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPoint2(String point2) {
		this.point2 = point2;
	}
	public String getPoint3() {
		return point3;
	}
	public void setPoint3(String point3) {
		this.point3 = point3;
	}
	public String getPoint4() {
		return point4;
	}
	public void setPoint4(String point4) {
		this.point4 = point4;
	}
	public String getPoint5() {
		return point5;
	}
	public void setPoint5(String point5) {
		this.point5 = point5;
	}
	public String getPoint6() {
		return point6;
	}
	public void setPoint6(String point6) {
		this.point6 = point6;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getQuantityLimits() {
		return quantityLimits;
	}
	public void setQuantityLimits(String quantityLimits) {
		this.quantityLimits = quantityLimits;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public boolean isImageVerified() {
		return imageVerified;
	}
	public void setImageVerified(boolean imageVerified) {
		this.imageVerified = imageVerified;
	}
	public boolean isReturnPolicy() {
		return returnPolicy;
	}
	public void setReturnPolicy(boolean returnPolicy) {
		this.returnPolicy = returnPolicy;
	}
	public boolean isServiceAndRepair() {
		return serviceAndRepair;
	}
	public void setServiceAndRepair(boolean serviceAndRepair) {
		this.serviceAndRepair = serviceAndRepair;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDisplayLocation() {
		return displayLocation;
	}
	public void setDisplayLocation(String displayLocation) {
		this.displayLocation = displayLocation;
	}
	public String getBulkorderMinQuantity() {
		return bulkorderMinQuantity;
	}
	public void setBulkorderMinQuantity(String bulkorderMinQuantity) {
		this.bulkorderMinQuantity = bulkorderMinQuantity;
	}
	public String getBulkorderMaxQuantity() {
		return bulkorderMaxQuantity;
	}
	public void setBulkorderMaxQuantity(String bulkorderMaxQuantity) {
		this.bulkorderMaxQuantity = bulkorderMaxQuantity;
	}
	public String getBulkorderPrice() {
		return bulkorderPrice;
	}
	public void setBulkorderPrice(String bulkorderPrice) {
		this.bulkorderPrice = bulkorderPrice;
	}
	public String getBulkorderOfferPrice() {
		return bulkorderOfferPrice;
	}
	public void setBulkorderOfferPrice(String bulkorderOfferPrice) {
		this.bulkorderOfferPrice = bulkorderOfferPrice;
	}
	public String getProductListing() {
		return productListing;
	}
	public void setProductListing(String productListing) {
		this.productListing = productListing;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
