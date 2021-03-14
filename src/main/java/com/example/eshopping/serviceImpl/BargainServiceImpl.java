package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.eshopping.dao.impl.BargainDAOImpl;
import com.example.eshopping.entity.Bargain;
import com.example.eshopping.entity.BulkOrders;
import com.example.eshopping.entity.CurrencyMaintain;
import com.example.eshopping.entity.NewProduct;
import com.example.eshopping.repo.BargainRepository;
import com.example.eshopping.repo.BulkOrderReposiotry;
import com.example.eshopping.repo.CurrencyMaintainReposiotry;
import com.example.eshopping.repo.NewProductRepository;
import com.example.eshopping.service.BargainService;

@Service
public class BargainServiceImpl implements BargainService{

	@Autowired
	BargainRepository bargainRepository;
	
	@Autowired
	CurrencyMaintainReposiotry currencyMaintainRepository;
	
	@Autowired
	NewProductRepository newProductRepository;
	
	@Autowired
	BulkOrderReposiotry bulkOrderRepository;
	
	@Autowired
	BargainDAOImpl bargainDAO;
	
	public Bargain saveBargain(Bargain bargain) {
		return this.bargainRepository.save(bargain);
	}
	
	public Bargain getBargainByUserAndCategoryId(String userId, String categoryId) {
		return this.bargainRepository.findByUserIdAndCategoryId(userId, categoryId);
	}
	
	public void deleteAll() {
		this.bargainRepository.deleteAll();
	}
	
	public CurrencyMaintain saveCurrency(CurrencyMaintain currency) {
		return this.currencyMaintainRepository.save(currency);
	}
	
	public List<CurrencyMaintain> getAllCurrency(){
		return this.currencyMaintainRepository.findAll();
	}
	
	public NewProduct saveNewProduct(NewProduct product) {
		return this.newProductRepository.save(product);
	}
	
	public List<NewProduct> getNewProduct(){
		return this.newProductRepository.findAll();
	}
	
	public BulkOrders saveBulkOrder(BulkOrders bulkOrders) {
		return this.bulkOrderRepository.save(bulkOrders);
	}
	
	public List<BulkOrders> getBulkOrders(){
		return this.bulkOrderRepository.findByStatus("Pending");
	}
	
	public List<BulkOrders> getBulkOrdersByBuyerId(String id){
		return this.bulkOrderRepository.findByBuyerIdAndStatus(id,"Approved");
	}
	
	public BulkOrders getBulkOrdersById(String id) {
		return this.bulkOrderRepository.findById(id);
	}
	
	public void deleteBulkOrderRequest(String id) {
		 this.bulkOrderRepository.deleteById(id);
	}
	
	public void deleteProductRequest(String id) {
		this.newProductRepository.deleteById(id);
	}
}
