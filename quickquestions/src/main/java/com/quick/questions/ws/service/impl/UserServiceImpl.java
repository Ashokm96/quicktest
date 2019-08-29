package com.quick.questions.ws.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.exceptions.UserServiceException;
import com.quick.questions.ws.io.entity.PasswordResetTokenEntity;
import com.quick.questions.ws.io.entity.UserBillingEntity;
import com.quick.questions.ws.io.entity.UserEntity;
import com.quick.questions.ws.io.entity.UserPaymentEntity;
import com.quick.questions.ws.io.entity.UserShippingEntity;
import com.quick.questions.ws.io.repositories.PasswordRestTokenRepository;
import com.quick.questions.ws.io.repositories.UserBillingRepository;
import com.quick.questions.ws.io.repositories.UserPaymentRepository;
import com.quick.questions.ws.io.repositories.UserRepository;
import com.quick.questions.ws.io.repositories.UserShippingRepository;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.AmazonSES;
import com.quick.questions.ws.shared.Utills;
import com.quick.questions.ws.shared.dto.AddressDTO;
import com.quick.questions.ws.shared.dto.UserBillingDTO;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.shared.dto.UserPaymentDTO;
import com.quick.questions.ws.shared.dto.UserRoleDto;
import com.quick.questions.ws.shared.dto.UserShippingDTO;
import com.quick.questions.ws.ui.model.response.ErrorMessage;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utills utils;
	
	@Autowired
	PasswordRestTokenRepository passwordRestTokenRepository;
	
	
	@Autowired
	AmazonSES amazonSES;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserBillingRepository  userBillingRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		if(userRepository.findByEmail(userdto.getEmail()) != null) throw new RuntimeException("user already exists");
		//UserEntity userEntity = new UserEntity();
		
		for (int i = 0; i < userdto.getAddresses().size(); i++) {
			
			AddressDTO addressDto = userdto.getAddresses().get(i);
			addressDto.setUserDetails(userdto);
			addressDto.setAddressId(utils.generatedAddressId(30));
			userdto.getAddresses().set(i, addressDto);
			
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Iterator<UserRoleDto>  userRolesDto =userdto.getUserRoles().iterator();
		while (userRolesDto.hasNext()) {
			UserRoleDto userRoleDto = (UserRoleDto) userRolesDto.next();
			userRoleDto.setUserDetails(userdto);
		}
		userdto.setUserPaymentList(new ArrayList<UserPaymentDTO>());
		
		UserEntity userEntity = modelMapper.map(userdto, UserEntity.class);
		//BeanUtils.copyProperties(userdto, userEntity);
		
		
		
		String userId =utils.generatedString(30);
		userEntity.setUserId(userId);
		userEntity.setEmailVerificationStatus(false);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(userId));
		UserEntity storedUserEntity=userRepository.save(userEntity);
		
		
		 UserDto returnedUserDto = modelMapper.map(storedUserEntity, UserDto.class);
		//BeanUtils.copyProperties(storedUserEntity, returnedUserDto);
		 
		 amazonSES.verifyEmail(returnedUserDto);
		
		return returnedUserDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), 
				userEntity.getEncryptedPassword(), 
				userEntity.getEmailVerificationStatus(), 
				true, true, true,
				new ArrayList<>());
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
		ModelMapper modelMapper = new ModelMapper();
		if(userEntity == null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
		returnedUserDto = modelMapper.map(userEntity, UserDto.class);
		//BeanUtils.copyProperties(userEntity, returnedUserDto);
		return returnedUserDto;
	}

	@Override
	public UserDto updateUser(String userId,UserDto userDto) {
		// TODO Auto-generated method stub
		UserDto updatedUserDto = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) 
			throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity updatedUserEntity= userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserEntity, updatedUserDto);
		return updatedUserDto;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
		}
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		if(page >0 ) page = page-1;
		Pageable pageable = PageRequest.of(page, limit);
		Page<UserEntity>  userPage= userRepository.findAll(pageable);
		List<UserEntity> userEntityList = userPage.getContent();
		
		for (UserEntity userEntity : userEntityList) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}
	
	@Override
	public boolean verifyEmailToken(String token) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
		if (userEntity != null) {
			
			boolean hasTokenExpired = Utills.hasTokenExpired(token);
			if (!hasTokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepository.save(userEntity);
				returnValue = true;
			}
			
		} 
		
		return returnValue;
	}

	@Override
	public boolean resetPassword(String email) {
		
		boolean returnValue = false;
		
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) {
			return returnValue;
		}
		
		String passwordResetToken = utils.generatePasswordResetToken(userEntity.getUserId());
		
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		
		passwordResetTokenEntity.setToken(passwordResetToken);
		passwordResetTokenEntity.setUserDetails(userEntity);
		
		passwordRestTokenRepository.save(passwordResetTokenEntity);
		
		returnValue  = new AmazonSES().sendPasswordResetRequest(
				userEntity.getFirstName(), userEntity.getEmail(), passwordResetToken);
		
		return returnValue;
	}

	@Override
	public boolean resetPasswordReq(String token, String password) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		
		if(Utills.hasTokenExpired(token)) {
			return returnValue;
		}
		PasswordResetTokenEntity passwordResetTokenEntity = passwordRestTokenRepository.findByToken(token);
		if(passwordResetTokenEntity == null) {
			return returnValue;
		}
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		
		if(savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}
		passwordRestTokenRepository.delete(passwordResetTokenEntity);
		
		return returnValue;
	}

	@Override
	public UserDto updateUserBilling(UserBillingDTO userBillingDTO, UserPaymentDTO userPaymentDTO, UserDto userDto) {
		// TODO Auto-generated method stub
		userPaymentDTO.setUser(userDto);
		userPaymentDTO.setUserBilling(userBillingDTO);
		userPaymentDTO.setDefaultPayment(true);
		userBillingDTO.setUserPayment(userPaymentDTO);
		ModelMapper modelMapper = new ModelMapper();
		UserPaymentEntity userPaymentEntity = modelMapper.map(userPaymentDTO, UserPaymentEntity.class);
		userPaymentRepository.save(userPaymentEntity);
		//userDto.getUserPaymentDTOs().add(userPaymentDTO);
		UserDto updatedUserDto = updateUser(userDto.getUserId(), userDto);
		return updatedUserDto;
	}

	@Override
	public void setUserDefaultPayment(long id, UserDto userDto) {
		// TODO Auto-generated method stub
		
		Iterable<UserPaymentEntity> iterable=userPaymentRepository.findAll();
		for (UserPaymentEntity userPaymentEntity : iterable) {
			if(userPaymentEntity.getId() == id) {
				userPaymentEntity.setDefaultPayment(true);
				userPaymentRepository.save(userPaymentEntity);
			}else {
				userPaymentEntity.setDefaultPayment(false);
				userPaymentRepository.save(userPaymentEntity);
			}
		}
	}

	@Override
	public UserDto updateUserPaymentInfo(UserBillingDTO userBillingDTO, UserPaymentDTO userPaymentDTO,
			UserDto userDto) {
		// TODO Auto-generated method stub
		
		UserDto updatedUserDto = updateUser(userDto.getUserId(), userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserBillingEntity userBillingEntity = modelMapper.map(userBillingDTO, UserBillingEntity.class);
		userBillingRepository.save(userBillingEntity);
		UserPaymentEntity userPaymentEntity= modelMapper.map(userPaymentDTO, UserPaymentEntity.class);
		userPaymentRepository.save(userPaymentEntity);
		return updatedUserDto;
	}

	@Override
	public UserShippingDTO updateUserShipping(UserShippingDTO userShippingDTO, UserDto userDto) {
		// TODO Auto-generated method stub
		userShippingDTO.setUser(userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserShippingEntity userShippingEntity=modelMapper.map(userShippingDTO, UserShippingEntity.class);
		UserShippingEntity savedUserShippingEntity=userShippingRepository.save(userShippingEntity);
		UserShippingDTO returnedUserShippingDTO=modelMapper.map(savedUserShippingEntity, UserShippingDTO.class);
		//UserDto updatedUserDto = updateUser(userDto.getUserId(), userDto);
		return returnedUserShippingDTO;
	}
	
	@Override
	public void setUserDefaultShipping(Long userShippingId, UserDto userDto) {
		List<UserShippingEntity> userShippingList = (List<UserShippingEntity>) userShippingRepository.findAll();
		
		for (UserShippingEntity userShipping : userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
	}
}
