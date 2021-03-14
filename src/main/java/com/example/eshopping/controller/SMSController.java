package com.example.eshopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.model.address.SmsResponse;
import com.example.eshopping.model.sms.CancelOrder;
import com.example.eshopping.model.sms.ForwardFlow;
import com.example.eshopping.model.sms.InvoiceRequest;
import com.example.eshopping.model.sms.UpdateWarehouse;
import com.example.eshopping.model.sms.WareHouse;
import com.example.eshopping.util.JSONUtil;

@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SMSController {

	
	@GetMapping("/sendSms")
	public BaseResponse sendSMS() {
		BaseResponse response = new BaseResponse();
		try {
			String uri = 
					"https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=Yt7gu65VeEKdMBL9gjsjjA&senderid=SMSTST&channel=2&DCS=0&flashsms=0&number=919344159742&text=test message&route=1";
			String mainUrl="https://www.smsgatewayhub.com/api/mt/SendSMS?";
			String apikey = "Yt7gu65VeEKdMBL9gjsjjA";
			//Approved sender id(6 characters string only).
			String senderId = "SMSTST";
			//Message channel Promotional=1 or Transactional=2.
			String channel = "2";
			//Default is 0 for normal message, Set 8 for unicode sms.
			String DCS = "0";
			 //Default is 0 for normal sms, Set 1 for immediate display.
			 String flashsms = "0";
			//Your message to send.
			String message = "Check message";
			//Recipient mobile nCheckumber (pass with comma seprated if need to send on more then one number)
			String number = "919500544899";
			//define route
			String route="1";
			//Prepare parameter string 
			String APIKey = "Yt7gu65VeEKdMBL9gjsjjA";
			//Multiple mobiles numbers separated by comma
			//Sender ID should be 6 characters long.
			String senderid= "SMSTST";
			//Your message to send, Add URL encoding here.
			String text = "Test message";
			//Prepare Url
			URLConnection myURLConnection=null;
			URL myURL=null;
			BufferedReader reader=null;
			//encoding message
			String encoded_message=URLEncoder.encode(message);
			//Send SMS API
			//Prepare parameter string
			StringBuilder sbPostData= new StringBuilder("https://www.smsgatewayhub.com/api/mt/SendSMS?");
			sbPostData.append("APIKey="+ APIKey);
			sbPostData.append("&number="+number);
			sbPostData.append("&text="+encoded_message);
			sbPostData.append("&route="+route);
			sbPostData.append("&channel="+channel);
			sbPostData.append("&flashsms="+flashsms);
			sbPostData.append("&DCS="+DCS);
			//final string
			mainUrl = sbPostData.toString();
			try
			{
			 //prepare connection
			 myURL = new URL(mainUrl);
			 myURLConnection = myURL.openConnection();
			 myURLConnection.connect();
			 reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			 //reading response
			 String response1;
			 while ((response1 = reader.readLine()) != null)
			 //print response
			 System.out.println(response1);

			 //finally close connection
			 reader.close();
			    
			} 
			catch (IOException e) 
			{ 
			  e.printStackTrace();
			} 
//				 RestTemplate restTemplate = new RestTemplate();
//				    SmsResponse result = restTemplate.getForObject(uri, SmsResponse.class);
////				 restTemplate.execute(uri);
			   System.out.println();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/validatePinCode/{pincode}")
	public ResponseEntity<?> validatePinCode(@PathVariable String pincode) {
		ResponseEntity<?> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
			  String url="https://staging-express.delhivery.com/c/api/pin-codes/json/?filter_codes="+pincode;
			  response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getWayBill")
	public BaseResponse getWayBill() {
		BaseResponse response = new BaseResponse();
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
			  String url="https://staging-express.delhivery.com/waybill/api/fetch/json/";
			  ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			  System.out.println("result "+result.getBody().toString());
			  response.setMessage(result.getBody().toString());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	
	@PostMapping("/forwardFlow")
	public ResponseEntity<String> forwardFlow(@RequestBody ForwardFlow request) {
		ResponseEntity<String> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  String requestString = JSONUtil.toJson(request);
			  requestString = "format=json&data=".concat(requestString);
			  System.out.println(requestString);
			  HttpEntity<String> entity = new HttpEntity<String>(requestString,httpHeaders);
			  String url="https://staging-express.delhivery.com/api/cmu/create.json";
			  response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/backwardFlow")
	public ResponseEntity<String> backwardFlow(@RequestBody ForwardFlow request) {
		ResponseEntity<String> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  String requestString = JSONUtil.toJson(request);
			  requestString = "format=json&data=".concat(requestString);
			  System.out.println(requestString);
			  HttpEntity<String> entity = new HttpEntity<String>(requestString,httpHeaders);
			  String url="https://staging-express.delhivery.com/api/cmu/create.json";
			  response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/invoiceAPI")
	public ResponseEntity<String> invoiceAPI(@RequestBody InvoiceRequest request) {
		ResponseEntity<String> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token 692cbf1b28190da88d064cf3007e5c3ef99b890c");
			  HttpEntity<ForwardFlow> entity = new HttpEntity<ForwardFlow>(httpHeaders);
			  String url="https://track.delhivery.com/api/kinko/v1/invoice/charges/.json?md="+request.getMd()+"&cgm="+request.getCgm()
			  +"&o_pin="+request.getO_pin()+"&d_pin="+request.getD_pin()+"&ss="+request.getSs();
			  response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/wareHouseCreation")
	public BaseResponse wareHouseCreation(@RequestBody WareHouse request) {
		BaseResponse response = new BaseResponse();
		ResponseEntity<String> result = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  httpHeaders.set("Accept", "application/json");
			  HttpEntity<WareHouse> entity = new HttpEntity<WareHouse>(request,httpHeaders);
			  System.out.println(JSONUtil.toJson(entity));
			  String url="https://staging-express.delhivery.com/api/backend/clientwarehouse/create/";
			  result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			  System.out.println("result "+result.getBody().toString());
			  response.setResult(result);
		}
		catch(Exception e) {
			System.out.println(e);
			response.setMessage("Ware House not created");
		}
		return response;
	}
	
	
	@GetMapping("/track-order/{id}")
	public ResponseEntity<String> getTrackOrder(@PathVariable String id) {
		ResponseEntity<String> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
			  String url="https://staging-express.delhivery.com/api/v1/packages/json/?waybill="+id+"&token=a3e76b4efa800da090c3aa95d8dec8a6717ccf9c";
			  response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/cancel-order")
	public ResponseEntity<String> cancelOrder(@RequestBody CancelOrder request) {
		ResponseEntity<String> result = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<CancelOrder> entity = new HttpEntity<CancelOrder>(request,httpHeaders);
			  String url="https://staging-express.delhivery.com/api/p/edit";
			  result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			  System.out.println("result "+result.getBody().toString());
//			  response.setMessage(result.getBody().toString());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	@PostMapping("/updateWareHouse")
	public BaseResponse updateWareHouse(@RequestBody UpdateWarehouse request) {
		BaseResponse response = new BaseResponse();
		ResponseEntity<String> result = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<UpdateWarehouse> entity = new HttpEntity<UpdateWarehouse>(request,httpHeaders);
			  String url="https://staging-express.delhivery.com/api/backend/clientwarehouse/edit/";
			  result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			  System.out.println("result "+result.getBody().toString());
			  response.setResult(result);
//			  response.setMessage(result.getBody().toString());
		}
		catch(Exception e) {
			System.out.println(e);
			response.setMessage("WareHouse does not exists");
		}
		return response;
	}
	
	@GetMapping("/packingSlip/{id}")
	public ResponseEntity<String> getPackingSlip(@PathVariable String id) {
		ResponseEntity<String> response = null;
		try {
			  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders httpHeaders = new HttpHeaders();
			  httpHeaders.set("Content-Type", "application/json");
			  httpHeaders.set("Authorization", "Token a3e76b4efa800da090c3aa95d8dec8a6717ccf9c");
			  HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
			  String url="https://staging-express.delhivery.com/api/p/packing_slip?wbns="+id;
			  response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			  System.out.println("result "+response.getBody().toString());
			  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}
	
}
