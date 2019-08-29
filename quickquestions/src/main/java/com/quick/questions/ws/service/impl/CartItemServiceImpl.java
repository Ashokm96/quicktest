package com.quick.questions.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quick.questions.ws.service.CartItemService;
import com.quick.questions.ws.shared.dto.BookDTO;
import com.quick.questions.ws.shared.dto.CartItemDto;
import com.quick.questions.ws.shared.dto.ShoppingCartDTO;
import com.quick.questions.ws.shared.dto.UserDto;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Override
	public CartItemDto addBookToCartItem(BookDTO bookDto, UserDto userDto, int qty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItemDto> findByShoppingCart(ShoppingCartDTO shoppingCartDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItemDto updateCartItem(CartItemDto cartItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCartItem(CartItemDto cartItemDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CartItemDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
