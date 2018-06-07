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
import com.example.myapp.models.Lesson;

import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {

	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public void saveExam(@PathVariable("lessonId") int lessonId, @RequestBody Exam exam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if (data.isPresent()) {
			Lesson lesson = data.get();
			exam.setLesson(lesson);
			examRepository.save(exam);
		}	
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId)
	{
		examRepository.deleteById(examId);
	}
	
}
