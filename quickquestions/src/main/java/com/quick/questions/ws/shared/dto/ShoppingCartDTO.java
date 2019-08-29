package com.quick.questions.ws.shared.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartDTO {

	private Long id;
	private BigDecimal GrandTotal;
	
	private List<CartItemDto> cartItemList;
	
	private UserDto user;

	public Long getId() {
		return id;
	}

	public BigDecimal getGrandTotal() {
		return GrandTotal;
	}

	public List<CartItemDto> getCartItemList() {
		return cartItemList;
	}

	public UserDto getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		GrandTotal = grandTotal;
	}

	public void setCartItemList(List<CartItemDto> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
