package com.quick.questions.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.questions.ws.io.entity.BookEntity;
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
	List<BookEntity> findByTitle(String keyword);
}
