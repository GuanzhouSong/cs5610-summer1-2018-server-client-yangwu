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
import com.example.myapp.models.TrueFalseQuestionJoined;
import com.example.myapp.repositories.BaseQuestionJoinedRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.TrueFalseQuestionJoinedRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueFalseQuestionJoinedService {

	@Autowired
	TrueFalseQuestionJoinedRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	BaseQuestionJoinedRepository baseRepository;
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public void saveTrueFalseQuestion(@PathVariable("examId") int examId, @RequestBody TrueFalseQuestionJoined truefalse) {
		Optional<Exam> data = examRepository.findById(examId);
	
		if (data.isPresent()) {
			Exam exam = data.get();
			truefalse.setExam(exam);
			repository.save(truefalse);
		}
	}
	
	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId)
	{
		baseRepository.deleteById(questionId);
	}
}
