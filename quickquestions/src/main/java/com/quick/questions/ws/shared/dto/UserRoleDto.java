package com.quick.questions.ws.shared.dto;

public class UserRoleDto {

	private long userRoleId;
	private UserDto userDetails;
	private RoleDto role;
	
	
	public UserRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRoleDto(RoleDto role) {
		super();
		this.role = role;
	}
	public long getUserRoleId() {
		return userRoleId;
	}
	public UserDto getUserDetails() {
		return userDetails;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	
}
