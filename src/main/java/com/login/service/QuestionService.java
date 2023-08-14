package com.login.service;

import java.util.List;
import java.util.Set;

import com.login.model.online.Question;
import com.login.model.online.Quiz;

public interface QuestionService 
{

	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public List<Question> getQuestion();
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionOfQuiz(Quiz quiz);
	public void deleteQuestion(Long questionId);
	
	public Question get(Long questionnId);
}
