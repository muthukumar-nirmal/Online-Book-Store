/**
 * 
 */
package com.fixme.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author muthu_m
 *
 */
@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		System.getProperties().put("server.port", 8080);
		SpringApplication.run(BookStoreApplication.class, args);
	}
}
