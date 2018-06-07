package com.example.myapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;


@Entity
@Table(name = "JOINED_ESSAY_QUESTION")
public class EssayQuestionJoined extends BaseQuestionJoined {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	
	
}
