package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.CurrencyMaintain;

@Repository
public interface CurrencyMaintainReposiotry extends CrudRepository<CurrencyMaintain, Long>{

	List<CurrencyMaintain> findAll();
	CurrencyMaintain findByCurrency(String currency);
	CurrencyMaintain findById(String id);
}
