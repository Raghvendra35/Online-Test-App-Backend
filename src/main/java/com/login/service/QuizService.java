package com.login.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.login.model.online.Category;
import com.login.model.online.Quiz;

public interface QuizService 
{

	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public List<Quiz> getQuizzes();
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	public List<Quiz> getQuizzesOfCategory(Category category);

    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
    
    public List<Quiz> getActiveQuizzes();
    public List<Quiz> getActiveQuizzesCategory(Category c);
    
}
