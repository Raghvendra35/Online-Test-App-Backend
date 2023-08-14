package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.model.online.Category;
import com.login.service.CategoryService;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class CategoryController
{

	@Autowired
	private CategoryService categoryService;
	
	
	//Add Category
	@PostMapping("category")
	public ResponseEntity<?> addCategory(@RequestBody Category category)
	{
		Category category1=this.categoryService.addCategory(category);
	    return ResponseEntity.ok(category1);	
	}

	//Get Sinle Category
	@GetMapping("category/{categoryId}")
	public Category getCategory(@PathVariable long categoryId)
	{
		return this.categoryService.getCategory(categoryId);
	}

	//Get All Category
	@GetMapping("categoryall")
	public ResponseEntity<?> getAllCategory()
	{
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	
	@PutMapping("category")
	public Category updateCategory(@RequestBody Category category)
	{
		return this.categoryService.updateCategory(category);
	}
	
	@DeleteMapping("category/{categoryId}")
	public void deleteCategory(@PathVariable long categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
	}
}






















