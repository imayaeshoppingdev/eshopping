package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.Bargain;
import com.example.eshopping.entity.BulkOrders;
import com.example.eshopping.entity.CurrencyMaintain;
import com.example.eshopping.entity.NewProduct;

public interface BargainService {

	public Bargain saveBargain(Bargain bargain);
	
	public Bargain getBargainByUserAndCategoryId(String userId, String categoryId);
	
	public void deleteAll();
	
	public CurrencyMaintain saveCurrency(CurrencyMaintain currency);
	
	public List<CurrencyMaintain> getAllCurrency();
	
	public NewProduct saveNewProduct(NewProduct product) ;
	
	public List<NewProduct> getNewProduct();
	
	public BulkOrders saveBulkOrder(BulkOrders bulkOrders);
	
	public List<BulkOrders> getBulkOrders();
	
	public List<BulkOrders> getBulkOrdersByBuyerId(String id);
	
	public BulkOrders getBulkOrdersById(String id) ;
	
	public void deleteBulkOrderRequest(String id);
	
	public void deleteProductRequest(String id);
}
