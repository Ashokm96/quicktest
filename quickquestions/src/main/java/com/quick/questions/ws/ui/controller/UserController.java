package com.quick.questions.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.exceptions.UserServiceException;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.ui.model.request.UserDetailsRequestModel;
import com.quick.questions.ws.ui.model.response.ErrorMessage;
import com.quick.questions.ws.ui.model.response.UserDetailsResponseModel;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(path ="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel getUsers(@PathVariable String id) {
		
		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
		
		
		
		UserDto	getUser =userService.getUserByUserId(id);
		BeanUtils.copyProperties(getUser, userDetailsResponseModel);
		return userDetailsResponseModel;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDeatialsReqModel) throws Exception {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		
		if(userDeatialsReqModel.getFirstName().isEmpty())
			throw new NullPointerException("The Object is null");
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDeatialsReqModel, userDto);
		
		UserDto	createdUser=userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userResponse);
		return userResponse;
	}
	
	@PutMapping(
			path ="/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel updateUser(@RequestBody UserDetailsRequestModel userDeatialsReqModel, @PathVariable String id) {
		
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		
		if(userDeatialsReqModel.getFirstName().isEmpty())
			throw new NullPointerException("The Object is null");
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDeatialsReqModel, userDto);
		
		UserDto	updatedUser=userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updatedUser, userResponse);
		return userResponse;
	}
	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}
}
