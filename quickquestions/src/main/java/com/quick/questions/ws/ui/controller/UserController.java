package com.quick.questions.ws.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quick.questions.ws.service.AddressService;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.dto.AddressDTO;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.ui.model.request.UserDetailsRequestModel;
import com.quick.questions.ws.ui.model.response.AddressResponseModel;
import com.quick.questions.ws.ui.model.response.OperationStatusModel;
import com.quick.questions.ws.ui.model.response.RequestOperationName;
import com.quick.questions.ws.ui.model.response.RequestOperationStatus;
import com.quick.questions.ws.ui.model.response.UserDetailsResponseModel;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressesService;
	
	@GetMapping(path ="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel getUsers(@PathVariable String id) {
		
		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
		
		
		
		UserDto	getUser =userService.getUserByUserId(id);
		BeanUtils.copyProperties(getUser, userDetailsResponseModel);
		return userDetailsResponseModel;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDeatialsReqModel) throws Exception {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		
		if(userDeatialsReqModel.getFirstName().isEmpty())
			throw new NullPointerException("The Object is null");
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDeatialsReqModel, UserDto.class);
		/*
		 * UserDto userDto = new UserDto();
		 * BeanUtils.copyProperties(userDeatialsReqModel, userDto);
		 */
		
		UserDto	createdUser=userService.createUser(userDto);
		userResponse = modelMapper.map(createdUser, UserDetailsResponseModel.class);
		
		return userResponse;
	}
	
	@PutMapping(
			path ="/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel updateUser(@RequestBody UserDetailsRequestModel userDeatialsReqModel, @PathVariable String id) {
		
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		
		if(userDeatialsReqModel.getFirstName().isEmpty())
			throw new NullPointerException("The Object is null");
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDeatialsReqModel, userDto);
		
		UserDto	updatedUser=userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updatedUser, userResponse);
		return userResponse;
	}
	@DeleteMapping(
			path ="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		userService.deleteUser(id);
		return operationStatusModel;
	}
	
	@GetMapping( 
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserDetailsResponseModel> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value="limit", defaultValue = "5") int limit ) {
		
		List<UserDetailsResponseModel> userDetailsResponseModelList = new ArrayList<>();
		
		List<UserDto> userDtoList = userService.getUsers(page, limit);
		
		for (UserDto userDto : userDtoList) {
			
			UserDetailsResponseModel userResponseModel = new UserDetailsResponseModel();
			BeanUtils.copyProperties(userDto, userResponseModel);
			userDetailsResponseModelList.add(userResponseModel);
		}
		
		return userDetailsResponseModelList;
	}
	
	@GetMapping(path = "/{id}/addresses",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,
					"application/hal+json"})
	public List<AddressResponseModel> getUserAddresses(@PathVariable String id) {
		
		List<AddressResponseModel> addressResponseModel = new ArrayList<>();
		
		List<AddressDTO> addressesDto= addressesService.getAddresses(id);
		
		if(addressesDto != null && !addressesDto.isEmpty()) {
			
			Type listType = new TypeToken<List<AddressResponseModel>>() {}.getType();
			
			
			
			 addressResponseModel = new ModelMapper().map(addressesDto, listType);
			/*
			 * for (AddressResponseModel addressResponse : addressResponseModel) { Link
			 * addressLink = linkTo(methodOn(UserController.class).getUserAddress(id,
			 * addressResponse.getAddressId())) .withSelfRel();
			 * addressResponse.add(addressLink); Link userLink =
			 * linkTo(methodOn(UserController.class).getUsers(id)) .withRel("user");
			 * addressResponse.add(userLink); }
			 */
		}
		 return addressResponseModel;
		
		//return new Resources<>(addressResponseModel);
	}
	
	@GetMapping(path = "/{id}/addresses/{address_id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, 
					"application/hal+json"})
	public AddressResponseModel getUserAddress(@PathVariable String id,@PathVariable String address_id) {
		
		
		
		AddressDTO addressesDto= addressesService.getAddress(address_id);
		
		
			ModelMapper modelMapper = new ModelMapper();
		/*
		 * Link addressLink = linkTo(methodOn(UserController.class).getUserAddress(id,
		 * address_id)) .withSelfRel(); Link userLink =
		 * linkTo(methodOn(UserController.class).getUsers(id)) .withRel("user"); Link
		 * addressesLink =
		 * linkTo(methodOn(UserController.class).getUserAddresses(address_id))
		 * .withRel("addresses");
		 */
			AddressResponseModel addressResponseModel = modelMapper.map(addressesDto, AddressResponseModel.class);
		/*
		 * addressResponseModel.add(addressLink); addressResponseModel.add(userLink);
		 * addressResponseModel.add(addressesLink);
		 */
		
			return new ModelMapper().map(addressesDto, AddressResponseModel.class );
		//return new Resource<AddressResponseModel>(addressResponseModel);
	}
}
