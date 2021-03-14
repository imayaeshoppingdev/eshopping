package com.example.eshopping.common;

import org.springframework.http.ResponseEntity;

public class BaseResponse {

	private String status = "Success";
	private String message;
	private String errorCode;
	private int statusCode = 200;
	ResponseEntity<String> result;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public ResponseEntity<String> getResult() {
		return result;
	}
	public void setResult(ResponseEntity<String> result) {
		this.result = result;
	}
	
	
}
