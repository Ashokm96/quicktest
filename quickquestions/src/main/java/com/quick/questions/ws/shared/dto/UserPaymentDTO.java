package com.quick.questions.ws.shared.dto;

public class UserPaymentDTO {

	private Long id;
	private String type;
	private String cardName;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	private String holderName;
	private boolean defaultPayment;
	
	private UserDto user;
	
	private UserBillingDTO userBilling;
	

	public UserPaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public UserDto getUser() {
		return user;
	}

	public UserBillingDTO getUserBilling() {
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

	public void setUser(UserDto user) {
		this.user = user;
	}

	public void setUserBilling(UserBillingDTO userBilling) {
		this.userBilling = userBilling;
	}
	
}
