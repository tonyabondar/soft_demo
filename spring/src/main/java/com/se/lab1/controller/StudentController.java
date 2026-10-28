package com.se.lab1.controller;  


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Dependency Injection
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.beans.factory.annotation.Autowired;  

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.lab1.model.*;

@CrossOrigin(origins = "http://localhost:5173") // used for vue
@RestController
@RequestMapping("/api")

public class StudentController {
	private static ConcurrentHashMap<String, Student> studentDatabase=new ConcurrentHashMap<>();
	
	static  {
		Student stud1001=new Student("1001", "123456", "", "");
		studentDatabase.put(stud1001.getStudentId(), stud1001);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
		String studentId = loginRequest.get("studentId");
		String password = loginRequest.get("password");
		// 1. verify the input student ID and password are not empty
		if (studentId == null || studentId.trim().isEmpty() ||
			password == null || password.trim().isEmpty()) {
			Map<String, String> error = new HashMap<>();
			error.put("message", "Student ID and Password cannot be empty.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		// 2. check the student existence
		Student student = studentDatabase.get(studentId);
		// 3. validate the login info
		if (student != null && student.getPassword().equals(password)) {
			// success, assemble the data back to Vue
			Map<String, Object> response = new HashMap<>();
			response.put("id", student.getStudentId());
			response.put("name", student.getName());
		// send 200 OK along with student info
			return ResponseEntity.ok(response);
		} else {
			// login fail
			Map<String, String> error = new HashMap<>();
			error.put("message", "Invalid Student ID or Password");
			// send 401 Unauthorized
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			}
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@RequestBody Student newStudent) {
		// validate data, make sure not null
		if (newStudent.getStudentId() == null || ((String)
			newStudent.getStudentId()).trim().isEmpty() ||
			newStudent.getPassword() == null || ((String)
			newStudent.getPassword()).trim().isEmpty()) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Student ID and Password cannot be empty.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		// check studentId has been created or not
		if (studentDatabase.containsKey(newStudent.getStudentId())) {
			Map<String, String> errorResponse = new HashMap<>();
			// align with Vue frontend
			errorResponse.put("message", "Registration failed: Student ID already exists.");
			// return 409 Conflict status code
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
		// if it is unique StudentID, "insert" into the simulated database
		studentDatabase.put(newStudent.getStudentId(), newStudent);
		// successfully registered
		Map<String, String> successResponse = new HashMap<>();
		successResponse.put("message", "Registration successful!");
		// return 201 Created status code
		return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
	}


}
