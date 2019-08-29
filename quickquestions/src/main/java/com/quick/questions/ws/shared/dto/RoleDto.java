package com.quick.questions.ws.shared.dto;

public class RoleDto {

	private long roleId;
	private String roleName;
	public long getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
