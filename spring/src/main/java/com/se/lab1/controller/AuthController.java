package com.se.lab1.controller;  


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.se.lab1.model.Course;
import com.se.lab1.model.CourseRepository;

@CrossOrigin(origins = "http://localhost:5173") // used for vue
@RestController
@RequestMapping("/api")

public class AuthController {
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String studentId = loginRequest.get("studentId");
        String password = loginRequest.get("password");

        // for testing only
        if ("1001".equals(studentId) && "123456".equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", studentId);
            response.put("name", "Test Student");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid Student ID or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }


}
