package com.quick.questions.ws.ui.controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.service.UserPaymentService;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.dto.UserBillingDTO;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.shared.dto.UserPaymentDTO;
import com.quick.questions.ws.ui.model.request.UserBillingRequestModel;
import com.quick.questions.ws.ui.model.request.UserPaymentRequestModel;
import com.quick.questions.ws.ui.model.response.OperationStatusModel;
import com.quick.questions.ws.ui.model.response.RequestOperationName;
import com.quick.questions.ws.ui.model.response.RequestOperationStatus;
import com.quick.questions.ws.ui.model.response.UserDetailsResponseModel;
import com.quick.questions.ws.ui.model.response.UserPaymentResponseModel;

@RestController
public class PaymentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService paymentService;

	@PostMapping(path="/{id}/payment/add",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserPaymentResponseModel addNewCreditCardPost(@RequestBody UserPaymentRequestModel userPaymentRequestModel, @PathVariable String id) {
		UserPaymentResponseModel userPaymentResponseModel = new UserPaymentResponseModel();
		
		UserDto userDto= userService.getUserByUserId(id);
		
		UserBillingRequestModel userBillingRequestModel = userPaymentRequestModel.getUserBilling();
		
		ModelMapper modelMapper = new ModelMapper();
		UserBillingDTO userBillingDTO=modelMapper.map(userBillingRequestModel, UserBillingDTO.class);
		UserPaymentDTO userPaymentDTO=modelMapper.map(userPaymentRequestModel, UserPaymentDTO.class);
		
		UserDto updatedUserDto=userService.updateUserBilling(userBillingDTO, userPaymentDTO, userDto);
		UserDetailsResponseModel userDetailsResponseModel=modelMapper.map(updatedUserDto, UserDetailsResponseModel.class);
		
		
		
		System.out.println("Payment added Successfully");
		
		return userPaymentResponseModel;
	}
	
	@DeleteMapping(path="/{userId}/payment/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel removePaymentPost(@PathVariable String userId,@PathVariable Long id){
		
		UserDto userDto= userService.getUserByUserId(userId);
		
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		paymentService.removeById(id);
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
	
	@PostMapping(path="/setDefault",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity setDefaultPaymentPost(
			@RequestBody String id,
			Principal principal
			){
		UserDto userDto= userService.getUser(principal.getName());
		
		userService.setUserDefaultPayment(Long.parseLong(id), userDto);
		
		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/payment/getUserPaymentList")
	public List<UserPaymentResponseModel> getUserPaymentList(
			@PathVariable String userId
			){
		List<UserPaymentResponseModel> userPaymentResponseModelList= new ArrayList<UserPaymentResponseModel>();
		UserDto userDto= userService.getUserByUserId(userId);
		
		List<UserPaymentDTO> userPaymentList = userDto.getUserPaymentList();
		Type listType = new TypeToken<List<UserPaymentResponseModel>>() {}.getType();
		ModelMapper modelMapper = new ModelMapper();
		 userPaymentResponseModelList=modelMapper.map(userPaymentList, listType);
		 
		return userPaymentResponseModelList;
	}
}
