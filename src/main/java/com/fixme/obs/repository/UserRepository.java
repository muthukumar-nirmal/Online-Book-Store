/**
 * 
 */
package com.fixme.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixme.obs.entity.User;

/**
 * @author muthu_m
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
