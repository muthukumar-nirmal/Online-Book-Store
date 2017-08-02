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

import com.fixme.obs.entity.User;
import com.fixme.obs.service.UserService;

/**
 * @author muthu_m
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

final static Logger logger = Logger.getLogger(BookController.class);
	
	@Autowired
	UserService userService;
			
	/**
	 * List all the books
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllBooks(){
		List<User> users = userService.findAll();
		if(users.isEmpty()){
			logger.debug("User does not exists");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.size() + " users");
		logger.debug(Arrays.toString(users.toArray()));
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}		
			
}
