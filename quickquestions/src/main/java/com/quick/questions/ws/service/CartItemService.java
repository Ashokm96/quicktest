package com.quick.questions.ws.service;

import java.util.List;

import com.quick.questions.ws.shared.dto.BookDTO;
import com.quick.questions.ws.shared.dto.CartItemDto;
import com.quick.questions.ws.shared.dto.ShoppingCartDTO;
import com.quick.questions.ws.shared.dto.UserDto;

public interface CartItemService {

	CartItemDto addBookToCartItem(BookDTO bookDto, UserDto userDto, int qty);
	
	List<CartItemDto> findByShoppingCart(ShoppingCartDTO shoppingCartDTO);
	
	CartItemDto updateCartItem(CartItemDto cartItemDto);
	
	void removeCartItem(CartItemDto cartItemDto);
	
	CartItemDto findById(Long id);
}
