package com.login.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.model.online.Category;
import com.login.model.online.Quiz;
import com.login.repo.QuizRepository;
import com.login.service.QuizService;


@Service
public class QuizServiceImpl implements QuizService
{
	@Autowired
	private QuizRepository quizRepo;

	
	
	
	@Override
	public Quiz addQuiz(Quiz quiz) 
	{
			return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz)
	{
		return this.quizRepo.save(quiz);
	}

	@Override
	public List<Quiz> getQuizzes() 
	{
		return this.quizRepo.findAll();
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		
		return this.quizRepo.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) 
	{
	   this.quizRepo.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) 
	{
		return this.quizRepo.findByCategory(category);
	}
	
	
	
  
	@Override
	public List<Quiz> findByCategoryAndActive(Category c, Boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getActiveQuizzes() 
	{
	     return this.quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesCategory(Category c)
	{
		return this.quizRepo.findByCategoryAndActive(c,true);
	}

}
