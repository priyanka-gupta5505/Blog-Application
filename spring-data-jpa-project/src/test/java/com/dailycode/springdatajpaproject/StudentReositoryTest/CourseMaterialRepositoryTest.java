package com.dailycode.springdatajpaproject.StudentReositoryTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycode.springdatajpaproject.Entity.Course;
import com.dailycode.springdatajpaproject.Entity.CourseMaterial;
import com.dailycode.springdatajpaproject.Repository.CourseMaterialRepository;

@SpringBootTest
public class CourseMaterialRepositoryTest {
	
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Test
	public void saveCourseMaterial() {
		
		Course course = Course.builder().title(".net").credit(6).build();
		CourseMaterial courseMaterial = CourseMaterial.builder().url("www.github.com")
				.course(course)
				.build();
		courseMaterialRepository.save(courseMaterial);
	}
	
	@Test
	public void printAllCourseMaterial() {
		List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
		
		System.out.println(" courseMaterials :" + courseMaterials);
		
	}

}
