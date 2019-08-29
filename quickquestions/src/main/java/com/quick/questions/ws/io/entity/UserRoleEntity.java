package com.quick.questions.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_role")
public class UserRoleEntity implements Serializable {

	private static final long serialVersionUID = -8298541741334441349L;
	
	@Id
	@GeneratedValue
	private long userRoleId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private RoleEntity role;

	public UserRoleEntity() {
		
	}
	
	
	public long getUserRoleId() {
		return userRoleId;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	
	

}
