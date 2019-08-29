package com.quick.questions.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.questions.ws.io.entity.CartItemEntity;
import com.quick.questions.ws.io.entity.ShoppingCartEntity;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Long> {

	List<CartItemEntity> findByShoppingCart(ShoppingCartEntity shoppingCartEntity);
}
