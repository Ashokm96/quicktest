package com.quick.questions.ws.service;

import java.util.List;

import com.quick.questions.ws.shared.dto.UserPaymentDTO;

public interface UserPaymentService {

	UserPaymentDTO findById(Long id);
	void removeById(Long id);
	List<UserPaymentDTO> findUserPaymentsByUserId(Long id);
}
