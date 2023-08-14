package com.login.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.model.online.Question;
import com.login.model.online.Quiz;
import com.login.repo.QuestionRepository;
import com.login.service.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService
{

	@Autowired
	private QuestionRepository questionRepo;
	
	
	@Override
	public Question addQuestion(Question question)
	{
		return this.questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) 
	{
		return  this.questionRepo.save(question);
	}	

	@Override
	public List<Question> getQuestion() 
	{
		return  this.questionRepo.findAll();
	}

	@Override
	public Question getQuestion(Long questionId) 
	{
		return this.questionRepo.findById(questionId).get();
	}

	

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz)
	{
		return this.questionRepo.findByQuiz(quiz);
		//return null;
	}

	
	
	@Override
	public void deleteQuestion(Long questionId) 
	{
		Question question=new Question();
		 question.setQuestionId(questionId);
		
		this.questionRepo.delete(question);
		
	}

	
	
	@Override
	public Question get(Long questionnId)
	{
		
		return this.questionRepo.getOne(questionnId);
	}

	
}
