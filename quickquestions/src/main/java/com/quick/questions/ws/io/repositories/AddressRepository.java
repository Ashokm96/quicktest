package com.quick.questions.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.quick.questions.ws.io.entity.AddressEntity;
import com.quick.questions.ws.io.entity.UserEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);

	AddressEntity findByAddressId(String addressId);


}
