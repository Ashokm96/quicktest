package com.quick.questions.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.io.entity.UserEntity;
import com.quick.questions.ws.io.repositories.UserRepository;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.Utills;
import com.quick.questions.ws.shared.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utills utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		if(userRepository.findByEmail(userdto.getEmail()) != null) throw new RuntimeException("user already exists");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userdto, userEntity);
		
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
		String userId =utils.generatedString(30);
		userEntity.setUserId(userId);
		
		UserEntity storedUserEntity=userRepository.save(userEntity);
		
		UserDto returnedUserDto = new UserDto();
		BeanUtils.copyProperties(storedUserEntity, returnedUserDto);
		
		return returnedUserDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDto returnedUserDto = new UserDto();
		BeanUtils.copyProperties(userEntity, returnedUserDto);
		return returnedUserDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserDto returnedUserDto = new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId	);
		BeanUtils.copyProperties(userEntity, returnedUserDto);
		return returnedUserDto;
	}
}
