package com.example.eshopping.model.images;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.CurrencyMaintain;

public class CurrencyMaintainResponse extends BaseResponse{

	List<CurrencyMaintain> currency;

	public List<CurrencyMaintain> getCurrency() {
		return currency;
	}

	public void setCurrency(List<CurrencyMaintain> currency) {
		this.currency = currency;
	}
	
	
}
