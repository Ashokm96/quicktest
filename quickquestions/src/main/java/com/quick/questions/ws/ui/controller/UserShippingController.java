package com.quick.questions.ws.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.service.UserShippingService;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.shared.dto.UserShippingDTO;
import com.quick.questions.ws.ui.model.request.UserShippingRequestModel;
import com.quick.questions.ws.ui.model.response.OperationStatusModel;
import com.quick.questions.ws.ui.model.response.RequestOperationName;
import com.quick.questions.ws.ui.model.response.RequestOperationStatus;
import com.quick.questions.ws.ui.model.response.UserShippingResponseModel;

@RestController
public class UserShippingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	
	@PostMapping(path = "/{userId}/userShipping/add",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserShippingResponseModel addUserShipping(@PathVariable String userId, @RequestBody UserShippingRequestModel userShippingRequestModel) {
		
		UserShippingResponseModel userShippingResponseModel = new UserShippingResponseModel();
		UserDto returnUserDto=new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		UserShippingDTO returnedUserShippingDTO = new UserShippingDTO();
		if(userId != null) {
			UserDto userDto = userService.getUserByUserId(userId);
			
			UserShippingDTO userShippingDTO=modelMapper.map(userShippingRequestModel, UserShippingDTO.class);
			returnedUserShippingDTO=userService.updateUserShipping(userShippingDTO, userDto);
		}
		userShippingResponseModel = modelMapper.map(returnedUserShippingDTO, UserShippingResponseModel.class);
		return userShippingResponseModel;
	}
	
	@GetMapping(path = "/{userId}/userShipping/getUserShippingList",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserShippingResponseModel> getUserShippingList(
			@PathVariable String userId
			){
		List<UserShippingResponseModel> userShippingResponseModelList = new ArrayList<UserShippingResponseModel>();
		UserDto userDto = userService.getUserByUserId(userId);
		List<UserShippingDTO> userShippingList = userDto.getUserShippingList();
		Type listType = new TypeToken<List<UserShippingResponseModel>>() {}.getType();
		ModelMapper modelMapper = new ModelMapper();
		 userShippingResponseModelList=modelMapper.map(userShippingList, listType);
		return userShippingResponseModelList;
	}
	@GetMapping(path="/{userId}/userShipping/remove/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel removeUserShippingPost(@PathVariable String userId,
			@PathVariable Long id
			
			) {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		
		userShippingService.removeById(id);
		
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
		
		
	}
	
	@GetMapping(path="/{userId}/userShipping/setDefaultShipping/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel setDefaultUserShippingPost(
			@PathVariable String userId,
			@PathVariable Long id
			){
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.SET_DEFAULT_SHIPPING.name());
		UserDto userDto = userService.getUserByUserId(userId);
		userService.setUserDefaultShipping(id, userDto);
		
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
}
