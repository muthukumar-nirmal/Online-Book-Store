/**
 * 
 */
package com.fixme.obs.service;

import java.util.List;

import com.fixme.obs.entity.LoginInfo;

/**
 * @author muthu_m
 *
 */
public interface LoginInfoService {
	
	LoginInfo save(LoginInfo loginInfo);
	List<LoginInfo> listAll();
	List<LoginInfo> findByEmail(String email);

}
