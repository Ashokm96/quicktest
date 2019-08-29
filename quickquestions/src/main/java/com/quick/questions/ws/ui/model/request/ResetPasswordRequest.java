package com.quick.questions.ws.ui.model.request;

public class ResetPasswordRequest {

	private String token;
	private String password;
	public String getToken() {
		return token;
	}
	public String getPassword() {
		return password;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
