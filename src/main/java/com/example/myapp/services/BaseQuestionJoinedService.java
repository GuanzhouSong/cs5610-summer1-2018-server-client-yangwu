package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.BaseQuestionJoined;
import com.example.myapp.models.Exam;
import com.example.myapp.models.MultipleChoiceQuestionJoined;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.BaseQuestionJoinedRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseQuestionJoinedService {
	@Autowired
	BaseQuestionJoinedRepository repository;

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
//	@GetMapping("/api/exam/{examId}/question")
//	public List<BaseQuestionJoined> findAllQuestionsForExam(
//			@PathVariable("examId") int examId) {
//		
//		Optional<Exam> data = examRepository.findById(examId);
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			List<BaseQuestionJoined> result = exam.getQuestions();
//			return result;
//		}
//		return null;		
//	}
	

	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseQuestionJoined> findAllQuestionsForExam(
			@PathVariable("examId") int examId) {
		
		Optional<Widget> data = widgetRepository.findById(examId);
		if(data.isPresent()) {
			Widget widget = data.get();
			Exam exam = (Exam)widget;
			List<BaseQuestionJoined> result = exam.getQuestions();
			return result;
		}
		return null;		
	}
	
}
