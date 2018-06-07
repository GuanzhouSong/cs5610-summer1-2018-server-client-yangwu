package com.example.myapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget{
	private String title;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="exam")
	@JsonIgnore
	private List<BaseQuestionJoined> questions;
	
	public List<BaseQuestionJoined> getQuestions() {
		return questions;
	}
	public void setQuestions(List<BaseQuestionJoined> questions) {
		this.questions = questions;
	}
}
