/**
 * 
 */
package com.fixme.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixme.obs.entity.Category;

/**
 * @author muthu_m
 *
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category getCategoryByName(String name);
}
