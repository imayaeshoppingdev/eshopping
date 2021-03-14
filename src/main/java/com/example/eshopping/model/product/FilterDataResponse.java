package com.example.eshopping.model.product;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.eshopping.common.BaseResponse;

public class FilterDataResponse extends BaseResponse{

	Map<String, Set<String>> filterData;

	public Map<String, Set<String>> getFilterData() {
		return filterData;
	}

	public void setFilterData(Map<String, Set<String>> filterData) {
		this.filterData = filterData;
	}
	
	
}
