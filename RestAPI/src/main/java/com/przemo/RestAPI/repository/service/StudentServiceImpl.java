package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(int id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);		
	}
	
	
}
