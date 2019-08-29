package com.quick.questions.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.questions.ws.io.entity.UserShippingEntity;

@Repository
public interface UserShippingRepository extends CrudRepository<UserShippingEntity, Long> {

}
