package com.quick.questions.ws.io.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -7323481259730031136L;
	
	@Id
	@GeneratedValue
	private int roleId;
	
	private String roleName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRoleEntity> userRoles = new HashSet<>();

	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	
	
}
