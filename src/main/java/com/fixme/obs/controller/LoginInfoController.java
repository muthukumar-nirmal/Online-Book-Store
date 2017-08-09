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

import com.fixme.obs.entity.LoginInfo;
import com.fixme.obs.service.LoginInfoService;

/**
 * @author muthu_m
 *
 */
@RestController
@RequestMapping("/api/loginInfo")
public class LoginInfoController {
	
	final static Logger logger = Logger.getLogger(LoginInfoController.class);
	
	@Autowired
	LoginInfoService loginInfoService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<LoginInfo>> getAllLoginInfo(){
		List<LoginInfo> list = loginInfoService.listAll();
		if(list.isEmpty()){
			logger.debug("Login info does not exists");
			return new ResponseEntity<List<LoginInfo>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + list.size() + " login info");
		logger.debug(Arrays.toString(list.toArray()));
		return new ResponseEntity<List<LoginInfo>>(list, HttpStatus.OK);
	}	
}
