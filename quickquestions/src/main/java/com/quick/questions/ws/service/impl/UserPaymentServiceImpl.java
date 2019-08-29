package com.quick.questions.ws.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.io.entity.UserPaymentEntity;
import com.quick.questions.ws.io.repositories.UserPaymentRepository;
import com.quick.questions.ws.service.UserPaymentService;
import com.quick.questions.ws.shared.dto.UserPaymentDTO;
import com.quick.questions.ws.ui.model.response.UserPaymentResponseModel;
@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	
	@Override
	public UserPaymentDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<UserPaymentEntity> optionalUserPaymentEntity=userPaymentRepository.findById(id);
		UserPaymentEntity userPaymentEntity=optionalUserPaymentEntity.get();
		
		ModelMapper modelMapper = new ModelMapper();
		UserPaymentDTO userPaymentDTO=modelMapper.map(userPaymentEntity, UserPaymentDTO.class);
		
		return userPaymentDTO;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userPaymentRepository.deleteById(id);
	}

	@Override
	public List<UserPaymentDTO> findUserPaymentsByUserId(Long id) {
		// TODO Auto-generated method stub
		List<UserPaymentEntity> userPaymentsEntity = userPaymentRepository.findUserPaymentsByUserId(id);
		List<UserPaymentDTO> userPaymentList = new ArrayList<UserPaymentDTO>();
		Type listType = new TypeToken<List<UserPaymentDTO>>() {}.getType();
		ModelMapper modelMapper = new ModelMapper();
		userPaymentList=modelMapper.map(userPaymentsEntity, listType);
		return userPaymentList;
	}

}
