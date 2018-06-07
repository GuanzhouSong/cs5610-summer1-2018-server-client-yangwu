package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.repositories.AssignmentRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.models.Assignment;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	LessonRepository lessonRepository;
	
//	@GetMapping("/api/lesson/{lessonId}/assignment")
//	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId) {
//		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
//		if(optionalLesson.isPresent()) {
//			Lesson lesson = optionalLesson.get();
//			return lesson.getAssignments();
//		}
//		return null;
//	}
//	
//	@PostMapping("/api/lesson/{lessonId}/assignment")
//	public void saveAllAssignments(@RequestBody List<Assignment> assignments) {
//		assignmentRepository.deleteAll();
//		
//		for(Assignment assignment: assignments) {
//			assignmentRepository.save(assignment);
//		}
//	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public void saveAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment) {
		
		
//		System.out.println("working here");
		Optional<Lesson> data = lessonRepository.findById(lessonId);
//		
		if (data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			assignmentRepository.save(assignment);}
		
//			List<Widget> tmp = lesson.getWidgets();
//			widgetRepository.deleteAll(tmp);
//			
//			for (Widget widget : widgets) {
//				widget.setLesson(lesson);
//			}
//			widgetRepository.saveAll(widgets);
//		}
		
			
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId)
	{
		assignmentRepository.deleteById(assignmentId);
	}
	
	
	
	
	
	
	
}
