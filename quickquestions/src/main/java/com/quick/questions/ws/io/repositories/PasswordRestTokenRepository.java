package com.quick.questions.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.quick.questions.ws.io.entity.PasswordResetTokenEntity;

public interface PasswordRestTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {

	PasswordResetTokenEntity findByToken(String token);

}
