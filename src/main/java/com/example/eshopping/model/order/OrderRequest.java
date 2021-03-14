package com.example.eshopping.model.order;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.eshopping.entity.Cart;
import com.example.eshopping.entity.OrderMaster;

public class OrderRequest {

	private String id;
	@NotEmpty
	private String userId;
	@NotEmpty
	private String addressId;
	
	@NotEmpty
	private String paymentMethod;
	@NotEmpty
	private String orderStatus;
	private double shippingCharge;
	private List<Cart> orderProduct;
	private String currency;
	private String sellerId;
	private boolean directOrder;
	List<DirectOrder> directOrderList;
	private String orderType;
	private String wayBillNumber;
	private String selectedSize;
	private String handlingFee;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	public List<Cart> getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(List<Cart> orderProduct) {
		this.orderProduct = orderProduct;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public boolean isDirectOrder() {
		return directOrder;
	}
	public void setDirectOrder(boolean directOrder) {
		this.directOrder = directOrder;
	}
	public List<DirectOrder> getDirectOrderList() {
		return directOrderList;
	}
	public void setDirectOrderList(List<DirectOrder> directOrderList) {
		this.directOrderList = directOrderList;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getWayBillNumber() {
		return wayBillNumber;
	}
	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
