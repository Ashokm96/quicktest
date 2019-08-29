package com.quick.questions.ws.shared.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartItemDto {

	private Long id;
	private int qty;
	private BigDecimal subtotal;
	
	private BookDTO book;
	
	
	private List<BookToCartItemDTO> bookToCartItemList;
	
	private ShoppingCartDTO shoppingCart;

	public Long getId() {
		return id;
	}

	public int getQty() {
		return qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public BookDTO getBook() {
		return book;
	}

	public List<BookToCartItemDTO> getBookToCartItemList() {
		return bookToCartItemList;
	}

	public ShoppingCartDTO getShoppingCart() {
		return shoppingCart;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public void setBookToCartItemList(List<BookToCartItemDTO> bookToCartItemList) {
		this.bookToCartItemList = bookToCartItemList;
	}

	public void setShoppingCart(ShoppingCartDTO shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
