/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixme.obs.entity.LoginInfo;
import com.fixme.obs.repository.LoginInfoRepository;
import com.fixme.obs.service.LoginInfoService;

/**
 * @author muthu_m
 *
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService {

	@Autowired
	LoginInfoRepository loginInfoRepository;

	@Override
	public LoginInfo save(LoginInfo loginInfo) {
		return loginInfoRepository.save(loginInfo);
	}

	@Override
	public List<LoginInfo> listAll() {
		return loginInfoRepository.findAll();
	}

	@Override
	public List<LoginInfo> findByEmail(String email) {
		return loginInfoRepository.findByEmail(email);
	}

}
