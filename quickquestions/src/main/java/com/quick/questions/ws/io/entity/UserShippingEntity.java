package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserShippingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1984778950175031319L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userShippingName;
	private String userShippingStreet1;
	private String userShippingStreet2;
	private String userShippingCity;
	private String userShippingState;
	private String userShippingCountry;
	private String userShippingZipcode;
	private Boolean userShippingDefault;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private UserEntity user;


	public Long getId() {
		return id;
	}


	public String getUserShippingName() {
		return userShippingName;
	}


	public String getUserShippingStreet1() {
		return userShippingStreet1;
	}


	public String getUserShippingStreet2() {
		return userShippingStreet2;
	}


	public String getUserShippingCity() {
		return userShippingCity;
	}


	public String getUserShippingState() {
		return userShippingState;
	}


	public String getUserShippingCountry() {
		return userShippingCountry;
	}


	public String getUserShippingZipcode() {
		return userShippingZipcode;
	}


	public Boolean getUserShippingDefault() {
		return userShippingDefault;
	}


	

	public void setId(Long id) {
		this.id = id;
	}


	public void setUserShippingName(String userShippingName) {
		this.userShippingName = userShippingName;
	}


	public void setUserShippingStreet1(String userShippingStreet1) {
		this.userShippingStreet1 = userShippingStreet1;
	}


	public void setUserShippingStreet2(String userShippingStreet2) {
		this.userShippingStreet2 = userShippingStreet2;
	}


	public void setUserShippingCity(String userShippingCity) {
		this.userShippingCity = userShippingCity;
	}


	public void setUserShippingState(String userShippingState) {
		this.userShippingState = userShippingState;
	}


	public void setUserShippingCountry(String userShippingCountry) {
		this.userShippingCountry = userShippingCountry;
	}


	public void setUserShippingZipcode(String userShippingZipcode) {
		this.userShippingZipcode = userShippingZipcode;
	}


	public void setUserShippingDefault(Boolean userShippingDefault) {
		this.userShippingDefault = userShippingDefault;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	
	
	
}
