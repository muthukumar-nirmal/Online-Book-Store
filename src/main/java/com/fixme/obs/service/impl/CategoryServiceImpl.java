/**
 * 
 */
package com.fixme.obs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixme.obs.entity.Category;
import com.fixme.obs.repository.CategoryRepository;
import com.fixme.obs.service.CategoryService;

/**
 * @author muthu_m
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addOrUpdateCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findOne(id);
		if (category != null) {
			categoryRepository.delete(category);
		}
	}

	@Override
	public Category getCategoryByID(Long id) {
		Category category = categoryRepository.findOne(id);
		if (category != null) {
			return category;
		}
		return null;
	}

	@Override
	public Category getCategoryByName(String name) {
		Category category = categoryRepository.getCategoryByName(name);
		if (category != null) {
			return category;
		}
		return null;
	}

}
