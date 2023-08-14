package com.login.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.model.online.Category;
import com.login.repo.CategoryRepository;
import com.login.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepo;

	
	@Override
	public Category addCategory(Category category) 
	{
		return this.categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) 
	{
		
		return this.categoryRepo.save(category);
	}

	@Override
	public List<Category> getCategories()
	{
		return this.categoryRepo.findAll();
	}

	@Override
	public Category getCategory(Long categoryId)
	{
		
		return this.categoryRepo.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId)
	{
		Category category=new Category();
		category.setCategoryId(categoryId);
		this.categoryRepo.delete(category);
		
	}

}
