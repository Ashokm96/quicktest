package com.quick.questions.ws.io.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItemEntity implements Serializable {

	private static final long serialVersionUID = -1775655726283435272L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int qty;
	private BigDecimal subtotal;
	
	
	@OneToOne
	private BookEntity book;
	
	@OneToMany(mappedBy ="cartItem")
	@JsonIgnore
	private List<BookToCartItemEntity> bookToCartItemList;
	
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	@JsonIgnore
	private ShoppingCartEntity shoppingCart;


	public Long getId() {
		return id;
	}

	public int getQty() {
		return qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public BookEntity getBook() {
		return book;
	}

	public List<BookToCartItemEntity> getBookToCartItemList() {
		return bookToCartItemList;
	}

	public ShoppingCartEntity getShoppingCart() {
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

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public void setBookToCartItemList(List<BookToCartItemEntity> bookToCartItemList) {
		this.bookToCartItemList = bookToCartItemList;
	}

	public void setShoppingCart(ShoppingCartEntity shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	

}
