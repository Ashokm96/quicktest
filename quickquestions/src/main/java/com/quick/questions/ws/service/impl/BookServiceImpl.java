package com.quick.questions.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.questions.ws.exceptions.UserServiceException;
import com.quick.questions.ws.io.entity.BookEntity;
import com.quick.questions.ws.io.repositories.BookRepository;
import com.quick.questions.ws.service.BookService;
import com.quick.questions.ws.shared.dto.BookDTO;
import com.quick.questions.ws.ui.model.response.ErrorMessage;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		
		ModelMapper modelMapper = new ModelMapper();
		BookEntity bookentity = modelMapper.map(bookDTO, BookEntity.class);
		BookEntity storedBookEntity = bookRepository.save(bookentity);
		BookDTO returnedbookDTO=modelMapper.map(storedBookEntity, BookDTO.class);
		
		return returnedbookDTO;
	}

	@Override
	public BookDTO findBookById(long bookId) {
		// TODO Auto-generated method stub
		Optional<BookEntity> bookEntity=bookRepository.findById(bookId);
		BookEntity returnedBookEntity=bookEntity.get();
		ModelMapper modelMapper = new ModelMapper();
		BookDTO bookDto=modelMapper.map(returnedBookEntity, BookDTO.class);
		return bookDto;
	}

	@Override
	public List<BookDTO> findAllBooks() {
		// TODO Auto-generated method stub
		List<BookDTO> bookDtoList = new ArrayList<BookDTO>();
		Iterable<BookEntity> bookEntityList =bookRepository.findAll();
		for (BookEntity bookEntity : bookEntityList) {
			if(bookEntity.isActive()) {
				bookDtoList.add(new ModelMapper().map(bookEntity, BookDTO.class));
			}
			
		}
		return bookDtoList;
	}

	@Override
	public List<BookDTO> blurrySearch(String title) {
		// TODO Auto-generated method stub
		List<BookEntity> bookEntityList=bookRepository.findByTitle(title);
		List<BookDTO> booklist = new ArrayList<>();
		for (BookEntity bookEntity : bookEntityList) {
			ModelMapper modelMapper = new ModelMapper();
			BookDTO bookDTO=modelMapper.map(bookEntity, BookDTO.class);
			booklist.add(bookDTO);
		}
		
		
		return booklist;
	}

	@Override
	public void removeBook(long bookId) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(bookId);
		
	}

	@Override
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		// TODO Auto-generated method stub
		Optional<BookEntity> optionalBookEntity= bookRepository.findById(id);
		BookEntity bookEntity = optionalBookEntity.get();
		ModelMapper modelMapper = new ModelMapper();
		if(bookEntity == null) 
			throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
		
		BookEntity  updateBookEntity=modelMapper.map(bookDTO, BookEntity.class);
		BookEntity returnedbookEntity=bookRepository.save(updateBookEntity);
		BookDTO returnedbookDTO=modelMapper.map(returnedbookEntity, BookDTO.class);
		return returnedbookDTO;
	}

}
