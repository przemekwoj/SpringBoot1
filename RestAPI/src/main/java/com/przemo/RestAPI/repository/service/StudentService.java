package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.user.Student;

public interface StudentService
{
	public List<Student> getAllStudents();
	
	public Optional<Student> getStudentById(int id);
	
	public Student saveStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(int id);

	
}
