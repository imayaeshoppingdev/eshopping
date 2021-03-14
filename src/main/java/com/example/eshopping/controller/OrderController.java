package com.example.eshopping.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Order;
import com.example.eshopping.entity.OrderMaster;
import com.example.eshopping.model.order.OrderDetailResponse;
import com.example.eshopping.model.order.OrderListResponse;
import com.example.eshopping.model.order.OrderRequest;
import com.example.eshopping.model.order.OrderResponse;
import com.example.eshopping.model.order.RazerOrderRequest;
import com.example.eshopping.model.order.RazerOrderResponse;
import com.example.eshopping.service.AddressService;
import com.example.eshopping.service.OrderService;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.util.JSONUtil;
//import com.example.eshopping.util.PdfGenerator;
//import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class OrderController {

	@Autowired
	OrderService orderService;
	
//	@Autowired
//	PdfGenerator pdfGenerator;
	
	@Autowired
	ProductService productService;
	 
	@Autowired
	AddressService addressService;
	
	@PostMapping("/placeOrder")
	public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
		OrderResponse response = new OrderResponse();
		try {
			response = orderService.saveOrder(request);
			if(response.getStatus() == null || !response.getStatus().equals(CommonConstant.ERROR)) {
				response.setStatus(CommonConstant.SUCCESS);
				response.setMessage("Order Placed");
			}
		}
		catch(Exception e) {
			response.setMessage("Order Failed ");
			response.setStatusCode(1);
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getOrders/{id}")
	public OrderListResponse getOrderByUserId(@PathVariable String id) {
		OrderListResponse response = new OrderListResponse();
		try {
			List<Order> order = orderService.getOrderByUserId(id);
			List<OrderDetailResponse> orderListResponse = new ArrayList<>();
			if(order.isEmpty()) {
				response.setMessage("No orders yet");
			}
			else {
				for(Order orderValue : order) {
					OrderDetailResponse orderResponse = new OrderDetailResponse();
					List<OrderMaster> orderMaster = orderService.getOrderMasterByOrderId(orderValue.getId());
					orderResponse.setOrders(orderValue);
					orderResponse.setOrderMaster(orderMaster);
					orderListResponse.add(orderResponse);
				}
			}
			response.setOrderResponse(orderListResponse);
			response.setStatus(CommonConstant.SUCCESS);
		}
		catch(Exception e) {
			response.setMessage("Invalid Id");
			response.setStatusCode(1);
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getOrderDetails/{id}")
	public OrderResponse getOrderDetails(@PathVariable String id) {
		OrderResponse response = new OrderResponse();
		try {
			Order order = orderService.getOrderById(id);
			if(order != null) {
				response.setStatus(CommonConstant.SUCCESS);
				response.setMessage("Order");
				response.setOrder(order);
			}
			else {
				response.setMessage("Invalid Order Id ");
				response.setStatusCode(1);
				response.setStatus(CommonConstant.ERROR);
			}
		}
		catch(Exception e) {
			response.setMessage("Order Details Failed ");
			response.setStatusCode(1);
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
////	@GetMapping("/invoice/{id}")
////	public void getInvoice(@PathVariable String id, HttpServletResponse response) throws IOException{
////
////		OrderMaster orderMaster = orderService.getOrderMasterById(id);
////		Order order = orderService.getOrderById(orderMaster.getOrderId());
//////		Map<String, List<OrderMaster>> mapOrders = orderMaster.stream().collect(Collectors.groupingBy(OrderMaster::getSellerId));
//////		System.out.println(JSONUtil.toJson(mapOrders));
////		ByteArrayOutputStream outputValue[] = null;
////
////                try {
////
//////                	for(Map.Entry<String,List<OrderMaster>> entry : mapOrders.entrySet()) {
////
////            			ByteArrayOutputStream output = pdfGenerator.citiesReport(order, orderMaster);
////            			String headerKey = "Content-Disposition";
////            	        String headerValue = "attachment; filename="+order.getId()+".pdf";
////            	        response.setHeader(headerKey, headerValue);
////	            		response.setContentType("application/pdf");
////	                	output.writeTo(response.getOutputStream());
////
//////                	}
////                } catch (IOException e) {
////                    System.out.println(e);
////
////                }
//////            }
//////        }).start();
//
//
//		}
//		ByteArrayOutputStream output = pdfGenerator.citiesReport(order, orderMaster); 
//		 String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=users.pdf";
//	        response.setHeader(headerKey, headerValue);
//		response.setContentType("application/pdf");
//		output.writeTo(response.getOutputStream());



//	@PostMapping("/orderId")
//	public RazerOrderResponse orderRecipt(@RequestBody RazerOrderRequest request) {
//		RazerOrderResponse response = new RazerOrderResponse();
//		com.razorpay.Order order = null;
//		try {
//			RazorpayClient razorpay = new RazorpayClient("rzp_test_IeUOJK4gYGNEMj", "GSSJZK5rA40xHs3yRMw49X3f");
//			JSONObject orderRequest = new JSONObject();
//			  orderRequest.put("amount", request.getAmount()); // amount in the smallest currency unit
//			  orderRequest.put("currency", request.getCurrency());
//			  orderRequest.put("receipt", request.getReceipt());
//			  System.out.println("test");
//			  order = razorpay.Orders.create(orderRequest);
//			  System.out.println(order);
////			  String id = order.get("id");
//			  response.setId(order.get("id"));
////			  response.setOrder(order);
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//		return response;
//	}
	
	@PostMapping("/getOrderByStatus")
	public OrderDetailResponse getOrderByStatus(@RequestBody OrderRequest request) {
		OrderDetailResponse response = new OrderDetailResponse();
		try {
			List<OrderMaster> orderMaster = orderService.getOrderBySellerIdAndStatus(request.getSellerId(), request.getOrderStatus());
			response.setOrderMaster(orderMaster);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/orderStatusUpdate")
	public BaseResponse orderStatusUpdate(@RequestBody OrderRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Order order  = orderService.getOrderById(request.getId());
			order.setOrderStatus(request.getOrderStatus());
			orderService.saveOrderDirectly(order);			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
}
