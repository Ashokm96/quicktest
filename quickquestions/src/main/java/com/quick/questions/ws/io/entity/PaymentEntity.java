package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentEntity implements Serializable {

	private static final long serialVersionUID = 373528854901724483L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false, updatable = false)
	private Long id;
	
	private String type;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	private String holderName;
	private boolean defaultPayment;
	
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
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
	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
}
