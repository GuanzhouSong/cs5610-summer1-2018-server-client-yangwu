package com.example.myapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_ISTRUE_QUESTION")
public class TrueFalseQuestionJoined extends BaseQuestionJoined {
	@Column(name = "ISTRUE", nullable = false)
	private Boolean isTrue;
	public Boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}
