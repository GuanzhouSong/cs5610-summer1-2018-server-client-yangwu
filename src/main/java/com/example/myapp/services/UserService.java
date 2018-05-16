package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;


@RestController
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		User res = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (res != null) {
			request.getServletContext().setAttribute("currentUser", res);
			return res;
		} 
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)repository.findAll();
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpServletResponse response) {
		User data = repository.findUserByUsername(user.getUsername());
		if (data == null) {
			User res = repository.save(user);
			return res;	
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/profile")
	public User popularProfile(HttpServletRequest request, HttpServletResponse response) {
		Object cur = request.getServletContext().getAttribute("currentUser");
		
		if (cur != null) {
			return (User)cur;
		}
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}

	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser, HttpServletResponse response) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			User user =  data.get();
			user.setUsername(newUser.getUsername());
			user.setFirstName(newUser.getFirstName());
			user.setPassword(newUser.getPassword());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		User cur = (User) request.getServletContext().getAttribute("currentUser");
		
		if (cur != null) {
			cur.setUsername(user.getUsername());
			cur.setPhone(user.getPhone());
			cur.setEmail(user.getEmail());
			cur.setRole(user.getRole());
			cur.setDateOfBirth(user.getDateOfBirth());
			repository.save(cur);
			return cur; 
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
		
	@PostMapping("/api/profile")
	public void logout(HttpServletRequest request) {
		request.getServletContext().removeAttribute("currentUser");
	}
}
