package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookToCartItemEntity implements Serializable {

	
	private static final long serialVersionUID = -2343715977095660873L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private BookEntity book;
	
	@ManyToOne
	@JoinColumn(name="cart_item_id")
	private CartItemEntity cartItem;

	public Long getId() {
		return id;
	}

	public BookEntity getBook() {
		return book;
	}

	public CartItemEntity getCartItem() {
		return cartItem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public void setCartItem(CartItemEntity cartItem) {
		this.cartItem = cartItem;
	}
	
	
	
}
