package com.quick.questions.ws.ui.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.service.BookService;
import com.quick.questions.ws.service.CartItemService;
import com.quick.questions.ws.service.ShoppingCartService;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.ui.model.response.CartItemResponseModel;
import com.quick.questions.ws.ui.model.response.ShoppingCartResponseModel;

@RestController
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping(path="/{userId}/cart",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity addItem(@PathVariable String userId,@RequestBody HashMap<String, String> mapper) {
		
		return new ResponseEntity("Book Added Successfully!", HttpStatus.OK);
	}
	
	
	@GetMapping("/{userId}/cart/getCartItemList")
	public List<CartItemResponseModel> getCartItemList(@PathVariable String userId) {
		List<CartItemResponseModel> cartItemList = null;
		
		return cartItemList;
	}
	
	@GetMapping("/{userId}/cart/getShoppingCart")
	public ShoppingCartResponseModel getShoppingCart(@PathVariable String userId) {
		ShoppingCartResponseModel shoppingCart = new ShoppingCartResponseModel();
		
		return shoppingCart;
	}
	
	@GetMapping("/{userId}/cart/removeItem/{id}")
	public ResponseEntity removeItem(@PathVariable String userId,@PathVariable String id) {
		
		
		return new ResponseEntity("Cart Item Removed Successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/{userId}/cart/updateCartItem")
	public ResponseEntity updateCartItem(
			@RequestBody HashMap<String, String> mapper
			){
		String cartItemId = mapper.get("cartItemId");
		String qty = mapper.get("qty");
		
		
		
		return new ResponseEntity("Cart Item Updated Successfully!", HttpStatus.OK);
	}
}
