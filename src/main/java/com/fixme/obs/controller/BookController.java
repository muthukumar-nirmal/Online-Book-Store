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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}