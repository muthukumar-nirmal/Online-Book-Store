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

import com.fixme.obs.config.PasswordEncryption;
import com.fixme.obs.entity.User;
import com.fixme.obs.service.AddressService;
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
	
	@Autowired
	AddressService addressService;
		
	@Autowired
	PasswordEncryption passwordEncryption;
	
	/**
	 * This method is used to list all the user information 
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
	
	/**
	 * This method is used to add user information 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setPasswordHash(passwordEncryption.generateHash(user.getPasswordHash()));
		user.setRole("USER");
		user.setAddress(addressService.addOrUpdateAddress(user.getAddress()));
		userService.addOrUpdateUser(user);
		logger.debug("Added:: " + user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	/**
	 * This method is used to update existing user information 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User existingUser = userService.getUserByID(user.getId());
		if(existingUser == null){
			logger.debug("User with email " + user.getEmail() + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else{
			user.setAddress(addressService.addOrUpdateAddress(user.getAddress()));
			userService.addOrUpdateUser(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
	}
	
	/**
	 * Delete user by user id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		User user = userService.getUserByID(id);
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.deleteUser(id);
			logger.debug("User with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	/**
	 * This method is for getting user information by using email
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email") String email) {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			logger.debug("User with id " + email + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found User:: " + user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	/**
	 * This method is used to login with validation
	 * @param email
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/validateLogin", method = RequestMethod.GET)
	public ResponseEntity<User> validateLogin(@RequestParam(name = "email") String email, @RequestParam(name="password") String password){
		String hashedPassword = passwordEncryption.generateHash(password);
		User user = userService.getUserByEmail(email);
		if(user == null){
			logger.debug("User with id " + email + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		if(hashedPassword.equals(user.getPasswordHash())){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			logger.debug("User with id " + email + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * This method is to change password
	 * @param email
	 * @param oldPassword
	 * @param currentPassword
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ResponseEntity<Boolean> changePassword(@RequestParam(name = "email")String email, @RequestParam(name = "oldPassword")String oldPassword, @RequestParam(name = "currentPassword")String currentPassword){
		Boolean isPasswordChanged = false;
		String hashedCurrentPassword = passwordEncryption.generateHash(currentPassword);
		String hashedOldPassword = passwordEncryption.generateHash(oldPassword);
		User user = userService.getUserByEmail(email);
		if(hashedOldPassword.equals(user.getPasswordHash())){
			user.setPasswordHash(hashedCurrentPassword);
			userService.addOrUpdateUser(user);
			isPasswordChanged = true;
		}else{
			isPasswordChanged = false;
		}
		return new ResponseEntity<Boolean>(isPasswordChanged, HttpStatus.OK);
	}		
	
	/**
	 * This method is used if user forgot about the password
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ResponseEntity<User> forgotPassword(@RequestParam(name = "email")String email){
		User user = userService.getUserByEmail(email);
		if(user == null){
			logger.debug("User with email " + email + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		logger.debug("User exist with this email : " + email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
