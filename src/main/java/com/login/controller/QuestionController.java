package com.login.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RestController;

import com.login.model.online.Question;
import com.login.model.online.Quiz;
import com.login.service.QuestionService;
import com.login.service.QuizService;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class QuestionController 
{

	@Autowired
	private QuestionService questionService;//here object  will create of implements class ka that object will come here
	@Autowired
	private QuizService quizService;
	
	
	//Add Question
	@PostMapping("question")
	public ResponseEntity<?> addQuestion(@RequestBody Question question)
	{
		Question q1=this.questionService.addQuestion(question);
	    return ResponseEntity.ok(q1);	
	}

	
	
	//Get Sinle Question 
	@GetMapping("question/{questionId}")
	public Question getQuestion(@PathVariable long questionId)
	{
		return this.questionService.getQuestion(questionId);
	}

	
	
	//Get All Question
	@GetMapping("questionall")
	public ResponseEntity<?> getAllQuiz()
	{
		return ResponseEntity.ok(this.questionService.getQuestion());
	}
	
	
	
	
	@PutMapping("question")
	public Question updateQuestion(@RequestBody Question question)
	{
		return this.questionService.updateQuestion(question);
	}
	
	
	//get All question of any quiz
	@GetMapping("question/quiz/{quizid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizid") Long quizId)
	{
//		Quiz quiz=new Quiz();
//		quiz.setQuizId(quizId);
//		java.util.Set<Question> questionOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
//		return ResponseEntity.ok(questionOfQuiz);
		
		//Here  we get maximum number of questiom in sinlge object
		Quiz quiz=this.quizService.getQuiz(quizId);
		Set<Question> questions=quiz.getQuestions();
		//here we will send question jitne number of question ka size hoga
		
		List<Question> list=new ArrayList(questions);
		
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()))
		{
			//jitne Question bhejne use jyda question hai database 
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		
		
		list.forEach(q->{   //this thing we are doing not displaying answer to client on inspect fields
			               //here we are use one model for display answer to client and Admin so that' why we set blank answer when client wii
			               // test then he can see answer 
			q.setAnswer("");
		});
		
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
		
	
	
	
	//get All question of any quiz
	@GetMapping("question/quiz/all/{quizid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("quizid") Long quizId)
	{
		Quiz quiz=new Quiz();
		quiz.setQuizId(quizId);
		java.util.Set<Question> questionOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
		

	}
		
	
	//Delete
	@DeleteMapping("question/{questId}")
	public void delete(@PathVariable Long questId)
	{
		this.questionService.deleteQuestion(questId);
	}
	
	
	//evaluate quiz
	@PostMapping("question/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
		 double marksGot=0;
		 int correctAnswers=0;
		 int  attempted=0; 
		  
		System.out.println(questions);
		 for(Question q: questions)
		{
			System.out.println(q.getGivenAnswer());
			
			//Single Questions
		  Question question=this.questionService.get(q.getQuestionId());
		  if(question.getAnswer().equals(q.getGivenAnswer()))
		  {
			  //correct answer
			  correctAnswers++;
			  
			  double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
			  marksGot+=marksSingle;
			  
		  }
		  if(q.getGivenAnswer()!=null)
		  {
			  attempted++;
		  }
		}
		 
		 Map<String, Object> map=Map.of("marksGot", marksGot,"correctAnswers",correctAnswers,"attempted", attempted);
		 
		return ResponseEntity.ok(map);
	}
	

}
