package com.example.eshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.service.WishListService;

@RestController
@RequestMapping("/wishList")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishListController {

	@Autowired
	WishListService wishListService;
	
	
}
