package com.quick.questions.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.quick.questions.ws.service.BookService;
import com.quick.questions.ws.shared.dto.BookDTO;
import com.quick.questions.ws.ui.model.request.BookRequestModel;
import com.quick.questions.ws.ui.model.response.BookResponseModel;
import com.quick.questions.ws.ui.model.response.OperationStatusModel;
import com.quick.questions.ws.ui.model.response.RequestOperationName;
import com.quick.questions.ws.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	@PostMapping(path = "/addBook",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookResponseModel createBook(@RequestBody BookRequestModel bookRequestModel) {
		
		BookResponseModel returnBookResponseModel = new BookResponseModel();
		
		ModelMapper modelMapper = new ModelMapper();
		
		BookDTO bookDTO = modelMapper.map(bookRequestModel, BookDTO.class);
		
		BookDTO createdBookDto=bookService.createBook(bookDTO);
		
		returnBookResponseModel = modelMapper.map(createdBookDto, BookResponseModel.class);
		
		return returnBookResponseModel;
	}
	
	@GetMapping(path = "/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookResponseModel getBook(@PathVariable long id) {
		BookResponseModel returnBookResponseModel = new BookResponseModel();
		
		BookDTO bookDTO=bookService.findBookById(id);
		ModelMapper modelMapper = new ModelMapper();
		returnBookResponseModel = modelMapper.map(bookDTO, BookResponseModel.class);
		return returnBookResponseModel;
	}
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<BookResponseModel> getAllBook() {
		List<BookResponseModel> returnBookResponseModel = new ArrayList<BookResponseModel>();
		
		List<BookDTO> bookDTOList=bookService.findAllBooks();
		ModelMapper modelMapper = new ModelMapper();
		for (BookDTO bookDTO : bookDTOList) {
			BookResponseModel bookResponseModel = new BookResponseModel();
			bookResponseModel = modelMapper.map(bookDTO, BookResponseModel.class);
			returnBookResponseModel.add(bookResponseModel);
		}
		
		return returnBookResponseModel;
	}
	
	@GetMapping(path="/searchBook",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<BookResponseModel> blurrySearch(@RequestParam(value = "search") String search) {
		
		List<BookResponseModel> returnBookResponseModel = new ArrayList<BookResponseModel>();

		List<BookDTO> bookDTOList=bookService.blurrySearch(search);
		ModelMapper modelMapper = new ModelMapper();
		for (BookDTO bookDTO : bookDTOList) {
			BookResponseModel bookResponseModel = new BookResponseModel();
			bookResponseModel = modelMapper.map(bookDTO, BookResponseModel.class);
			returnBookResponseModel.add(bookResponseModel);
		}
		return returnBookResponseModel;
	}
	@PutMapping(path = "/updateBook/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public BookResponseModel updateBook(@PathVariable Long id,@RequestBody BookRequestModel bookRequestModel) {
		
		BookResponseModel returnBookResponseModel = new BookResponseModel();
		
		ModelMapper modelMapper = new ModelMapper();
		
		BookDTO bookDTO = modelMapper.map(bookRequestModel, BookDTO.class);
		
		BookDTO createdBookDto=bookService.updateBook(id,bookDTO);
		
		returnBookResponseModel = modelMapper.map(createdBookDto, BookResponseModel.class);
		
		return returnBookResponseModel;
	}
	@DeleteMapping(path = "deleteBook/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteBook(@PathVariable long id) {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		bookService.removeBook(id);
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
	
}
