package com.quick.questions.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.UserRepository;
import com.quick.questions.ws.io.entity.UserEntity;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.Utills;
import com.quick.questions.ws.shared.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utills utils;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		if(userRepository.findByEmail(userdto.getEmail()) != null) throw new RuntimeException("user already exists");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userdto, userEntity);
		
		
		userEntity.setEncryptedPassword("test");
		String userId =utils.generatedString(30);
		userEntity.setUserId(userId);
		
		UserEntity storedUserEntity=userRepository.save(userEntity);
		
		UserDto returnedUserDto = new UserDto();
		BeanUtils.copyProperties(storedUserEntity, returnedUserDto);
		
		return returnedUserDto;
	}

}
