package com.quick.questions.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.io.entity.AddressEntity;
import com.quick.questions.ws.io.entity.UserEntity;
import com.quick.questions.ws.io.repositories.AddressRepository;
import com.quick.questions.ws.io.repositories.UserRepository;
import com.quick.questions.ws.service.AddressService;
import com.quick.questions.ws.shared.dto.AddressDTO;
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public List<AddressDTO> getAddresses(String id) {
		List<AddressDTO> addresses = new ArrayList<AddressDTO>();
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity=userRepository.findByUserId(id);
		if(userEntity  ==  null) return addresses;
		
		Iterable<AddressEntity>  addressEntityList= addressRepository.findAllByUserDetails(userEntity);
		for (AddressEntity addressEntity : addressEntityList) {
			addresses.add(modelMapper.map(addressEntity, AddressDTO.class));
		}
		
		return addresses;
	}

	@Override
	public AddressDTO getAddress(String address_id) {
		AddressDTO addressesDto= null;
		
		AddressEntity addressEntity = addressRepository.findByAddressId(address_id);
		
		if(addressEntity != null) {
			addressesDto = new ModelMapper().map(addressEntity, AddressDTO.class);
		}
		return addressesDto;
	}

}
