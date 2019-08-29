package com.quick.questions.ws.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.io.entity.UserShippingEntity;
import com.quick.questions.ws.io.repositories.UserShippingRepository;
import com.quick.questions.ws.service.UserShippingService;
import com.quick.questions.ws.shared.dto.UserShippingDTO;
@Service
public class UserShippingServiceImpl implements UserShippingService {
	
	@Autowired
	private UserShippingRepository userShippingRepository;

	@Override
	public UserShippingDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<UserShippingEntity> optionalUserShippingEntity= userShippingRepository.findById(id);
		UserShippingEntity userShippingEntity=optionalUserShippingEntity.get();
		ModelMapper modelMapper =  new ModelMapper();
		UserShippingDTO userShippingDTO=modelMapper.map(userShippingEntity, UserShippingDTO.class);
		return userShippingDTO;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userShippingRepository.deleteById(id);
	}

}
