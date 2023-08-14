package com.login.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.model.online.Question;
import com.login.model.online.Quiz;

public interface QuestionRepository  extends JpaRepository<Question, Long> 
{

	Set<Question> findByQuiz(Quiz quiz);

	

}
