package com.quick.questions.ws.ui.model.request;

public class UserRole {

	private long userRoleId;
	private UserDetailsRequestModel userDetailsRequestModel;
	private Role role;
	
	
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRole(UserDetailsRequestModel userDetailsRequestModel, Role role) {
		super();
	
		this.userDetailsRequestModel = userDetailsRequestModel;
		this.role = role;
	}
	
	
	public long getUserRoleId() {
		return userRoleId;
	}
	public UserDetailsRequestModel getUserDetailsRequestModel() {
		return userDetailsRequestModel;
	}
	public Role getRole() {
		return role;
	}
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public void setUserDetailsRequestModel(UserDetailsRequestModel userDetailsRequestModel) {
		this.userDetailsRequestModel = userDetailsRequestModel;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
