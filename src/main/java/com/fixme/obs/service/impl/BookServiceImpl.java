/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixme.obs.entity.Book;
import com.fixme.obs.repository.BookRepository;
import com.fixme.obs.service.BookService;

/**
 * @author muthu_m
 *
 */
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book addOrUpdateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findOne(id);
		if(book != null){
			bookRepository.delete(book);
		}
	}
	
	@Override
	public Book getBookByID(Long id) {
		Book book = bookRepository.findOne(id);
		if(book == null){
			return null;
		}
		return book;
	}

	@Override
	public List<Book> getBookByTitle(String isbn) {
		List<Book> books = bookRepository.findByIsbn(isbn);
		if(books.isEmpty()){
			return null;
		}
		return books;
	}
}
