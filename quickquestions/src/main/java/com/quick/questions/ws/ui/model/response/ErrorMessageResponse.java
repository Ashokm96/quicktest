package com.quick.questions.ws.ui.model.response;

import java.util.Date;

public class ErrorMessageResponse {

	public ErrorMessageResponse(Date timeStamp, boolean success, String message) {
		super();
		this.timeStamp = timeStamp;
		this.success = success;
		this.message = message;
	}
	private Date timeStamp;
	private boolean success;
	private String message;
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorMessageResponse() {
		
		// TODO Auto-generated constructor stub
	}
	
}
