package com.example.myapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JOINED_MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceQuestionJoined extends BaseQuestionJoined {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	
	
}
