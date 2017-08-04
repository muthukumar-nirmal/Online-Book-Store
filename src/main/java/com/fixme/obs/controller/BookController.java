/**
 * 
 */
package com.fixme.obs.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fixme.obs.entity.Book;
import com.fixme.obs.service.BookService;

/**
 * @author muthu_m
 *
 */
@RestController
@RequestMapping("/api/book")
public class BookController {
	
	final static Logger logger = Logger.getLogger(BookController.class);
	
	@Autowired
	BookService bookService;
	
	
	/**
	 * List all the books
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookService.findAll();
		if(books.isEmpty()){
			logger.debug("Book does not exists");
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + books.size() + " books");
		logger.debug(Arrays.toString(books.toArray()));
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	/**
	 * This method is used to add no of books to the system
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		bookService.addOrUpdateBook(book);
		logger.debug("Added:: " + book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	/**
	 * Update the existing book
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		Book existingBook = bookService.getBookByID(book.getId());
		if(existingBook == null){
			logger.debug("Book with id " + book.getId() + " does not exists");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}else{
			bookService.addOrUpdateBook(book);
			return new ResponseEntity<Book>(HttpStatus.OK);
		}
	}
	
	/**
	 * This method used to get all the books by isbn
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBookByISBN(@RequestParam(value = "isbn") String isbn){
		List<Book> books = bookService.getBookByISBN(isbn);
		if(books.isEmpty()){
			logger.debug("Book with isbn " + isbn + "does not exists");
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Book with isbn " + isbn + "found");
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	/**
	 * This method used to get all the book information by book id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBookByID(@PathVariable Long id){
		Book book = bookService.getBookByID(id);
		if(book == null){
			logger.debug("Book with id " + id + "dose not exists");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Book with id : " + id + "found");
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	/**
	 * This method is used to delete the books by using book id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
		Book book = bookService.getBookByID(id);
		if (book == null) {
			logger.debug("Book with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			bookService.deleteBook(id);
			logger.debug("Book with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}
