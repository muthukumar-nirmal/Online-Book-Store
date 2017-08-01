/**
 * 
 */
package com.fixme.obs.service;

import java.util.List;

import com.fixme.obs.entity.Category;

/**
 * @author muthu_m
 *
 */
public interface CategoryService {
	List<Category> findAll();
	Category addOrUpdateCategory(Category category);
	void deleteCategory(Long id);
	Category getCategoryByID(Long id);
	Category getCategoryByName(String name);
}
