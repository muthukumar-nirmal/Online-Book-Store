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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fixme.obs.entity.Category;
import com.fixme.obs.service.CategoryService;

/**
 * @author muthu_m
 *
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	final static Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * Add New category 
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		categoryService.addOrUpdateCategory(category);
		logger.debug("Added:: " + category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	/**
	 * Update the existing category
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		Category existingCategory = categoryService.getCategoryByID(category.getCategoryId());
		if(existingCategory == null){
			logger.debug("Category with id " + category.getCategoryId() + " does not exists");
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}else{
			categoryService.addOrUpdateCategory(category);
			return new ResponseEntity<Category>(HttpStatus.OK);
		}
	}
	
	/**
	 * Get the categories by name
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getByCategoryName(@RequestParam(value = "name") String name){
		List<Category> categories = categoryService.getCategoryByName(name);
		if(categories.isEmpty()){
			logger.debug("Category with name " + name + "does not exists");
			return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Category with name : " + name + "found");
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	/**
	 * Get the categories by ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getByCategoryID(@PathVariable Long id){
		Category category = categoryService.getCategoryByID(id);
		if(category == null){
			logger.debug("Category with id " + id + "dose not exists");
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Category with id : " + id + "found");
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	/**
	 * List all the categories
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.findAll();
		if(categories.isEmpty()){
			logger.debug("Category does not exists");
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + categories.size() + " categories");
		logger.debug(Arrays.toString(categories.toArray()));
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	/**
	 * Delete category by category id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
		Category category = categoryService.getCategoryByID(id);
		if (category == null) {
			logger.debug("Category with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			categoryService.deleteCategory(id);
			logger.debug("Category with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}
