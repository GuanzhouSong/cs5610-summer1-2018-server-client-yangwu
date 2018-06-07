package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.myapp.models.Exam;

import com.example.myapp.models.MultipleChoiceQuestionJoined;
import com.example.myapp.repositories.BaseQuestionJoinedRepository;
import com.example.myapp.repositories.ExamRepository;

import com.example.myapp.repositories.MultipleChoicesQuestionJoinedRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionJoinedService {
	@Autowired
	MultipleChoicesQuestionJoinedRepository repository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	BaseQuestionJoinedRepository baseRepository;
	
	
	@PostMapping("/api/exam/{examId}/choice")
	public void saveMultipleChoiceQuestion(@PathVariable("examId") int examId, @RequestBody MultipleChoiceQuestionJoined multiple) {
		
		
//		System.out.println("working here");
		Optional<Exam> data = examRepository.findById(examId);
//		
		if (data.isPresent()) {
			Exam exam = data.get();
			multiple.setExam(exam);
			repository.save(multiple);}
		
	}
	
	@DeleteMapping("/api/choice/{questionId}")
	public void deleteMulipleChoiceQuestion(@PathVariable("questionId") int questionId)
	{
		baseRepository.deleteById(questionId);
	}
	
	
	
}
