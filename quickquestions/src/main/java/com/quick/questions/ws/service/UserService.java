package com.quick.questions.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quick.questions.ws.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{

	UserDto createUser(UserDto userdto);
	UserDto getUser(String email);
}
