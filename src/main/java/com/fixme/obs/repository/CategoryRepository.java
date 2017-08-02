/**
 * 
 */
package com.fixme.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixme.obs.entity.Category;

/**
 * @author muthu_m
 *
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category> findByName(String name);
}
