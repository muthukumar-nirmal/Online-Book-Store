/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fixme.obs.entity.User;
import com.fixme.obs.service.UserService;

/**
 * @author muthu_m
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User addOrUpdateUser(User user) {
		return null;
	}

	@Override
	public void deleteUser(Long id) {
	}
	
	@Override
	public User getUserByID(Long id) {
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		return null;
	}

}
