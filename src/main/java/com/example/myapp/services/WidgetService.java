

package com.example.myapp.services;

import java.util.Collections;
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

import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable("lessonId") int lessonId, @RequestBody Widget newWidget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if (data.isPresent()) {
			Lesson lesson = data.get();
			newWidget.setLesson(lesson);
			return widgetRepository.save(newWidget);
		}
		return null;
	}
	

	
//	@PostMapping("/api/widget/save")
//	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
//		System.out.println("save");
//		widgetRepository.deleteAll();
//
//			for (Widget widget : widgets) {
//				widgetRepository.save(widget);
//			}
//		}
//	
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveWidgetsForLesson(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> widgets) {
		
//		widgetRepository.deleteAll();
			System.out.println("working here");
			Optional<Lesson> data = lessonRepository.findById(lessonId);
			
			if (data.isPresent()) {
				Lesson lesson = data.get();
				List<Widget> tmp = lesson.getWidgets();
				widgetRepository.deleteAll(tmp);
				
				for (Widget widget : widgets) {
					widget.setLesson(lesson);
				}
				widgetRepository.saveAll(widgets);
			}
		}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{
		widgetRepository.deleteById(widgetId);
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets()
	{
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId) {
		
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			
			Lesson lesson = data.get();
			List<Widget> result = lesson.getWidgets();
			Collections.sort(result);
			return result;
			
//			Lesson lesson = data.get();
//			List<Widget> result = lesson.getWidgets();
//			return result;
		}
		return null;		
	}
	
	
}
