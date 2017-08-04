/**
 * 
 */
package com.fixme.obs.service;

import java.util.List;

import com.fixme.obs.entity.Book;

/**
 * @author muthu_m
 *
 */
public interface BookService {
	List<Book> findAll();
	Book addOrUpdateBook(Book book);
	void deleteBook(Long id);
	Book getBookByID(Long id);
	List<Book> getBookByISBN(String isbn);
}
