package com.quick.questions.ws.io.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ShoppingCartEntity implements Serializable {

	private static final long serialVersionUID = 7429326163449990271L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private BigDecimal GrandTotal;
	
	@OneToMany(mappedBy = "shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItemEntity> cartItemList;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserEntity user;

	public Long getId() {
		return id;
	}

	public BigDecimal getGrandTotal() {
		return GrandTotal;
	}

	public List<CartItemEntity> getCartItemList() {
		return cartItemList;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		GrandTotal = grandTotal;
	}

	public void setCartItemList(List<CartItemEntity> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}
