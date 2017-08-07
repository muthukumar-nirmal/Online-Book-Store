/**
 * 
 */
package com.fixme.obs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixme.obs.entity.Address;
import com.fixme.obs.repository.AddressRepository;
import com.fixme.obs.service.AddressService;

/**
 * @author muthu_m
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address addOrUpdateAddress(Address address) {
		return addressRepository.save(address);
	}
}
