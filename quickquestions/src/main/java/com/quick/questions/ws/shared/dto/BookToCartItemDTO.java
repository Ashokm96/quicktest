package com.quick.questions.ws.shared.dto;

public class BookToCartItemDTO {

	private Long id;
	private BookDTO book;
	
	private CartItemDto cartItem;

	public Long getId() {
		return id;
	}

	public BookDTO getBook() {
		return book;
	}

	public CartItemDto getCartItem() {
		return cartItem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public void setCartItem(CartItemDto cartItem) {
		this.cartItem = cartItem;
	}
	
}
