package com.dailycode.springdatajpaproject.StudentReositoryTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycode.springdatajpaproject.Entity.Guardian;
import com.dailycode.springdatajpaproject.Entity.Student;
import com.dailycode.springdatajpaproject.Repository.StudentRepository;

@SpringBootTest
public class StudentRepoTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {

		Student student = Student.builder().emailId("rohit.gupta@gmail.com").firstName("Rohit").lastName("Gupta")
				.build();
		studentRepository.save(student);
	}

	@Test
	public void saveStudentWithGuardian() {
		Guardian guardian = Guardian.builder().name("Rabindra").email("ranidra@gmail.com").mobile(91681750930L).build();
		Student student = Student.builder().firstName("Rahul").lastName("Gupta").emailId("rahul.gupta@gmail.com")
				.guardian(guardian).build();
		studentRepository.save(student);
	}

	@Test
	public void printAllStudent() {
		List<Student> studentList = studentRepository.findAll();

		System.out.println("studentList = " + studentList);
	}

	@Test
	public void printStudentByFirstName() {
		List<Student> student = studentRepository.findByFirstName("priyanka");
		System.out.println("studentList = " + student);
	}

	@Test
	public void printStudentByFirstNameContaining() {
		List<Student> students = studentRepository.findByFirstNameContaining("nka");
		System.out.println("studentList = " + students);
	}

	@Test
	public void findByFirstLastNotNull() {
		List<Student> students = studentRepository.findByLastNameNotNull();
		System.out.println("studentList = " + students);
	}

	@Test
	public void printStudentBasedOnGuardianName() {
		List<Student> students = studentRepository.findByGuardianName("Rabindra");
		System.out.println("studentList = " + students);
	}

	@Test
	public void printgetStudentByEmailAddress() {
		Student student = studentRepository.getStudentByEmailAddress("priyanka.gupta@gmail.com");
		System.out.println("student = " + student);

	}

	@Test
	public void printgetStudentFirstNameByEmailAddress() {
		String firstName = studentRepository.getStudentFirstNameByEmailAddress("priyanka.gupta@gmail.com");
		System.out.println("firstName = " + firstName);

	}
	
	@Test
	public void getStudentFirstNameByEmailAddressNative() {
		
		Student student = studentRepository.getStudentFirstNameByEmailAddressNative("rohit.gupta@gmail.com");
		System.out.println("student = " + student);
		
		
	}
	
	@Test
	public void getStudentFirstNameByEmailAddressNativeNamedParam() {
		
		Student student = studentRepository.getStudentFirstNameByEmailAddressNativeNamedParam("rohit.gupta@gmail.com");
		System.out.println("student = " + student);
		
	}
	
	@Test
	public void updateStudentNameByEmailId() {
		studentRepository.updateStudentNameByEmailId("ravi", "rohit.gupta@gmail.com");
	}

}
