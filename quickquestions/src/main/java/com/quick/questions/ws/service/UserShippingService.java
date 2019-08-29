package com.quick.questions.ws.service;

import com.quick.questions.ws.shared.dto.UserShippingDTO;

public interface UserShippingService {

	UserShippingDTO findById(Long id);
	
	void removeById(Long id);
}
