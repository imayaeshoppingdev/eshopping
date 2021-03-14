package com.example.eshopping.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.eshopping.service.BargainService;

@Component
public class SchedulerUtil {
	
	@Autowired
	BargainService bargainService;

	@Scheduled(cron ="50 23 * * ?")
	public void bargainShop() {
		
		bargainService.deleteAll();
	}
}
