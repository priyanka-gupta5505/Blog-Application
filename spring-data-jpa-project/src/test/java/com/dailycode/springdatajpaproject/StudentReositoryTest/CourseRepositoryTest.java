package com.dailycode.springdatajpaproject.StudentReositoryTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.dailycode.springdatajpaproject.Entity.Course;
import com.dailycode.springdatajpaproject.Repository.CourseRepository;

@SpringBootTest
public class CourseRepositoryTest {
	
	@Autowired
	private CourseRepository courseRepository;
	@Test
	public void printCourse() {
		List<Course> courses  = courseRepository.findAll();
		
		System.out.println("courses :" + courses );
		
	}
	
	@Test
	  public void findAllPagination() {
	     PageRequest firstPagewithThreeRecords =
	                PageRequest.of(0, 3);
	        PageRequest secondPageWithTwoRecords = 
	                PageRequest.of(1,2);
	        
	        List<Course> courses =
	                courseRepository.findAll(secondPageWithTwoRecords)
	                        .getContent();

	        long totalElements =
	                courseRepository.findAll(secondPageWithTwoRecords)
	                .getTotalElements();

	        long totalPages =
	                courseRepository.findAll(secondPageWithTwoRecords)
	                .getTotalPages();

	        System.out.println("totalPages = " + totalPages);
	        
	        System.out.println("totalElements = " + totalElements);

	        System.out.println("courses = " + courses);
		  
	  }
	
	
}
