package com.example.eshopping.model.address;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsResponse {

	private String ErrorCode;
	private String ErrorMessage;
	private String JobId;
	private List<SmsMessageResponse> MessageData;
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getJobId() {
		return JobId;
	}
	public void setJobId(String jobId) {
		JobId = jobId;
	}
	public List<SmsMessageResponse> getMessageData() {
		return MessageData;
	}
	public void setMessageData(List<SmsMessageResponse> messageData) {
		MessageData = messageData;
	}
	
	
	
}
