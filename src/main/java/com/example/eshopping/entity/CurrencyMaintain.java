package com.example.eshopping.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CurrencyMaintain {

	@Id
	private String id;
	private String country;
	private String currency;
	private String currencyRate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	
	
}
