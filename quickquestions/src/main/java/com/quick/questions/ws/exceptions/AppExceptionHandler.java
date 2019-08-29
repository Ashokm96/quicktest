package com.quick.questions.ws.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.quick.questions.ws.ui.model.response.ErrorMessageResponse;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value= {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceExceptoin(UserServiceException userServiceException, WebRequest webRequest){
		
		ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(new Date(), false, userServiceException.getMessage() );
		
		return new ResponseEntity<>(errorMessageResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAllExceptoin(Exception ex, WebRequest webRequest){
		
		ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(new Date(), false, ex.getMessage() );
		
		return new ResponseEntity<>(errorMessageResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult	= ex.getBindingResult();
		List<ErrorMessageResponse> errorMessages = new ArrayList<ErrorMessageResponse>();
		
		List<ObjectError> errors= bindingResult.getAllErrors();
		for (ObjectError objectError : errors) {
			ErrorMessageResponse errorMessageResp = new ErrorMessageResponse();
			errorMessageResp = new ErrorMessageResponse(new Date(), false, objectError.getDefaultMessage());
			errorMessages.add(errorMessageResp);
			
		}
		
		
		return new ResponseEntity<>(errorMessages, headers,  HttpStatus.BAD_REQUEST);
	}
}
