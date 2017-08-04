/**
 * 
 */
package com.fixme.obs.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author muthu_m
 *
 */
@Service
public class PasswordEncryption {
	
	final static Logger logger = Logger.getLogger(PasswordEncryption.class);
	
	public static final String SALT = "GEWB#@232jfaSFWAF";
	public static String saltedPassword  = "";
	
	public String generateHash(String input) {
		saltedPassword = SALT + input;
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(saltedPassword.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.debug("No Algorithm Found :" + e.getMessage());
		}

		return hash.toString();
	}

}