package com.quick.questions.ws.io.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 2937920676412628289L;
	
	@Id
	@GeneratedValue
	@Column(nullable=false, updatable = false)
	private Long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus;
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRoleEntity> userRoles = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	private List<UserPaymentEntity> userPaymentList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	private List<UserShippingEntity> userShippingList;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "user")
	private ShoppingCartEntity shoppingCart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public List<UserPaymentEntity> getUserPaymentList() {
		return userPaymentList;
	}

	public void setUserPaymentList(List<UserPaymentEntity> userPaymentList) {
		this.userPaymentList = userPaymentList;
	}

	public List<UserShippingEntity> getUserShippingList() {
		return userShippingList;
	}

	public void setUserShippingList(List<UserShippingEntity> userShippingList) {
		this.userShippingList = userShippingList;
	}

	public ShoppingCartEntity getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCartEntity shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
