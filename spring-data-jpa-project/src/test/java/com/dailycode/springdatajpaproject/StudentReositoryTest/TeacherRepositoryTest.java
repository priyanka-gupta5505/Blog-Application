package com.dailycode.springdatajpaproject.StudentReositoryTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycode.springdatajpaproject.Entity.Course;
import com.dailycode.springdatajpaproject.Entity.Teacher;
import com.dailycode.springdatajpaproject.Repository.TeacherRepository;

@SpringBootTest
public class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void saveTeacher() {

		Course courseDBA = Course.builder().title("DBA").credit(5).build();
		Course courseJava = Course.builder().title("JAVA").credit(6).build();
		Teacher teacher = Teacher.builder().firstName("Sayan").lastName("Ghosh").courses(List.of(courseDBA, courseJava))
				.build();
		
		teacherRepository.save(teacher);
	}

}
