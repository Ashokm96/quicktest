package com.quick.questions.ws.ui.model.response;

public class UserBillingResponseModel {

	private Long id;
	private String userBillingName;
	private String userBillingStreet1;
	private String userBillingStreet2;
	private String userBillingCity;
	private String userBillingState;
	private String userBillingCountry;
	private String userBillingZipcode;
	
	private UserPaymentResponseModel userPaymentResponseModel;

	public Long getId() {
		return id;
	}

	public String getUserBillingName() {
		return userBillingName;
	}

	public String getUserBillingStreet1() {
		return userBillingStreet1;
	}

	public String getUserBillingStreet2() {
		return userBillingStreet2;
	}

	public String getUserBillingCity() {
		return userBillingCity;
	}

	public String getUserBillingState() {
		return userBillingState;
	}

	public String getUserBillingCountry() {
		return userBillingCountry;
	}

	public String getUserBillingZipcode() {
		return userBillingZipcode;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserBillingName(String userBillingName) {
		this.userBillingName = userBillingName;
	}

	public void setUserBillingStreet1(String userBillingStreet1) {
		this.userBillingStreet1 = userBillingStreet1;
	}

	public void setUserBillingStreet2(String userBillingStreet2) {
		this.userBillingStreet2 = userBillingStreet2;
	}

	public void setUserBillingCity(String userBillingCity) {
		this.userBillingCity = userBillingCity;
	}

	public void setUserBillingState(String userBillingState) {
		this.userBillingState = userBillingState;
	}

	public void setUserBillingCountry(String userBillingCountry) {
		this.userBillingCountry = userBillingCountry;
	}

	public void setUserBillingZipcode(String userBillingZipcode) {
		this.userBillingZipcode = userBillingZipcode;
	}

	public UserPaymentResponseModel getUserPaymentResponseModel() {
		return userPaymentResponseModel;
	}

	public void setUserPaymentResponseModel(UserPaymentResponseModel userPaymentResponseModel) {
		this.userPaymentResponseModel = userPaymentResponseModel;
	}

	
	
	
}
