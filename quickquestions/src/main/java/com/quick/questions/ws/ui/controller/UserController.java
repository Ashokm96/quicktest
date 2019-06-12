package com.quick.questions.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.ui.model.request.UserDetailsRequestModel;
import com.quick.questions.ws.ui.model.response.UserDetailsResponseModel;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	@GetMapping
	public String getUsers() {
		return "get user details";
	}
	
	@PostMapping
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDeatialsReqModel) {
		return null;
	}
	
	@PutMapping
	public String updateUser() {
		return "update user called";
	}
	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}
}
