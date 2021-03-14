package com.example.eshopping.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.example.eshopping.entity.Bargain;
import com.example.eshopping.entity.BulkOrders;
import com.example.eshopping.entity.CurrencyMaintain;
import com.example.eshopping.entity.NewProduct;
import com.example.eshopping.model.images.CurrencyMaintainResponse;
import com.example.eshopping.model.order.BulkOrderRequest;
import com.example.eshopping.model.order.BulkOrderResponse;
import com.example.eshopping.model.product.NewProductResponse;
import com.example.eshopping.service.BargainService;
import com.example.eshopping.util.DateFormat;

@RestController
@RequestMapping("/bargain")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BargainController {

	@Autowired
	BargainService bargainService;
	
	@PostMapping("/saveAndUpdateBargain")
	public BaseResponse bargainUpdate(@RequestBody Bargain request) {
		BaseResponse response = new BaseResponse();
		try {
			 Bargain bargain = bargainService.getBargainByUserAndCategoryId(request.getUserId(), request.getCategoryId());
			 if(bargain == null) {
				 request.setTodayDate(DateFormat.getTodayString());
				 request.setNoOfBargains(1);
				 bargainService.saveBargain(request);
				 response.setMessage("Remaining balance for Bargain is 2");
				 return response;
			 }
			 if(bargain.getNoOfBargains() >= 3) {
				 response.setMessage("Daily bargain exceed.");
				 return response;
			 }
			 else {
				 int total = bargain.getNoOfBargains() + 1;
				 bargain.setNoOfBargains(total);
				 bargainService.saveBargain(bargain);
				 response.setMessage("Remaining balance for Bargain is "+(3-total));
				 return response;
			 }
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Bargain add failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
//	public boolean checkforTodayDate(String recordDate) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		 String todayDate = sdf.format(new Date());
//		System.out.println(sdf.parse(todayDate).before(sdf.parse(recordDate)));
//		boolean value = sdf.parse(todayDate).before(sdf.parse(recordDate));
//		return value;
//	}
	
	@PostMapping("/updateCurrency")
	public BaseResponse updateCurrency(@RequestBody CurrencyMaintain request) {
		BaseResponse response = new BaseResponse();
		try {
			bargainService.saveCurrency(request);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Currency add failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getCurrency")
	public CurrencyMaintainResponse getCurrency() {
		CurrencyMaintainResponse response = new CurrencyMaintainResponse();
		try {
			List<CurrencyMaintain> currency = bargainService.getAllCurrency();
			response.setCurrency(currency);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Currency get failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/productRequest")
	public BaseResponse productRequest(@RequestBody NewProduct request) {
		BaseResponse response = new BaseResponse();
		try {
			request.setStatus("Pending");
			bargainService.saveNewProduct(request);
			response.setMessage("Product get Submitted");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("New Request get failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	
	@GetMapping("/getProductRequest")
	public NewProductResponse getProductRequest() {
		NewProductResponse response = new NewProductResponse();
		try {
			List<NewProduct> newProduct = bargainService.getNewProduct();
			response.setNewProduct(newProduct);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("New Request failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/deleteProductRequest/{id}")
	public BaseResponse deleteProductRequest(@PathVariable String id) {
		BaseResponse response = new BaseResponse();
		try {
			bargainService.deleteProductRequest(id);
			response.setMessage("Updated");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("New Request failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/saveBulkOrders")
	public BaseResponse saveBulkOrders(@RequestBody BulkOrders request) {
		BaseResponse response = new BaseResponse();
		try {
			request.setStatus("Pending");
			BulkOrders orders = bargainService.saveBulkOrder(request);
			response.setMessage("Bulk Order requested");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Bulk Orders failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getBulkOrders")
	public BulkOrderResponse saveBulkOrders(){
		BulkOrderResponse response = new BulkOrderResponse();
		try {
			List<BulkOrders> orders = bargainService.getBulkOrders();
			response.setBulkOrders(orders);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Bulk Orders failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/updateBulkOrders")
	public BaseResponse updateBulkOrders(@RequestBody BulkOrderRequest request){
		BaseResponse response = new BaseResponse();
		try {
			BulkOrders orders = bargainService.getBulkOrdersById(request.getId());
			orders.setStatus(request.getStatus());
			bargainService.saveBulkOrder(orders);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Bulk Orders failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getBulkOrdersByBuyerId/{id}")
	public BulkOrderResponse getBulkOrdersByBuyerId(@PathVariable String id){
		BulkOrderResponse response = new BulkOrderResponse();
		try {
			List<BulkOrders> orders = bargainService.getBulkOrdersByBuyerId(id);
			response.setBulkOrders(orders);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Bulk Orders failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/processBulkRequest/{id}")
	public BaseResponse deleteBulkOrder(@PathVariable String id) {
		BaseResponse response = new BaseResponse();
		try {
			bargainService.deleteBulkOrderRequest(id);
			response.setMessage("Processed Successfully.");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Delete Bulk Orders failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
}
