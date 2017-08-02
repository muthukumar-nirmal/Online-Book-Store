/**
 * 
 */
package com.fixme.obs.service;

import java.util.List;

import com.fixme.obs.entity.User;


/**
 * @author muthu_m
 *
 */
public interface UserService {
	List<User> findAll();
	User addOrUpdateUser(User user);
	void deleteUser(Long id);
	User getUserByID(Long id);
	User getUserByEmail(String email);
}
