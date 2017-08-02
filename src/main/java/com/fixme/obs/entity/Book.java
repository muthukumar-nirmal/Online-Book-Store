/**
 * 
 */
package com.fixme.obs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author muthu_m
 *
 */
@Entity
@Table(name = "tbl_book")
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	public Book() {}
	
	public Book(String title, String author, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString(){
		if (category != null) {
			return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nPrice: " + price + "$\nCategory: " + this.getCategory();
		} else {
			return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nPrice: " + price + "$";
		}
	}
}
