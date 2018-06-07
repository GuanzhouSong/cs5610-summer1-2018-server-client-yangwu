package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.EssayQuestionJoined;
import com.example.myapp.models.Exam;
import com.example.myapp.repositories.BaseQuestionJoinedRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.EssayQuestionJoinedRepository;



@RestController
@CrossOrigin(origins = "*")
public class EssayQuestionJoinedService {
	@Autowired
	EssayQuestionJoinedRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	BaseQuestionJoinedRepository baseRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public void saveEssayQuestion(@PathVariable("examId") int examId, @RequestBody EssayQuestionJoined essay) {
		Optional<Exam> data = examRepository.findById(examId);
	
		if (data.isPresent()) {
			Exam exam = data.get();
			essay.setExam(exam);
			repository.save(essay);}
	}
	
	@DeleteMapping("/api/essay/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId)
	{
		baseRepository.deleteById(questionId);
	}
}

