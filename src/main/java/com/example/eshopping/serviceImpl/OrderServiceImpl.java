package com.example.eshopping.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Address;
import com.example.eshopping.entity.Cart;
import com.example.eshopping.entity.CurrencyMaintain;
import com.example.eshopping.entity.Order;
import com.example.eshopping.entity.OrderMaster;
import com.example.eshopping.entity.Product;
import com.example.eshopping.model.order.DirectOrder;
import com.example.eshopping.model.order.OrderRequest;
import com.example.eshopping.model.order.OrderResponse;
import com.example.eshopping.repo.CurrencyMaintainReposiotry;
import com.example.eshopping.repo.OrderMasterReposiotry;
import com.example.eshopping.repo.OrderRepository;
import com.example.eshopping.service.AddressService;
import com.example.eshopping.service.CartService;
import com.example.eshopping.service.OrderService;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.util.DateFormat;
import com.example.eshopping.util.JSONUtil;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	OrderMasterReposiotry orderMasterRepository;
	
	@Autowired
	CurrencyMaintainReposiotry currencyMaintainRepository;
	
	@Autowired
	CartService cartService;
	
	@Override
	public OrderResponse saveOrder(OrderRequest orderRequest) {
		OrderResponse response = new OrderResponse();
		System.out.println("SJON  "+JSONUtil.toJson(orderRequest));
		double totalPrice = 0.00;
		Address address = addressService.findAddressById(orderRequest.getAddressId());
		if(address == null) {
			response.setMessage("Invalid Address");
			response.setStatusCode(1);
			response.setStatus(CommonConstant.ERROR);
			return response;
		}
		List<OrderMaster> orderMasterSave = new ArrayList<>();
		if(orderRequest.isDirectOrder()) {
			for(Cart cartMaster : orderRequest.getOrderProduct()) {
				Product product = productService.getProductById(cartMaster.getProductId());
				OrderMaster master = new OrderMaster();
				if(product == null) {
					response.setMessage("Invalid Product");
					response.setStatusCode(1);
					response.setStatus(CommonConstant.ERROR);
					return response;
				} 
				master.setStatus("InProgress");
				master.setSellerId(product.getUserId());
				master.setSupplyerprice(product.getSupplyerprice());
				master.setDutytax(product.getDutytax());
				master.setQuantity(String.valueOf(cartMaster.getQuantity()));
				master.setProductId(cartMaster.getProductId());
				master.setProductName(cartMaster.getProductName());
				master.setProductImg(product.getProdImage());
				master.setReturnPolicy(product.isReturnPolicy());
				master.setWayBillNumber(orderRequest.getWayBillNumber());
				master.setStrikePrice(product.getStrikePrice());
				master.setSize(product.getSize());
				double price = product.getOfferPrice() * Integer.parseInt(master.getQuantity());
				totalPrice+=price;
				master.setPrice(String.valueOf(price));
				master.setHandlingFee(cartMaster.getHandlingFee());
				master.setSelectedSize(cartMaster.getSelectedSize());
				orderMasterSave.add(master);
				cartService.deleteCart(cartMaster.getId());
			}
		}
		else {
			for(DirectOrder direct : orderRequest.getDirectOrderList()) {
				Product product = productService.getProductById(direct.getProductId());
				OrderMaster master = new OrderMaster();
				if(product == null) {
					response.setMessage("Invalid Product");
					response.setStatusCode(1);
					response.setStatus(CommonConstant.ERROR);
					return response;
				} 
				master.setStatus("InProgress");
				master.setSellerId(product.getUserId());
				master.setQuantity(String.valueOf(direct.getQuantity()));
				master.setProductId(product.getId());
				master.setProductName(product.getProductName());
				master.setProductImg(product.getProdImage());
				master.setSupplyerprice(product.getSupplyerprice());
				master.setDutytax(product.getDutytax());
				master.setWayBillNumber(orderRequest.getWayBillNumber());
				master.setStrikePrice(product.getStrikePrice());
				master.setSize(product.getSize());
				String priceInString = null;
				if(orderRequest.getOrderType().equalsIgnoreCase("bulkOrder")) {
					priceInString = product.getBulkorderOfferPrice();
				}
				else {
					priceInString = direct.getPrice();
				}
				double price = Double.valueOf(priceInString) * Integer.parseInt(direct.getQuantity());
				totalPrice+=price;
				master.setPrice(String.valueOf(price));
				master.setHandlingFee(orderRequest.getHandlingFee());
				master.setSelectedSize(orderRequest.getSelectedSize());
				orderMasterSave.add(master);
			}
		}
 		
		System.out.println("address "+JSONUtil.toJson(address));
		if(orderRequest.getShippingCharge() > 0) {
			totalPrice+=orderRequest.getShippingCharge();
		}
		CurrencyMaintain currency = currencyMaintainRepository.findById(orderRequest.getCurrency());
		BigDecimal bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
		System.out.println("prize "+totalPrice+" doyuble "+bd.doubleValue());
		Order order = new Order();
		order.setShippingAddress(address);
		order.setTotalPrice(bd.doubleValue());
		order.setPaymentMethod(orderRequest.getPaymentMethod());
		order.setOrderStatus(orderRequest.getOrderStatus());
		order.setOrderDate(DateFormat.getTodayString());
		order.setWayBillNumber(orderRequest.getWayBillNumber());
		
		System.out.println("date  String "+DateFormat.getTodayString());
		order.setOrderUserId(orderRequest.getUserId());
		System.out.println("userId  ");
		order.setShippingCharge(orderRequest.getShippingCharge());
		order.setCurrency(orderRequest.getCurrency());
		order.setCurrencyValue(currency.getCurrencyRate());
		order = orderRepository.save(order);
		for(OrderMaster master : orderMasterSave) {
			master.setOrderId(order.getId());
			orderMasterRepository.save(master);
		}
		System.out.println("date  "+new Date());
		return  response;
	}
	
	@Override
	public List<Order> getOrderByUserId(String userId){
		return orderRepository.findByOrderUserId(userId);
	}
	
	@Override
	public Order getOrderById(String id) {
		return orderRepository.findById(id);
	}
	
	@Override
	public List<OrderMaster> getOrderMasterByOrderId(String orderId){
		return orderMasterRepository.findByOrderId(orderId);
	}
	
	@Override
	public OrderMaster getOrderMasterById(String orderId){
		return orderMasterRepository.findById(orderId);
	}
	
	public List<OrderMaster> getOrderBySellerIdAndStatus(String sellerId, String status){
		return orderMasterRepository.findBySellerIdAndStatus(sellerId, status);
	}
	
	public Order saveOrderDirectly(Order order) {
		return orderRepository.save(order);
	}
}
