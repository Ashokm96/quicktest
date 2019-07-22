package com.quick.questions.ws.service;

import java.util.List;

import com.quick.questions.ws.shared.dto.AddressDTO;

public interface AddressService {

	List<AddressDTO> getAddresses(String id);

	AddressDTO getAddress(String address_id);
}
