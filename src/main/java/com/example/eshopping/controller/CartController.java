package com.example.eshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Cart;
import com.example.eshopping.entity.Product;
import com.example.eshopping.model.cart.CartRequest;
import com.example.eshopping.model.cart.CartResponse;
import com.example.eshopping.service.CartService;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.util.JSONUtil;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addToCart")
	public CartResponse addToCart(@Valid @RequestBody CartRequest request) {
		CartResponse response = new CartResponse();
		try {
			Product product = productService.getProductById(request.getProductId());
			Cart cart = cartService.getCartByProductId(request.getProductId(),request.getUserId());
			if(cart != null && !request.isUpdateCart()) {
				response.setMessage("Product Already added");
				response.setStatus(CommonConstant.SUCCESS);
				return response;
			}
			else if(cart != null || request.isUpdateCart()) {
				cart.setQuantity(request.getQuantity());
			}
			else {
				cart = new Cart();
				cart.setProductId(product.getId());
				cart.setTitle(product.getTitle());
				cart.setProductName(product.getProductName());
				cart.setProdImage(product.getProdImage());
				cart.setProdAlt(product.getProdAlt());
				cart.setPrice(product.getPrice());
				cart.setOfferPrice(product.getOfferPrice());
				cart.setRating(product.getRating());
				cart.setCategory(product.getCategoryId());
				cart.setSubCategory(product.getSubCategoryId());
				cart.setBrand(product.getBrand());
				cart.setCod(product.isCod());
				cart.setOs(product.getOs());
				cart.setUserId(product.getUserId());
				cart.setCartUserId(request.getUserId());
				cart.setQuantity(request.getQuantity());
				cart.setGst(product.getGst());
				cart.setDutytax(product.getDutytax());
				cart.setSupplyerprice(product.getSupplyerprice());
				cart.setSelectedSize(request.getSelectedSize());
				cart.setSize(request.getSize());
				cart.setHandlingFee(request.getHandlingFee());
				cart.setDeliveryPartner(request.getDeliveryPartner());
				cart.setDisplayLocation(product.getDisplayLocation());
			}			
			
			cartService.saveCart(cart);
			long count = cartService.countCartItemsByUserId(request.getUserId());
			response.setCount(count);
			response.setCart(cart);
			response.setStatus(CommonConstant.SUCCESS);
			response.setMessage("Product added to cart");
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Product added to cart Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getCartByUserId")
	public CartResponse getCartByUserId(@RequestBody CartRequest request) {
		CartResponse response = new CartResponse();
		try {
			List<Cart> carts = cartService.getCartListByUser(request.getUserId());
			response.setCarts(carts);
			response.setStatus(CommonConstant.SUCCESS);
			if(carts.isEmpty()) {
				response.setMessage("Cart is Empty");
			}
		}
		catch(Exception e) {
			 response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Get cart Failed");
			 response.setStatusCode(01);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteCartItems")
	public BaseResponse deleteCartItems(@RequestBody CartRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Cart cart = cartService.finndCartById(request.getCartId());
			System.out.println("cart "+JSONUtil.toJson(cart));
			if(cart.getCartUserId().equals(request.getUserId())) {
				System.out.println("if");
				int delete = cartService.deleteCart(request.getCartId());
				if(delete == 1) {
					response.setStatus(CommonConstant.SUCCESS);
					response.setMessage("Product Deleted from Cart");
				}else {
					response.setStatusCode(01);
					response.setStatus(CommonConstant.ERROR);
				}
			}
			else {
				response.setStatusCode(01);
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("Product not found");
			}
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			 response.setMessage("Delete cart Failed");
			 response.setStatusCode(01);
			 System.out.println(e);
		}
		return response;
	}
	
}
