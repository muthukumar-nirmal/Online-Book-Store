/**
 * 
 */
package com.fixme.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixme.obs.entity.LoginInfo;

/**
 * @author muthu_m
 *
 */
@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long>{
	
	List<LoginInfo> findByEmail(String email);

}
