package com.login.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.model.online.Category;
import com.login.model.online.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> 
{

	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
}
