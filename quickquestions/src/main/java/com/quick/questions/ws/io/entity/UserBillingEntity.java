package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserBillingEntity implements Serializable{
	private static final long serialVersionUID = 79872894528934L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userBillingName;
	private String userBillingStreet1;
	private String userBillingStreet2;
	private String userBillingCity;
	private String userBillingState;
	private String userBillingCountry;
	private String userBillingZipcode;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserPaymentEntity userPayment;
	
	public UserPaymentEntity getUserPayment() {
		return userPayment;
	}
	public void setUserPayment(UserPaymentEntity userPayment) {
		this.userPayment = userPayment;
	}
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
	
	
}
