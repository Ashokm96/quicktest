package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserPaymentEntity implements Serializable {
	
	private static final long serialVersionUID = 78091345L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String type;
	private String cardName;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	private String holderName;
	private boolean defaultPayment;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "userPayment", fetch = FetchType.LAZY)
	private UserBillingEntity userBilling;

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getCardName() {
		return cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public int getCvc() {
		return cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public UserEntity getUser() {
		return user;
	}

	public UserBillingEntity getUserBilling() {
		return userBilling;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setUserBilling(UserBillingEntity userBilling) {
		this.userBilling = userBilling;
	}
	
	
}
