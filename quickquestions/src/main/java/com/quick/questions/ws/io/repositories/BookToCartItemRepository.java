package com.quick.questions.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.quick.questions.ws.io.entity.BookToCartItemEntity;
import com.quick.questions.ws.io.entity.CartItemEntity;

public interface BookToCartItemRepository extends CrudRepository<BookToCartItemEntity, Long> {

	void deleteByCartItem(CartItemEntity cartItem);
}
