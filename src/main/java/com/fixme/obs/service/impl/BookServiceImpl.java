/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fixme.obs.entity.Book;
import com.fixme.obs.service.BookService;

/**
 * @author muthu_m
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Override
	public List<Book> findAll() {
		return null;
	}

	@Override
	public Book addOrUpdateBook(Book book) {
		return null;
	}

	@Override
	public void deleteBook(Long id) {

	}
	@Override
	public Book getBookByID(Long id) {
		return null;
	}

	@Override
	public Book getBookByTitle(String title) {
		return null;
	}

}
