package com.quick.questions.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quick.questions.ws.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{

	UserDto createUser(UserDto userdto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page, int limit);
}
