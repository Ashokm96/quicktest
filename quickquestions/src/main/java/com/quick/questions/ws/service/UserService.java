package com.quick.questions.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quick.questions.ws.shared.dto.UserBillingDTO;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.shared.dto.UserPaymentDTO;
import com.quick.questions.ws.shared.dto.UserShippingDTO;

public interface UserService  extends UserDetailsService{

	UserDto createUser(UserDto userdto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean resetPassword(String email);
	boolean resetPasswordReq(String token, String password);
	UserDto updateUserBilling(UserBillingDTO userBillingDTO, UserPaymentDTO userPaymentDTO, UserDto userDto);
	void setUserDefaultPayment(long parseLong, UserDto userDto);
	UserDto updateUserPaymentInfo(UserBillingDTO userBillingDTO, UserPaymentDTO userPaymentDTO , UserDto userDto);
	UserShippingDTO updateUserShipping(UserShippingDTO userShippingDTO, UserDto userDto);
	void setUserDefaultShipping(Long userShippingId, UserDto userDto);
}
