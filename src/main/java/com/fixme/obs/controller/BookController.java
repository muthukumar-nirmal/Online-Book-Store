/**
 * 
 */
package com.fixme.obs.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
