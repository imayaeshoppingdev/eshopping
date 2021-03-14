package com.example.eshopping.model.order;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.BulkOrders;

public class BulkOrderResponse extends BaseResponse{

	private List<BulkOrders> bulkOrders;

	public List<BulkOrders> getBulkOrders() {
		return bulkOrders;
	}

	public void setBulkOrders(List<BulkOrders> bulkOrders) {
		this.bulkOrders = bulkOrders;
	}
	
	
}
