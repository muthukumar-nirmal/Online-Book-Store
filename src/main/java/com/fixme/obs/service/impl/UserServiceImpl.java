/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixme.obs.entity.User;
import com.fixme.obs.repository.UserRepository;
import com.fixme.obs.service.UserService;

/**
 * @author muthu_m
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User addOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findOne(id);
		if(user != null){
			userRepository.delete(id);
		}
	}
	
	@Override
	public User getUserByID(Long id) {
		User user = userRepository.findOne(id);
		if(user == null){
			return null;
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null){
			return null;
		}
		return user;
	}
}
