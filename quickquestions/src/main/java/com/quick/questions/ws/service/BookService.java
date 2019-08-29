package com.quick.questions.ws.service;

import java.util.List;

import com.quick.questions.ws.shared.dto.BookDTO;

public interface BookService {

	BookDTO createBook(BookDTO bookDTO);
	BookDTO findBookById(long bookId);
	List<BookDTO> findAllBooks();
	List<BookDTO> blurrySearch(String title);
	void removeBook(long bookId);
	BookDTO updateBook(Long id, BookDTO bookDTO);
}
