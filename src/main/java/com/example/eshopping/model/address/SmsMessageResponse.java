package com.example.eshopping.model.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMessageResponse {

	private String Number;
	private String MessageId;
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	
	
	
	
}
