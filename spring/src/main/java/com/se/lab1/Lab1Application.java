package com.se.lab1;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Lab1Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Lab1Application.class, args);
//	}
//
//}

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.se.lab1.model.Course;
import com.se.lab1.model.CourseRepository;

@SpringBootApplication
public class Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@Bean
	ApplicationRunner init(CourseRepository repository) {
		//lambda 
		return args -> {

			repository.save(new Course("CSIS2175", "ADVANCED INTEGRATED SOFTWARE DEVELOPMENT"));
			repository.save(new Course("CSIS3275", "SOFTWARE ENGINEERING"));
			repository.save(new Course("CSIS1190", "EXCEL FOR BUSINESS"));
			repository.findAll().forEach(System.out::println);
		};
	}

}
