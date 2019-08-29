package com.quick.questions.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.questions.ws.io.entity.UserPaymentEntity;
@Repository
public interface UserPaymentRepository extends CrudRepository<UserPaymentEntity, Long> {

	List<UserPaymentEntity> findUserPaymentsByUserId(Long id); 
}
