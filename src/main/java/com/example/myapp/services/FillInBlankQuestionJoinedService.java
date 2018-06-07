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
import com.example.myapp.models.FillInBlankQuestionJoined;
import com.example.myapp.repositories.BaseQuestionJoinedRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInBlankQuestionJoinedRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInBlankQuestionJoinedService {

	@Autowired
	FillInBlankQuestionJoinedRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	BaseQuestionJoinedRepository baseRepository;
	
	@PostMapping("/api/exam/{examId}/blanks")
	public void saveEssayQuestion(@PathVariable("examId") int examId, @RequestBody FillInBlankQuestionJoined blanks) {
		Optional<Exam> data = examRepository.findById(examId);
	
		if (data.isPresent()) {
			Exam exam = data.get();
			blanks.setExam(exam);
			repository.save(blanks);}
	}
	
	@DeleteMapping("/api/blanks/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId)
	{
		baseRepository.deleteById(questionId);
	}
}
