package com.login.controller;

import java.util.List;

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

import com.login.model.online.Category;
import com.login.model.online.Quiz;
import com.login.service.CategoryService;
import com.login.service.QuizService;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class QuizController 
{

	@Autowired
	private QuizService quizService;
	
	
	//Add Category
	@PostMapping("quiz")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
	{
		Quiz q1=this.quizService.addQuiz(quiz);
	    return ResponseEntity.ok(q1);	
	}

	
	
	//Get Sinle Category
	@GetMapping("quiz/{quizId}")
	public Quiz getQuiz(@PathVariable long quizId)
	{
		return this.quizService.getQuiz(quizId);
	}

	//Get All Category
	@GetMapping("quizall")
	public ResponseEntity<?> getAllQuiz()
	{
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	
	@PutMapping("quiz")
	public Quiz updateQuiz(@RequestBody Quiz quiz)
	{
		return this.quizService.updateQuiz(quiz);
	}
	
	@DeleteMapping("quiz/{quizId}")
	public void deleteCategory(@PathVariable long quizId)
	{
		this.quizService.deleteQuiz(quizId);
	}
	
	
	@GetMapping("categorysingle/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") long cid)
	{
		Category category=new Category();
		category.setCategoryId(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	//Get Active quizzes
	@GetMapping("quiz/active")
	public List<Quiz> getActiveQuizzes()
	{
		return this.quizService.getActiveQuizzes();
	}
	
	
	//Get Active quizzes of categories
	@GetMapping("quiz/category/active/{categoryId}")
	public List<Quiz> getActiveQuizzes(@PathVariable ("categoryId") Long cid)
	{
		Category c=new Category();
		c.setCategoryId(cid);
		return this.quizService.getActiveQuizzesCategory(c);
	}
	
}
























