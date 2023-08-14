package com.login.model.online;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private long quizId;
	
	private String title;
	private String description;
	private String maxMarks;
	private String numberOfQuestions;
	private boolean active=false; // Bydefault quize will be no active, Admin  will do active of quize
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy="quiz", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions=new HashSet<>();
}
